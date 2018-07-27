package za.ac.unisa.myadmin.services.base.decorators;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.AcademicModuleRecordService;
import za.ac.unisa.myadmin.module.services.dto.AcademicModuleRecordInfo;

import java.util.List;

public class AcademicModuleRecordServiceDecorator implements AcademicModuleRecordService {

	private AcademicModuleRecordService nextDecorator;

	public AcademicModuleRecordService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(AcademicModuleRecordService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	@Override
	public List<AcademicModuleRecordInfo> requestStudentAcademicModuleResults(Integer studentNumber, boolean isCreditsOnly, String selectedQualificationCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().requestStudentAcademicModuleResults(studentNumber, isCreditsOnly, selectedQualificationCode);
	}

}
