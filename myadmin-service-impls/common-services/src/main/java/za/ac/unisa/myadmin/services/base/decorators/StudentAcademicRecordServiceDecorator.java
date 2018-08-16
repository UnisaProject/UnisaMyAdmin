package za.ac.unisa.myadmin.services.base.decorators;

import za.ac.unisa.myadmin.common.dto.ErrorInfo;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;
import za.ac.unisa.myadmin.qualification.services.dto.AcademicRecordEmailRequestInfo;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;

import java.util.List;

public class StudentAcademicRecordServiceDecorator implements StudentAcademicRecordService {

	private StudentAcademicRecordService nextDecorator;

	public StudentAcademicRecordService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(StudentAcademicRecordService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	@Override
	public List<StudentAcademicQualificationRecordInfo> getStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getStudentAcademicQualificationResults(studentNumber);
	}

	@Override
	public ErrorInfo sendStudentAcademicRecordEmail(AcademicRecordEmailRequestInfo emailRequestInfo) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().sendStudentAcademicRecordEmail(emailRequestInfo);
	}

	@Override
	public List<StudentAcademicQualificationRecordInfo> getQualificationResultsByStudentNumber(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getQualificationResultsByStudentNumber(studentNumber);
	}

	@Override
	public StudentAcademicQualificationRecordInfo getQualificationResultByStudentNumberAndQualCode(Integer studentNumber, String qualCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getQualificationResultByStudentNumberAndQualCode(studentNumber, qualCode);
	}

}
