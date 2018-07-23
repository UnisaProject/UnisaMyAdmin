package za.ac.unisa.myadmin.student.services.decorators;

import java.util.List;

import org.springframework.util.StringUtils;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.ModuleEnrolmentService;
import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.services.base.decorators.ModuleEnrolmentServiceDecorator;
import za.ac.unisa.myadmin.student.services.StudentService;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

/**
 * Decorator that validates student input before parsing
 * to real service
 * Created by Adrian on 2018-06-29.
 */
public class ModuleEnrolmentServiceValidationDecorator extends ModuleEnrolmentServiceDecorator
		implements ModuleEnrolmentService {

	private StudentService studentService;

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public List<ModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student)
			throws MissingParameterException, InvalidParameterException, OperationFailedException,
			DoesNotExistException {
		if (!StringUtils.hasText(String.valueOf(student.getStudentNumber()))) {
			throw new OperationFailedException("Please enter the student number.");
		}
		if (String.valueOf(student.getStudentNumber()).length() < 7) {
			throw new OperationFailedException("The student number you have entered is not valid. Please check and try again.");
		}
		try {
			StudentInfo databaseStudent = studentService.getStudentByNumber(student.getStudentNumber());
		} catch (DoesNotExistException e) {
			throw new DoesNotExistException("The student number you have entered is not valid. Please check and try again.");
		}
		return getNextDecorator().requestStudentModuleEnrolments(student);
	}
}
