package za.ac.unisa.myadmin.student.services.rest.impls;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.module.services.rest.ModuleEnrolmentRestService;
import za.ac.unisa.myadmin.services.base.decorators.ModuleEnrolmentServiceDecorator;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

import java.util.List;

public class ModuleEnrolmentRestServiceImpl extends ModuleEnrolmentServiceDecorator
		implements ModuleEnrolmentRestService {



	@Override
	public List<ModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student)
			throws OperationFailedException, MissingParameterException, InvalidParameterException,
			DoesNotExistException {
		return getNextDecorator().requestStudentModuleEnrolments(student);
	}

}
