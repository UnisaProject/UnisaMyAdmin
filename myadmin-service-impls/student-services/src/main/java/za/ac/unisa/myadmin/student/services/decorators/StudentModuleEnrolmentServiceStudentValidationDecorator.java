package za.ac.unisa.myadmin.student.services.decorators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentModuleEnrolmentInfo;
import za.ac.unisa.myadmin.student.services.student.StudentModuleEnrolmentService;
import za.ac.unisa.myadmin.student.services.student.StudentService;

import java.util.List;

/**
 * Decorator that validates student input before parsing
 * to real service
 * Created by Adrian on 2018-06-29.
 */
@Service("StudentModuleEnrolmentServiceStudentValidationDecorator")
public class StudentModuleEnrolmentServiceStudentValidationDecorator implements StudentModuleEnrolmentService {

	@Autowired
	@Qualifier("StudentModuleEnrolmentService")
	private StudentModuleEnrolmentService moduleEnrolmentService;

	@Autowired
	@Qualifier("StudentService")
	private StudentService studentService;

	@Override
	public StudentModuleEnrolmentInfo getStudentModuleEnrolmentByNumberAndYearAndPeriodAndModuleAndSemester(Integer academicYear, Integer academicPeriod, String studyUnitCode, Integer studentNumber, Integer semesterPeriod) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return moduleEnrolmentService.getStudentModuleEnrolmentByNumberAndYearAndPeriodAndModuleAndSemester(academicYear, academicPeriod, studyUnitCode, studentNumber, semesterPeriod);
	}

	@Override
	public List<StudentModuleEnrolmentInfo> getStudentModuleEnrolmentByStudentNumber(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return moduleEnrolmentService.getStudentModuleEnrolmentByStudentNumber(studentNumber);
	}

	@Override
	public List<StudentModuleEnrolmentInfo> getStudentModuleEnrolmentByStudentNumberAndAcademicYear(Integer studentNumber, Integer academicYear) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return moduleEnrolmentService.getStudentModuleEnrolmentByStudentNumberAndAcademicYear(studentNumber, academicYear);
	}

	@Override
	public List<StudentModuleEnrolmentInfo> getModuleEnrolmentsByNumberAndYearAndSemesterAndStatusIn(Integer studentNumber, Integer year, Integer semester, List<String> statusList) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return moduleEnrolmentService.getModuleEnrolmentsByNumberAndYearAndSemesterAndStatusIn(studentNumber, year, semester, statusList);
	}


	@Override
	public List<StudentModuleEnrolmentInfo> getStudentModuleEnrolments(StudentInfo student) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return moduleEnrolmentService.getStudentModuleEnrolments(student);
	}

	@Override
	public List<StudentModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
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
		return moduleEnrolmentService.requestStudentModuleEnrolments(student);
	}
}
