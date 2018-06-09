package za.ac.unisa.myadmin.exam.service.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.dto.ExamAdmissionInfo;
import za.ac.unisa.myadmin.exam.service.ExamAdmissionService;
import za.ac.unisa.myadmin.exam.service.repositories.ExamAdmissionRepository;

@Service("ExamAdmissionService")
public class ExamAdmissionServiceImpl implements ExamAdmissionService {

	@Autowired
	private ExamAdmissionRepository examAdmissionRepository;

	@Override
	public List<ExamAdmissionInfo> getExamAdmissions() throws OperationFailedException {
		try {
			return examAdmissionRepository.findAll().stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examAdmissionRepository.findByYear(year).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByExamPeriodCode(Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examAdmissionRepository.findByExamPeriodCode(examPeriodCode).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByExamType(Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examAdmissionRepository.findByExamType(examType).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByYearAndExamPeriodCode(Integer year, Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examAdmissionRepository.findByYearAndExamPeriodCode(year, examPeriodCode).stream()
					.map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByYearAndExamType(Integer year, Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examAdmissionRepository.findByYearAndExamType(year, examType).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByExamPeriodCodeAndExamType(Integer examPeriodCode,
			Integer examType) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examAdmissionRepository.findByExamPeriodCodeAndExamType(examPeriodCode, examType).stream()
					.map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByYearAndExamPeriodCodeAndExamType(Integer year,
			Integer examPeriodCode, Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examAdmissionRepository.findByYearAndExamPeriodCodeAndExamType(year, examPeriodCode, examType)
					.stream().map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

}
