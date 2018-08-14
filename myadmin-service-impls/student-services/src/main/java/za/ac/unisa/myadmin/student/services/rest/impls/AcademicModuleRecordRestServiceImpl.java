package za.ac.unisa.myadmin.student.services.rest.impls;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.AcademicModuleRecordInfo;
import za.ac.unisa.myadmin.module.services.rest.AcademicModuleRecordRestService;
import za.ac.unisa.myadmin.services.base.decorators.AcademicModuleRecordServiceDecorator;

import java.util.List;

public class AcademicModuleRecordRestServiceImpl extends AcademicModuleRecordServiceDecorator implements AcademicModuleRecordRestService {

	@Override
	public List<AcademicModuleRecordInfo> requestStudentAcademicModuleResults(Integer studentNumber, boolean isCreditsOnly, String selectedQualificationCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().requestStudentAcademicModuleResults(studentNumber, isCreditsOnly, selectedQualificationCode);
	}

}
