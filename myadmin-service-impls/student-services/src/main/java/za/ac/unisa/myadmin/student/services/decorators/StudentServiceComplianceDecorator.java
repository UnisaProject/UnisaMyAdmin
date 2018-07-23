package za.ac.unisa.myadmin.student.services.decorators;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.services.base.decorators.StudentServiceDecorator;
import za.ac.unisa.myadmin.student.services.StudentService;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

/**
 * This decorator validates requiredness.
 *
 * Created by Adrian on 2018-07-11.
 */
public class StudentServiceComplianceDecorator extends StudentServiceDecorator implements StudentService {

	@Override
	public List<StudentInfo> getStudentsBySurname(String surname) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(surname)) {
			throw new MissingParameterException("Please enter a valid surname.");
		}
		return getNextDecorator().getStudentsBySurname(surname);
	}

	@Override
	public List<StudentInfo> getStudentsByFirstNames(String firstNames) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(firstNames)) {
			throw new MissingParameterException("Please enter a valid first name(s).");
		}
		return getNextDecorator().getStudentsByFirstNames(firstNames);
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNames(String surname, String firstNames) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(surname)) {
			throw new MissingParameterException("Please enter a valid surname.");
		}
		if (!StringUtils.hasText(firstNames)) {
			throw new MissingParameterException("Please enter a valid first name(s).");
		}
		return getNextDecorator().getStudentsBySurnameAndFirstNames(surname, firstNames);
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
		return getNextDecorator().getStudentsBySurnameAndFirstNamesAndBirthDate(surname, firstNames, dateOfBirth);
	}

	@Override
	public List<StudentInfo> getStudentsByIdNumber(String identityNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(identityNumber)) {
			throw new MissingParameterException("Please enter a valid Identity Number.");
		}
		return getNextDecorator().getStudentsByIdNumber(identityNumber);
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
		return getNextDecorator().getStudentsBySurnameAndFirstNamesAndBirthDateAndIdNumber(surname, firstNames,
				dateOfBirth, identityNumber);
	}

	@Override
	public List<StudentInfo> getStudentsByPassportNumber(String passportNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (!StringUtils.hasText(passportNumber)) {
			throw new OperationFailedException("Please enter a valid Passport Number.");
		}
		return getNextDecorator().getStudentsByPassportNumber(passportNumber);
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
			throw new MissingParameterException("Please enter a valid Passport Number.");
		}
		return getNextDecorator().getStudentsBySurnameAndFirstNamesAndBirthDateAndPassportNumber(surname, firstNames,
				dateOfBirth, passportNumber);
	}
}
