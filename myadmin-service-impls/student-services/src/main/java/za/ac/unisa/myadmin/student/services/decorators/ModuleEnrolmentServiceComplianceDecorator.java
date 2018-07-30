package za.ac.unisa.myadmin.student.services.decorators;

import org.apache.commons.lang3.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.services.base.decorators.ModuleEnrolmentServiceDecorator;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

import java.util.List;

/**
 * This decorator will check that the require inputs are set.
 */
public class ModuleEnrolmentServiceComplianceDecorator extends ModuleEnrolmentServiceDecorator {

	@Override
	public List<ModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student)
		throws MissingParameterException, InvalidParameterException, OperationFailedException,
		DoesNotExistException {

		if (student.getStudentNumber() == null) {
			throw new MissingParameterException("Please enter the student number.");
		}

		if (StringUtils.isBlank(student.getSurname())) {
			throw new MissingParameterException("Please enter the surname");
		}

		if (StringUtils.isBlank(student.getFirstNames())) {
			throw new MissingParameterException("Please enter full names");
		}

		if (student.getDateOfBirth() == null) {
			throw new MissingParameterException("Please enter year for date of birth");
		}

		return getNextDecorator().requestStudentModuleEnrolments(student);
	}
}
