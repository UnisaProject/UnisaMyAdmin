package za.ac.unisa.myadmin.student.services.decorators;

import org.springframework.util.StringUtils;
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
public class AcademicModuleRecordServiceComplianceDecorator extends AcademicModuleRecordServiceDecorator implements AcademicModuleRecordService {

	@Override
	public List<AcademicModuleRecordInfo> getAcademicModules(Integer studentNumber, boolean isCreditsOnly, String selectedQualificationCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (studentNumber == null) {
			throw new MissingParameterException("Please enter a valid student number.");
		}
		if (!StringUtils.hasText(selectedQualificationCode)) {
			throw new MissingParameterException("Please enter a valid qualification code.");
		}
		return getNextDecorator().getAcademicModules(studentNumber, isCreditsOnly, selectedQualificationCode);
	}
}
