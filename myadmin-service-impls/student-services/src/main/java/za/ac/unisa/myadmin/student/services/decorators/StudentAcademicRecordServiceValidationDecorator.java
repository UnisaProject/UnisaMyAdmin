package za.ac.unisa.myadmin.student.services.decorators;

import org.apache.commons.lang3.StringUtils;
import za.ac.unisa.myadmin.common.dto.ErrorInfo;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.EmailLogInfo;
import za.ac.unisa.myadmin.generic.dto.MessageInfo;
import za.ac.unisa.myadmin.generic.services.EmailLogService;
import za.ac.unisa.myadmin.generic.services.GenericServicesConstants;
import za.ac.unisa.myadmin.generic.services.MessageService;
import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;
import za.ac.unisa.myadmin.qualification.services.dto.AcademicRecordEmailRequestInfo;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.services.base.decorators.StudentAcademicRecordServiceDecorator;
import za.ac.unisa.myadmin.student.services.StudentService;
import za.ac.unisa.myadmin.student.services.StudentServicesConstants;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * This decorator validates student status.
 */
public class StudentAcademicRecordServiceValidationDecorator extends StudentAcademicRecordServiceDecorator implements StudentAcademicRecordService {

	//TODO Replace with suitable authorization lookup possibly CAS info.
	private boolean isStudentUser = true;

	private EmailLogService emailLogService;

	private MessageService messageService;

	private StudentService studentService;

	public void setEmailLogService(EmailLogService emailLogService) {
		this.emailLogService = emailLogService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}


	@Override
	public List<StudentAcademicQualificationRecordInfo> getStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (String.valueOf(studentNumber).length() < 7) {
			throw new OperationFailedException("Student Number can not be less than 7 characters");
		}
		if (String.valueOf(studentNumber).length() > 8) {
			throw new OperationFailedException("Student Number can not be greater than 8 characters");
		}

		if (isStudentUser) {
			StudentInfo student = studentService.getStudentByNumber(studentNumber);
			MessageInfo genMessageEnt;
			if (student.isExamFeesDebt() || student.isFinancialBlock()) {
				genMessageEnt = messageService.getMessageById(GenericServicesConstants.UNISA_MESSAGECODE_FEE_BLOCK, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
				if (genMessageEnt != null) {
					throw new InvalidParameterException(genMessageEnt.getMessage());
				}
			}

			if (student.getLibraryBlackList() > 0) {
				genMessageEnt = messageService.getMessageById(GenericServicesConstants.UNISA_MESSAGECODE_LIBRARY, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
				if (genMessageEnt != null) {
					throw new InvalidParameterException(genMessageEnt.getMessage());
				}
			}

			if (student.getDisciplinaryIncident() > 0) {
				genMessageEnt = messageService.getMessageById(GenericServicesConstants.UNISA_MESSAGECODE_STUD_DISPL, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
				if (genMessageEnt != null) {
					throw new InvalidParameterException(genMessageEnt.getMessage());
				}
			}
		}
		List<StudentAcademicQualificationRecordInfo> records = getNextDecorator().getStudentAcademicQualificationResults(studentNumber);
		if (records.isEmpty()) {
			throw new OperationFailedException("Academic record list is empty.");
		}
		return records;
	}

	@Override
	public ErrorInfo sendStudentAcademicRecordEmail(AcademicRecordEmailRequestInfo emailRequestInfo) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (String.valueOf(emailRequestInfo.getStudentNumber()).length() < 7) {
			throw new OperationFailedException("Student Number can not be less than 7 characters");
		}
		if (String.valueOf(emailRequestInfo.getStudentNumber()).length() > 8) {
			throw new OperationFailedException("Student Number can not be greater than 8 characters");
		}
		StudentInfo student = studentService.getStudentByNumber(emailRequestInfo.getStudentNumber());
		StudentAcademicQualificationRecordInfo academicQualificationRecordInfo = this.getQualificationResultByStudentNumberAndQualCode(emailRequestInfo.getStudentNumber(), emailRequestInfo.getAcademicQualificationCode());
		MessageInfo genMessageEnt;
		if (student.getNsfasContractBlock() > 0) {
			genMessageEnt = messageService.getMessageById(GenericServicesConstants.UNISA_MESSAGECODE_NSFAS_BLOCK, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
			if (genMessageEnt != null) {
				throw new InvalidParameterException(genMessageEnt.getMessage());
			}
		}

		if (!student.isNumberRestricted()) {
			genMessageEnt = messageService.getMessageById(GenericServicesConstants.UNISA_MESSAGECODE_AUDITED, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
			if (genMessageEnt != null) {
				throw new InvalidParameterException(genMessageEnt.getMessage());
			}
		}
		if (!academicQualificationRecordInfo.isAuditFlag()) {
			genMessageEnt = messageService.getMessageById(GenericServicesConstants.UNISA_MESSAGECODE_AUDITED, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
			if (genMessageEnt != null) {
				throw new InvalidParameterException(genMessageEnt.getMessage());
			}
		}

		if (!academicQualificationRecordInfo.getStatus().equalsIgnoreCase("CO") && academicQualificationRecordInfo.getGraduationCeremonyDate() == null) {
			genMessageEnt = messageService.getMessageById(GenericServicesConstants.UNISA_MESSAGECODE_GENMAILERROR, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
			if (genMessageEnt != null) {
				throw new InvalidParameterException(genMessageEnt.getMessage());
			}

		}
		validateEmailLog(emailRequestInfo);

		ErrorInfo messageInfo = getNextDecorator().sendStudentAcademicRecordEmail(emailRequestInfo);
		if (messageInfo!=null && StringUtils.isNotBlank(messageInfo.getMessage())) {
			if (messageInfo.getMessage().contains("Report completed successfully")) {
				genMessageEnt = messageService.getMessageById(GenericServicesConstants.UNISA_MESSAGECODE_MAILOK, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
				if (genMessageEnt != null) {
					messageInfo.setMessage(genMessageEnt.getMessage());
				}
			} else {
				genMessageEnt = messageService.getMessageById(GenericServicesConstants.UNISA_MESSAGECODE_GENMAILERROR, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
				if (genMessageEnt != null) {
					throw new InvalidParameterException(genMessageEnt.getMessage());
				}
			}
		}
		return messageInfo;
	}

	/**
	 * Validate email log table, Allowed hourly emails.
	 * @param emailRequestInfo
	 * @throws MissingParameterException
	 * @throws InvalidParameterException
	 * @throws OperationFailedException
	 * @throws DoesNotExistException
	 */
	private void validateEmailLog(AcademicRecordEmailRequestInfo emailRequestInfo) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		String recipient, program, reqSystem, reqProgram, emailBody, emailType;
		recipient = String.valueOf(emailRequestInfo.getStudentNumber());
		program = StudentServicesConstants.ACADEMIC_RECORD_EMAIL_LOG_DEFAULT_PROGRAM;
		reqSystem = StudentServicesConstants.ACADEMIC_RECORD_EMAIL_LOG_REQUIRED_SYSTEM;
		reqProgram = StudentServicesConstants.ACADEMIC_RECORD_EMAIL_LOG_REQUIRED_PROGRAM;
		emailBody = emailRequestInfo.getAcademicQualificationCode();
		//
		emailType = StudentServicesConstants.ACADEMIC_RECORD_EMAIL_LOG_DECLARE_NO_MARKS;
		if (emailRequestInfo.isAttachMarks()) {
			emailType = StudentServicesConstants.ACADEMIC_RECORD_EMAIL_LOG_DECLARE_WITH_MARKS;
		}
		EmailLogInfo emailLogInfo = emailLogService.getLastestEmailRequestForAcademicRecord(recipient, program, emailType, reqSystem, reqProgram, emailBody);
		if (emailLogInfo != null) {
			//Allowed to email per hour. Check some business rules around time.
			if (!emailLogInfo.getDateSent().toInstant().isBefore(Instant.now().minus(1, ChronoUnit.HOURS))) {
				throw new OperationFailedException("You requested an Academic Record less than an hour ago. Please wait a while to receive your email or try again later.");
			}
		}
	}
}
