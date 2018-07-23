package za.ac.unisa.myadmin.services.base.decorators;

import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.ModuleEnrolmentService;
import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

public class ModuleEnrolmentServiceDecorator implements ModuleEnrolmentService {

	private ModuleEnrolmentService nextDecorator;

	public ModuleEnrolmentService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(ModuleEnrolmentService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	@Override
	public ModuleEnrolmentInfo getStudentModuleEnrolmentByNumberAndYearAndPeriodAndModuleAndSemester(
			Integer academicYear, Integer academicPeriod, String studyUnitCode, Integer studentNumber,
			Integer semesterPeriod) throws MissingParameterException, InvalidParameterException,
			OperationFailedException, DoesNotExistException {
		return getNextDecorator().getStudentModuleEnrolmentByNumberAndYearAndPeriodAndModuleAndSemester(academicYear,
				academicPeriod, studyUnitCode, studentNumber, semesterPeriod);
	}

	@Override
	public List<ModuleEnrolmentInfo> getStudentModuleEnrolmentByStudentNumber(Integer studentNumber)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getStudentModuleEnrolmentByStudentNumber(studentNumber);
	}

	@Override
	public List<ModuleEnrolmentInfo> getStudentModuleEnrolmentByStudentNumberAndAcademicYear(Integer studentNumber,
			Integer academicYear)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getStudentModuleEnrolmentByStudentNumberAndAcademicYear(studentNumber, academicYear);
	}

	@Override
	public List<ModuleEnrolmentInfo> getModuleEnrolmentsByNumberAndYearAndSemesterInAndStatusIn(Integer studentNumber,
			Integer year, List<Integer> semesterList, List<String> statusCodesList)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getModuleEnrolmentsByNumberAndYearAndSemesterInAndStatusIn(studentNumber, year,
				semesterList, statusCodesList);
	}

	@Override
	public List<ModuleEnrolmentInfo> getStudentModuleEnrolments(StudentInfo student) throws MissingParameterException,
			InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getStudentModuleEnrolments(student);
	}

	@Override
	public List<ModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student)
			throws MissingParameterException, InvalidParameterException, OperationFailedException,
			DoesNotExistException {
		return getNextDecorator().requestStudentModuleEnrolments(student);
	}

}
