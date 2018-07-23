package za.ac.unisa.myadmin.student.services;

import java.util.Date;
import java.util.List;

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

	public int updateSmartCardValue(String smartCard, Integer studentNumber) throws OperationFailedException;

	public List<StudentInfo> getStudentsBySurname(String surname)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<StudentInfo> getStudentsByFirstNames(String firstNames)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<StudentInfo> getStudentsBySurnameAndFirstNames(String surname, String firstNames)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<StudentInfo> getStudentsBySurnameAndFirstNamesAndBirthDate(String surname, String firstNames, Date dateOfBirth)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<StudentInfo> getStudentsByIdNumber(String identityNumber)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<StudentInfo> getStudentsBySurnameAndFirstNamesAndBirthDateAndIdNumber(String surname, String firstNames, Date dateOfBirth, String identityNumber)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<StudentInfo> getStudentsByPassportNumber(String passportNumber)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<StudentInfo> getStudentsBySurnameAndFirstNamesAndBirthDateAndPassportNumber(String surname, String firstNames, Date dateOfBirth, String passportNumber)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

}
