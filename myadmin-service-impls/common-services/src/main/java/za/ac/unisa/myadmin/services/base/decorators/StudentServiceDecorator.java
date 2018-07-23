package za.ac.unisa.myadmin.services.base.decorators;

import java.util.Date;
import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.StudentService;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

public class StudentServiceDecorator implements StudentService {

	private StudentService nextDecorator;

	public StudentService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(StudentService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	@Override
	public StudentInfo getStudentByNumber(Integer studentNumber) throws MissingParameterException,
			InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getStudentByNumber(studentNumber);
	}

	@Override
	public String getSmartCardValue(Integer userId) throws OperationFailedException {
		return getNextDecorator().getSmartCardValue(userId);
	}

	@Override
	public int updateSmartCardValue(String smartCard, Integer studentNumber) throws OperationFailedException {
		return getNextDecorator().updateSmartCardValue(smartCard, studentNumber);
	}

	@Override
	public List<StudentInfo> getStudentsBySurname(String surname) throws MissingParameterException,
			InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getStudentsBySurname(surname);
	}

	@Override
	public List<StudentInfo> getStudentsByFirstNames(String firstNames) throws MissingParameterException,
			InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getStudentsByFirstNames(firstNames);
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNames(String surname, String firstNames)
			throws MissingParameterException, InvalidParameterException, OperationFailedException,
			DoesNotExistException {
		return getNextDecorator().getStudentsBySurnameAndFirstNames(surname, firstNames);
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNamesAndBirthDate(String surname, String firstNames,
			Date dateOfBirth) throws MissingParameterException, InvalidParameterException, OperationFailedException,
			DoesNotExistException {
		return getNextDecorator().getStudentsBySurnameAndFirstNamesAndBirthDate(surname, firstNames, dateOfBirth);
	}

	@Override
	public List<StudentInfo> getStudentsByIdNumber(String identityNumber) throws MissingParameterException,
			InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getStudentsByIdNumber(identityNumber);
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNamesAndBirthDateAndIdNumber(String surname, String firstNames,
			Date dateOfBirth, String identityNumber) throws MissingParameterException, InvalidParameterException,
			OperationFailedException, DoesNotExistException {
		return getNextDecorator().getStudentsBySurnameAndFirstNamesAndBirthDateAndIdNumber(surname, firstNames,
				dateOfBirth, identityNumber);
	}

	@Override
	public List<StudentInfo> getStudentsByPassportNumber(String passportNumber) throws MissingParameterException,
			InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getStudentsByPassportNumber(passportNumber);
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNamesAndBirthDateAndPassportNumber(String surname,
			String firstNames, Date dateOfBirth, String passportNumber) throws MissingParameterException,
			InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getStudentsBySurnameAndFirstNamesAndBirthDateAndPassportNumber(surname, firstNames,
				dateOfBirth, passportNumber);
	}

}
