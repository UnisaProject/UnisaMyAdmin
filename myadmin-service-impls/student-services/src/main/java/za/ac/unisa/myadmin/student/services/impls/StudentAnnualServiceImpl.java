package za.ac.unisa.myadmin.student.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.dto.StudentAnnualInfo;
import za.ac.unisa.myadmin.student.services.models.StudentAnnualEntity;
import za.ac.unisa.myadmin.student.services.models.StudentAnnualEntityId;
import za.ac.unisa.myadmin.student.services.repositories.StudentAnnualRepository;
import za.ac.unisa.myadmin.student.services.student.StudentAnnualService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Adrian on 2018-07-02.
 */
@Service("StudentAnnualService")
public class StudentAnnualServiceImpl implements StudentAnnualService {

	@Autowired
	private StudentAnnualRepository studentAnnualRepository;

	@Override
	public StudentAnnualInfo getStudentAnnualByStudentNumberAndYearAndPeriod(Integer studentNumber, Integer academicYear, Integer academicPeriod) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		StudentAnnualEntityId studentAnnualEntityId = new StudentAnnualEntityId(studentNumber, academicYear, academicPeriod);
		Optional<StudentAnnualEntity> entity = studentAnnualRepository.findById(studentAnnualEntityId);
		//Optional<StudentAnnualEntity> entity = studentAnnualRepository.findByStudentNumberAndAcademicYearAndAcademicPeriod(studentNumber, academicYear, academicPeriod);
		if (entity.isPresent()) {
			return entity.get().toDto();
		} else {
			throw new DoesNotExistException(Integer.toString(studentNumber));
		}
	}

	@Override
	public List<StudentAnnualInfo> getStudentAnnualByStudentNumber(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return studentAnnualRepository.findByStudentNumber(studentNumber)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<StudentAnnualInfo> getStudentAnnualByStudentNumberOrderByYearDesc(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return studentAnnualRepository.findByStudentNumberOrderByAcademicYearDesc(studentNumber)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<StudentAnnualInfo> getStudentAnnualByStudentNumberAndYear(Integer studentNumber, Integer academicYear) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return studentAnnualRepository.findByStudentNumberAndAcademicYear(studentNumber, academicYear)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}
}
