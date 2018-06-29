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

import java.util.Optional;

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
}