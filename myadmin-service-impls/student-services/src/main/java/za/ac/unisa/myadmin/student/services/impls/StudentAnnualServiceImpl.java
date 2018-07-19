package za.ac.unisa.myadmin.student.services.impls;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.StudentAnnualService;
import za.ac.unisa.myadmin.student.services.dto.StudentAnnualInfo;
import za.ac.unisa.myadmin.student.services.jpa.models.StudentAnnualEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.StudentAnnualEntityId;
import za.ac.unisa.myadmin.student.services.repositories.StudentAnnualRepository;

/**
 * Created by Adrian on 2018-07-02.
 */
public class StudentAnnualServiceImpl implements StudentAnnualService {

	private StudentAnnualRepository studentAnnualRepository;

	public void setStudentAnnualRepository(StudentAnnualRepository studentAnnualRepository) {
		this.studentAnnualRepository = studentAnnualRepository;
	}

	@Override
	public StudentAnnualInfo getStudentAnnualByStudentNumberAndYearAndPeriod(Integer studentNumber, Integer academicYear, Integer academicPeriod) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		StudentAnnualEntityId studentAnnualEntityId = new StudentAnnualEntityId(studentNumber, academicYear, academicPeriod);
		Optional<StudentAnnualEntity> entity = studentAnnualRepository.findById(studentAnnualEntityId);
		if (entity.isPresent()) {
			return entity.get().toDto();
		} else {
			throw new DoesNotExistException("Student " + studentNumber + " not found for academic year " + academicYear + " and period " + academicPeriod);
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
	public StudentAnnualInfo getLatestStudentAnnualRecord(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Optional<StudentAnnualEntity> entity = studentAnnualRepository.findTopByStudentNumberOrderByAcademicYearDesc(studentNumber);
		if (entity.isPresent()) {
			return entity.get().toDto();
		} else {
			return null;
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
