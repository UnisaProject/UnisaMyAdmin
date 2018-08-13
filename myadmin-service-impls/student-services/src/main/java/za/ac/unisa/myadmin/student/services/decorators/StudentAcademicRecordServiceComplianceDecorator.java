package za.ac.unisa.myadmin.student.services.decorators;

import org.apache.commons.lang3.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.services.base.decorators.StudentAcademicRecordServiceDecorator;
import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;

import java.util.List;

/**
 * This decorator validates required-ness.
 */
public class StudentAcademicRecordServiceComplianceDecorator extends StudentAcademicRecordServiceDecorator implements StudentAcademicRecordService {

	@Override
	public List<StudentAcademicQualificationRecordInfo> requestStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (studentNumber == null) {
			throw new MissingParameterException("Please enter a valid student number.");
		}
		return getNextDecorator().requestStudentAcademicQualificationResults(studentNumber);
	}

	@Override
	public String requestStudentAcademicRecordEmail(Integer studentNumber, String academicQualificationCode, boolean isAttachMarks) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (studentNumber == null) {
			throw new MissingParameterException("Please enter a valid student number.");
		}
		if(StringUtils.isBlank(academicQualificationCode)){
			throw new OperationFailedException("Qualification is required.");
		}
		return getNextDecorator().requestStudentAcademicRecordEmail(studentNumber, academicQualificationCode, isAttachMarks);
	}
}
