package za.ac.unisa.myadmin.student.services.decorators;

import org.springframework.util.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.StudentAcademicStudyUnitResultInfo;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.services.base.decorators.StudentAcademicRecordServiceDecorator;
import za.ac.unisa.myadmin.student.services.StudentAcademicRecordService;

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
	public List<StudentAcademicStudyUnitResultInfo> requestStudentAcademicModuleResults(Integer studentNumber, boolean isCreditsOnly, String selectedQualificationCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (studentNumber == null) {
			throw new MissingParameterException("Please enter a valid student number.");
		}
		if (!StringUtils.hasText(selectedQualificationCode)) {
			throw new MissingParameterException("Please enter a valid qualification code.");
		}
		return getNextDecorator().requestStudentAcademicModuleResults(studentNumber, isCreditsOnly, selectedQualificationCode);
	}
}
