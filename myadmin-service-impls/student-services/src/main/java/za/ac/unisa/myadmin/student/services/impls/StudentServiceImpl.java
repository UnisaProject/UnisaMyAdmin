package za.ac.unisa.myadmin.student.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.student.services.models.StudentEntity;
import za.ac.unisa.myadmin.student.services.repositories.StudentRepository;
import za.ac.unisa.myadmin.student.services.student.StudentService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Adrian on 2018-06-26.
 */
@Service("StudentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public StudentInfo getStudentByNumber(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		Optional<StudentEntity> entity = studentRepository.findById(studentNumber);
		if (entity.isPresent()) {
			return entity.get().toDto();
		} else {
			throw new DoesNotExistException(Integer.toString(studentNumber));
		}
	}

	@Override
	public String getSmartCardValue(Integer userId) throws OperationFailedException {
		return studentRepository.getSmartCardIssuedByStudentNumber(userId);
	}

	@Override
	@Transactional
	public int updateSmartCardValue(String smartCard, Integer studentNumber) {
		return studentRepository.updatesmartCardIssuedByStudentNumber(smartCard, studentNumber);
	}

	@Override
	public List<StudentInfo> getStudentsBySurname(String surname) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		try {
			return studentRepository.findBySurnameIgnoreCase(surname)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DoesNotExistException("Student number not found. Check that your details have been entered correctly. If correct, please contact study-info@unisa.ac.za for assistance.", e);
		}
	}

	@Override
	public List<StudentInfo> getStudentsByFirstNames(String firstNames) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		try {
			return studentRepository.findByFirstNamesIgnoreCase(firstNames)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DoesNotExistException("Student number not found. Check that your details have been entered correctly. If correct, please contact study-info@unisa.ac.za for assistance.", e);
		}
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNames(String surname, String firstNames) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		try {
			return studentRepository.findBySurnameAndFirstNamesAllIgnoreCase(surname, firstNames)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DoesNotExistException("Student number not found. Check that your details have been entered correctly. If correct, please contact study-info@unisa.ac.za for assistance.", e);
		}
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNamesAndBirthDate(String surname, String firstNames, Date dateOfBirth) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		try {
			return studentRepository.findBySurnameAndFirstNamesAndBirthDateAllIgnoreCase(surname, firstNames, dateOfBirth)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DoesNotExistException("Student number not found. Check that your details have been entered correctly. If correct, please contact study-info@unisa.ac.za for assistance.", e);
		}
	}

	@Override
	public List<StudentInfo> getStudentsByIdNumber(String identityNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		try {
			return studentRepository.findByIdentityNumberIgnoreCase(identityNumber)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DoesNotExistException("Student number not found. Check that your details have been entered correctly. If correct, please contact study-info@unisa.ac.za for assistance.", e);
		}
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNamesAndBirthDateAndIdNumber(String surname, String firstNames, Date dateOfBirth, String identityNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		try {
			return studentRepository.findBySurnameAndFirstNamesAndBirthDateAndIdentityNumberAllIgnoreCase(surname, firstNames, dateOfBirth, identityNumber)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DoesNotExistException("There is more than one student number that complies with the information submitted. Please contact study-info@unisa.ac.za for assistance.", e);
		}
	}

	@Override
	public List<StudentInfo> getStudentsByPassportNumber(String passportNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		try {
			return studentRepository.findByPassportNumberIgnoreCase(passportNumber)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DoesNotExistException("There is more than one student number that complies with the information submitted. Please contact study-info@unisa.ac.za for assistance.", e);
		}
	}

	@Override
	public List<StudentInfo> getStudentsBySurnameAndFirstNamesAndBirthDateAndPassportNumber(String surname, String firstNames, Date dateOfBirth, String passportNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		try {
			return studentRepository.findBySurnameAndFirstNamesAndBirthDateAndPassportNumberAllIgnoreCase(surname, firstNames, dateOfBirth, passportNumber)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new DoesNotExistException("There is more than one student number that complies with the information submitted. Please contact study-info@unisa.ac.za for assistance.", e);
		}
	}
}