package za.ac.unisa.myadmin.student.services.rest.impls;

import za.ac.unisa.myadmin.common.dto.ErrorInfo;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.qualification.services.dto.AcademicRecordEmailRequestInfo;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.services.base.decorators.StudentAcademicRecordServiceDecorator;
import za.ac.unisa.myadmin.student.services.rest.StudentAcademicRecordRestService;

import java.util.List;

public class StudentAcademicRecordRestServiceImpl extends StudentAcademicRecordServiceDecorator implements StudentAcademicRecordRestService {

	@Override
	public List<StudentAcademicQualificationRecordInfo> getStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getStudentAcademicQualificationResults(studentNumber);
	}

	@Override
	public ErrorInfo sendStudentAcademicRecordEmail(AcademicRecordEmailRequestInfo emailRequestInfo) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().sendStudentAcademicRecordEmail(emailRequestInfo);
	}
}
