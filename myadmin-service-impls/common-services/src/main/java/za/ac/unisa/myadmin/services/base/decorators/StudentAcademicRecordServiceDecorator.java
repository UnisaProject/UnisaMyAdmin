package za.ac.unisa.myadmin.services.base.decorators;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;

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
	public List<StudentAcademicQualificationRecordInfo> requestStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().requestStudentAcademicQualificationResults(studentNumber);
	}


}
