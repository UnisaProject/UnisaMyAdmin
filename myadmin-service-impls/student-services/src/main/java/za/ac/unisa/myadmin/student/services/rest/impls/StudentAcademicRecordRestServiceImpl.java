package za.ac.unisa.myadmin.student.services.rest.impls;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.StudentAcademicStudyUnitResultInfo;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.services.base.decorators.StudentAcademicRecordServiceDecorator;
import za.ac.unisa.myadmin.student.services.rest.StudentAcademicRecordRestService;

import java.util.List;

public class StudentAcademicRecordRestServiceImpl extends StudentAcademicRecordServiceDecorator implements StudentAcademicRecordRestService {

	@Override
	public List<StudentAcademicQualificationRecordInfo> requestStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().requestStudentAcademicQualificationResults(studentNumber);
	}

	@Override
	public List<StudentAcademicStudyUnitResultInfo> requestStudentAcademicModuleResults(Integer studentNumber, boolean isCreditsOnly, String selectedQualificationCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().requestStudentAcademicModuleResults(studentNumber, isCreditsOnly, selectedQualificationCode);
	}


}
