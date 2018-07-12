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
import za.ac.unisa.myadmin.student.services.student.StudentService;

import java.util.Date;
import java.util.List;

/**
 * This decorator validates requiredness.
 *
 * Created by Adrian on 2018-07-11.
 */
@Service("StudentServiceComplianceDecorator")
public class StudentServiceComplianceDecorator implements StudentService {

	@Autowired
	@Qualifier("StudentService")
	private StudentService studentService;

	@Override
	public StudentInfo getStudentByNumber(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return studentService.getStudentByNumber(studentNumber);
	}

	@Override
	public String getSmartCardValue(Integer userId) throws OperationFailedException {
		return studentService.getSmartCardValue(userId);
	}

	@Override
	public int updateSmartCardValue(String smartCard, Integer studentNumber) {
		return studentService.updateSmartCardValue(smartCard, studentNumber);
	}

	@Override
	public List<StudentInfo> getStudentsBySurname(String surname) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(surname)) {
			throw new MissingParameterException("Please enter a valid surname.");
		}
		return studentService.getStudentsBySurname(surname);
	}

	@Override
	public List<StudentInfo> getStudentsByFirstNames(String firstNames) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(firstNames)) {
			throw new MissingParameterException("Please enter a valid first name(s).");
		}
		return studentService.getStudentsByFirstNames(firstNames);
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNames(String surname, String firstNames) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(surname)) {
			throw new MissingParameterException("Please enter a valid surname.");
		}
		if (!StringUtils.hasText(firstNames)) {
			throw new MissingParameterException("Please enter a valid first name(s).");
		}
		return studentService.getStudentsBySurnameAndFirstNames(surname, firstNames);
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNamesAndBirthDate(String surname, String firstNames, Date dateOfBirth) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(surname)) {
			throw new MissingParameterException("Please enter a valid surname.");
		}
		if (!StringUtils.hasText(firstNames)) {
			throw new MissingParameterException("Please enter a valid first name(s).");
		}
		if (dateOfBirth == null) {
			throw new MissingParameterException("Please enter a valid date of birth.");
		}
		return studentService.getStudentsBySurnameAndFirstNamesAndBirthDate(surname, firstNames, dateOfBirth);
	}

	@Override
	public List<StudentInfo> getStudentsByIdNumber(String identityNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(identityNumber)) {
			throw new MissingParameterException("Please enter a valid Identity Number.");
		}
		return studentService.getStudentsByIdNumber(identityNumber);
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNamesAndBirthDateAndIdNumber(String surname, String firstNames, Date dateOfBirth, String identityNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(surname)) {
			throw new MissingParameterException("Please enter a valid surname.");
		}
		if (!StringUtils.hasText(firstNames)) {
			throw new MissingParameterException("Please enter a valid first name(s).");
		}
		if (dateOfBirth == null) {
			throw new MissingParameterException("Please enter a valid date of birth.");
		}
		if (!StringUtils.hasText(identityNumber)) {
			throw new MissingParameterException("Please enter a valid Identity Number.");
		}
		return studentService.getStudentsBySurnameAndFirstNamesAndBirthDateAndIdNumber(surname, firstNames, dateOfBirth, identityNumber);
	}

	@Override
	public List<StudentInfo> getStudentsByPassportNumber(String passportNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(passportNumber)) {
			throw new OperationFailedException("Please enter a valid Passport Number.");
		}
		return studentService.getStudentsByPassportNumber(passportNumber);
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNamesAndBirthDateAndPassportNumber(String surname, String firstNames, Date dateOfBirth, String passportNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(surname)) {
			throw new MissingParameterException("Please enter a valid surname.");
		}
		if (!StringUtils.hasText(firstNames)) {
			throw new MissingParameterException("Please enter a valid first name(s).");
		}
		if (dateOfBirth == null) {
			throw new MissingParameterException("Please enter a valid date of birth.");
		}
		if (!StringUtils.hasText(passportNumber)) {
			throw new MissingParameterException("Please enter a valid Identity Number.");
		}
		return studentService.getStudentsBySurnameAndFirstNamesAndBirthDateAndPassportNumber(surname, firstNames, dateOfBirth, passportNumber);
	}
}
