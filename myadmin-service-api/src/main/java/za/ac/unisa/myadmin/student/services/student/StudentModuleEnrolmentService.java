package za.ac.unisa.myadmin.student.services.student;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentModuleEnrolmentInfo;

import java.util.List;

/**
 * Created by Adrian on 2018-06-26.
 */
public interface StudentModuleEnrolmentService {

	public StudentModuleEnrolmentInfo getStudentModuleEnrolmentByNumberAndYearAndPeriodAndModuleAndSemester(Integer academicYear, Integer academicPeriod, String studyUnitCode, Integer studentNumber, Integer semesterPeriod)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<StudentModuleEnrolmentInfo> getStudentModuleEnrolmentByStudentNumber(Integer studentNumber)
		throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<StudentModuleEnrolmentInfo> getStudentModuleEnrolmentByStudentNumberAndAcademicYear(Integer studentNumber, Integer academicYear)
		throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<StudentModuleEnrolmentInfo> getModuleEnrolmentsByNumberAndYearAndSemesterInAndStatusIn(Integer studentNumber, Integer year, List<Integer> semesterList, List<String> statusCodesList)
		throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<StudentModuleEnrolmentInfo> getStudentModuleEnrolments(StudentInfo student) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<StudentModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;
}
