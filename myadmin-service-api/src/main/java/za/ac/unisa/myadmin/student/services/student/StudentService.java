package za.ac.unisa.myadmin.student.services.student;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

/**
 * Created by Adrian on 2018-06-26.
 */
public interface StudentService {

	public StudentInfo getStudentByNumber(Integer studentNumber)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public String getSmartCardValue(Integer userId) throws OperationFailedException;

	public int updateSmartCardValue(String smartCard, Integer studentNumber);

}
