package za.ac.unisa.myadmin.student.services.decorators;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.AcademicModuleRecordService;
import za.ac.unisa.myadmin.module.services.dto.AcademicModuleRecordInfo;
import za.ac.unisa.myadmin.services.base.decorators.AcademicModuleRecordServiceDecorator;

import java.util.List;

/**
 * Created by Adrian on 2018-07-27.
 */
public class AcademicModuleRecordServiceValidationDecorator extends AcademicModuleRecordServiceDecorator implements AcademicModuleRecordService {

	@Override
	public List<AcademicModuleRecordInfo> getAcademicModules(Integer studentNumber, boolean isCreditsOnly, String selectedQualificationCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (String.valueOf(studentNumber).length() < 7) {
			throw new OperationFailedException("Student Number can not be less than 7 characters");
		}
		if (String.valueOf(studentNumber).length() > 8) {
			throw new OperationFailedException("Student Number can not be greater than 8 characters");
		}
		return getNextDecorator().getAcademicModules(studentNumber, isCreditsOnly, selectedQualificationCode);
	}
}
