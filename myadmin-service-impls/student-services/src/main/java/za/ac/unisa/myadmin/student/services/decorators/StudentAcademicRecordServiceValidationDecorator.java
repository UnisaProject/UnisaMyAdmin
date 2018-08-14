package za.ac.unisa.myadmin.student.services.decorators;

import org.apache.commons.lang3.StringUtils;
import za.ac.unisa.myadmin.common.dto.ErrorInfo;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.EmailLogInfo;
import za.ac.unisa.myadmin.generic.dto.GenericMessageInfo;
import za.ac.unisa.myadmin.generic.services.EmailLogService;
import za.ac.unisa.myadmin.generic.services.GenericServicesConstants;
import za.ac.unisa.myadmin.generic.services.GenericService;
import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;
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

	private GenericService genericService;

	private StudentService studentService;

	public void setEmailLogService(EmailLogService emailLogService) {
		this.emailLogService = emailLogService;
	}

	public void setGenericService(GenericService genericService) {
		this.genericService = genericService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}


	@Override
	public List<StudentAcademicQualificationRecordInfo> requestStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (String.valueOf(studentNumber).length() < 7) {
			throw new OperationFailedException("Student Number can not be less than 7 characters");
		}
		if (String.valueOf(studentNumber).length() > 8) {
			throw new OperationFailedException("Student Number can not be greater than 8 characters");
		}

		if (isStudentUser) {
			StudentInfo student = studentService.getStudentByNumber(studentNumber);
			GenericMessageInfo genMessageEnt;
			if (student.isExamFeesDebt() || student.isFinancialBlock()) {
				genMessageEnt = genericService.getGenericMessageById(GenericServicesConstants.UNISA_MESSAGECODE_FEE_BLOCK, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
				if (genMessageEnt != null) {
					throw new InvalidParameterException(genMessageEnt.getMessage());
				}
			}

			if (student.getLibraryBlackList() > 0) {
				genMessageEnt = genericService.getGenericMessageById(GenericServicesConstants.UNISA_MESSAGECODE_LIBRARY, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
				if (genMessageEnt != null) {
					throw new InvalidParameterException(genMessageEnt.getMessage());
				}
			}

			if (student.getDisciplinaryIncident() > 0) {
				genMessageEnt = genericService.getGenericMessageById(GenericServicesConstants.UNISA_MESSAGECODE_STUD_DISPL, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
				if (genMessageEnt != null) {
					throw new InvalidParameterException(genMessageEnt.getMessage());
				}
			}
		}
		List<StudentAcademicQualificationRecordInfo> records = getNextDecorator().requestStudentAcademicQualificationResults(studentNumber);
		if (records.isEmpty()) {
			throw new OperationFailedException("Academic record list is empty.");
		}
		return records;
	}

	@Override
	public ErrorInfo requestStudentAcademicRecordEmail(Integer studentNumber, String academicQualificationCode, boolean isAttachMarks) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (String.valueOf(studentNumber).length() < 7) {
			throw new OperationFailedException("Student Number can not be less than 7 characters");
		}
		if (String.valueOf(studentNumber).length() > 8) {
			throw new OperationFailedException("Student Number can not be greater than 8 characters");
		}
		StudentInfo student = studentService.getStudentByNumber(studentNumber);
		StudentAcademicQualificationRecordInfo academicQualificationRecordInfo = this.getQualificationResultByStudentNumberAndQualCode(studentNumber, academicQualificationCode);
		GenericMessageInfo genMessageEnt;
		if (student.getNsfasContractBlock() > 0) {
			genMessageEnt = genericService.getGenericMessageById(GenericServicesConstants.UNISA_MESSAGECODE_NSFAS_BLOCK, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
			if (genMessageEnt != null) {
				throw new InvalidParameterException(genMessageEnt.getMessage());
			}
		}

		if (!student.isNumberRestricted()) {
			genMessageEnt = genericService.getGenericMessageById(GenericServicesConstants.UNISA_MESSAGECODE_AUDITED, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
			if (genMessageEnt != null) {
				throw new InvalidParameterException(genMessageEnt.getMessage());
			}
		}
		if (!academicQualificationRecordInfo.isAuditFlag()) {
			genMessageEnt = genericService.getGenericMessageById(GenericServicesConstants.UNISA_MESSAGECODE_AUDITED, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
			if (genMessageEnt != null) {
				throw new InvalidParameterException(genMessageEnt.getMessage());
			}
		}

		if (!academicQualificationRecordInfo.getStatus().equalsIgnoreCase("CO") && academicQualificationRecordInfo.getGraduationCeremonyDate() == null) {
			genMessageEnt = genericService.getGenericMessageById(GenericServicesConstants.UNISA_MESSAGECODE_GENMAILERROR, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
			if (genMessageEnt != null) {
				throw new InvalidParameterException(genMessageEnt.getMessage());
			}

		}
		validateEmailLog(studentNumber, academicQualificationCode, isAttachMarks);

		ErrorInfo messageInfo = getNextDecorator().requestStudentAcademicRecordEmail(studentNumber, academicQualificationCode, isAttachMarks);
		if (messageInfo!=null && StringUtils.isNotBlank(messageInfo.getMessage())) {
			if (messageInfo.getMessage().contains("Report completed successfully")) {
				genMessageEnt = genericService.getGenericMessageById(GenericServicesConstants.UNISA_MESSAGECODE_MAILOK, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
				if (genMessageEnt != null) {
					messageInfo.setMessage(genMessageEnt.getMessage());
				}
			} else {
				genMessageEnt = genericService.getGenericMessageById(GenericServicesConstants.UNISA_MESSAGECODE_GENMAILERROR, GenericServicesConstants.UNISA_PROGRAM_ACADEMIC_HISTORY);
				if (genMessageEnt != null) {
					throw new InvalidParameterException(genMessageEnt.getMessage());
				}
			}
		}
		return messageInfo;
	}

	/**
	 * Validate email log table, Allowed hourly emails.
	 * @param studentNumber
	 * @param academicQualificationCode
	 * @param isAttachMarks
	 * @throws MissingParameterException
	 * @throws InvalidParameterException
	 * @throws OperationFailedException
	 * @throws DoesNotExistException
	 */
	private void validateEmailLog(Integer studentNumber, String academicQualificationCode, boolean isAttachMarks) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		String recipient, program, reqSystem, reqProgram, emailBody, emailType;
		recipient = String.valueOf(studentNumber);
		program = StudentServicesConstants.ACADEMIC_RECORD_EMAIL_LOG_DEFAULT_PROGRAM;
		reqSystem = StudentServicesConstants.ACADEMIC_RECORD_EMAIL_LOG_REQUIRED_SYSTEM;
		reqProgram = StudentServicesConstants.ACADEMIC_RECORD_EMAIL_LOG_REQUIRED_PROGRAM;
		emailBody = academicQualificationCode;
		//
		emailType = StudentServicesConstants.ACADEMIC_RECORD_EMAIL_LOG_DECLARE_NO_MARKS;
		if (isAttachMarks) {
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
