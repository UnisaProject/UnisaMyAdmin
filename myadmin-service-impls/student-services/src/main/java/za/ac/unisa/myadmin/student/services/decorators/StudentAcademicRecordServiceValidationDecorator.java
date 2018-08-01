package za.ac.unisa.myadmin.student.services.decorators;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.generic.dto.GenericMessageInfo;
import za.ac.unisa.myadmin.generic.services.UnisaGenericService;
import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.services.base.decorators.StudentAcademicRecordServiceDecorator;
import za.ac.unisa.myadmin.student.services.StudentService;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

import java.util.List;

/**
 * This decorator validates student status.
 */
public class StudentAcademicRecordServiceValidationDecorator extends StudentAcademicRecordServiceDecorator implements StudentAcademicRecordService {

	//TODO Replace with suitable authorization lookup possibly CAS info.
	private boolean isStudentUser = true;

	private StudentService studentService;

	private UnisaGenericService genericService;

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setGenericService(UnisaGenericService genericService) {
		this.genericService = genericService;
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
				genMessageEnt = genericService.getGenericMessageById("FEEBLOCK", "UNISA_ACADHISTORY");
				if (genMessageEnt != null) {
					throw new InvalidParameterException(genMessageEnt.getMessage());
				}
			}

			if (student.getLibraryBlackList() > 0) {
				genMessageEnt = genericService.getGenericMessageById("LIBRARY", "UNISA_ACADHISTORY");
				if (genMessageEnt != null) {
					throw new InvalidParameterException(genMessageEnt.getMessage());
				}
			}

			if (student.getDisciplinaryIncident() > 0) {
				genMessageEnt = genericService.getGenericMessageById("STUDISPL", "UNISA_ACADHISTORY");
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
	public List<StudentAcademicQualificationRecordInfo> requestStudentAcademicRecordEmail(Integer studentNumber, String academicQualificationCode, boolean isAttachMarks) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (String.valueOf(studentNumber).length() < 7) {
			throw new OperationFailedException("Student Number can not be less than 7 characters");
		}
		if (String.valueOf(studentNumber).length() > 8) {
			throw new OperationFailedException("Student Number can not be greater than 8 characters");
		}
		StudentInfo student = studentService.getStudentByNumber(studentNumber);
		//StudentInfo student = this.get(studentNumber);
		StudentAcademicQualificationRecordInfo academicQualificationRecordInfo = this.getQualificationResultByStudentNumberAndQualCode(studentNumber, academicQualificationCode);
		GenericMessageInfo genMessageEnt;
		if (student.getNsfasContractBlock() > 0) {
			genMessageEnt = genericService.getGenericMessageById("NSFASBLOCK", "UNISA_ACADHISTORY");
			if (genMessageEnt != null) {
				throw new InvalidParameterException(genMessageEnt.getMessage());
			}
		}

		if (!student.isNumberRestricted()) {
			genMessageEnt = genericService.getGenericMessageById("AUDITED", "UNISA_ACADHISTORY");
			if (genMessageEnt != null) {
				throw new InvalidParameterException(genMessageEnt.getMessage());
			}
		}
		if (!academicQualificationRecordInfo.isAuditFlag()) {
			genMessageEnt = genericService.getGenericMessageById("AUDITED", "UNISA_ACADHISTORY");
			if (genMessageEnt != null) {
				throw new InvalidParameterException(genMessageEnt.getMessage());
			}
		}

		return  getNextDecorator().requestStudentAcademicRecordEmail(studentNumber, academicQualificationCode, isAttachMarks);
	}
}
