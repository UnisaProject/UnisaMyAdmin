package za.ac.unisa.myadmin.student.services.decorators;

import org.apache.commons.lang3.StringUtils;
import za.ac.unisa.myadmin.common.dto.ErrorInfo;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.qualification.services.dto.AcademicRecordEmailRequestInfo;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.services.base.decorators.StudentAcademicRecordServiceDecorator;
import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;

import java.util.List;

/**
 * This decorator validates required-ness.
 */
public class StudentAcademicRecordServiceComplianceDecorator extends StudentAcademicRecordServiceDecorator implements StudentAcademicRecordService {

	@Override
	public List<StudentAcademicQualificationRecordInfo> getStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (studentNumber == null) {
			throw new MissingParameterException("Please enter a valid student number.");
		}
		return getNextDecorator().getStudentAcademicQualificationResults(studentNumber);
	}

	@Override
	public ErrorInfo sendStudentAcademicRecordEmail(AcademicRecordEmailRequestInfo emailRequestInfo) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (emailRequestInfo == null) {
			throw new MissingParameterException("Please enter a valid student number.");
		}
		if (emailRequestInfo.getStudentNumber() == null) {
			throw new MissingParameterException("Please enter a valid student number.");
		}
		if(StringUtils.isBlank(emailRequestInfo.getAcademicQualificationCode())){
			throw new OperationFailedException("Qualification is required.");
		}
		return getNextDecorator().sendStudentAcademicRecordEmail(emailRequestInfo);
	}
}
