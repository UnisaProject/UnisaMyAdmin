package za.ac.unisa.myadmin.module.services;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

import java.util.List;

/**
 * Created by Adrian on 2018-06-26.
 */
public interface ModuleEnrolmentService {

	public ModuleEnrolmentInfo getStudentModuleEnrolmentByNumberAndYearAndPeriodAndModuleAndSemester(Integer academicYear, Integer academicPeriod, String studyUnitCode, Integer studentNumber, Integer semesterPeriod)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<ModuleEnrolmentInfo> getStudentModuleEnrolmentByStudentNumber(Integer studentNumber)
		throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ModuleEnrolmentInfo> getStudentModuleEnrolmentByStudentNumberAndAcademicYear(Integer studentNumber, Integer academicYear)
		throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ModuleEnrolmentInfo> getModuleEnrolmentsByNumberAndYearAndSemesterInAndStatusIn(Integer studentNumber, Integer year, List<Integer> semesterList, List<String> statusCodesList)
		throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ModuleEnrolmentInfo> getStudentModuleEnrolments(StudentInfo student) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<ModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;
}
