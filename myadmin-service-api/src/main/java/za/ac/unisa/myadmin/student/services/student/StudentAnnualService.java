package za.ac.unisa.myadmin.student.services.student;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.dto.StudentAnnualInfo;

import java.util.List;

/**
 * Created by Adrian on 2018-06-26.
 */
public interface StudentAnnualService {

	public StudentAnnualInfo getStudentAnnualByStudentNumberAndYearAndPeriod(Integer studentNumber, Integer academicYear, Integer academicPeriod)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<StudentAnnualInfo> getStudentAnnualByStudentNumber(Integer studentNumber)
		throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<StudentAnnualInfo> getStudentAnnualByStudentNumberOrderByYearDesc(Integer studentNumber)
		throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public StudentAnnualInfo getLatestStudentAnnualRecord(Integer studentNumber)
		throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<StudentAnnualInfo> getStudentAnnualByStudentNumberAndYear(Integer studentNumber, Integer academicYear)
		throws MissingParameterException, InvalidParameterException, OperationFailedException;
}
