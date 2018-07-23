package za.ac.unisa.myadmin.exam.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExaminationService;
import za.ac.unisa.myadmin.exam.services.dto.ExaminationInfo;
import za.ac.unisa.myadmin.exam.services.repositories.ExaminationRepository;

public class ExaminationServiceImpl implements ExaminationService {

	private ExaminationRepository examinationRepository;

	public void setExaminationRepository(ExaminationRepository examinationRepository) {
		this.examinationRepository = examinationRepository;
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examinationRepository.findByYear(year).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExaminationInfo> getExaminationsByExamPeriodCode(Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examinationRepository.findByExamPeriodCode(examPeriodCode).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExaminationInfo> getExaminationsByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examinationRepository.findByCourseCodeIn(courseCodes).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYearAndExamPeriodCode(Integer year, Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examinationRepository.findByYearAndExamPeriodCode(year, examPeriodCode).stream()
					.map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examinationRepository.findByYearAndCourseCodeIn(year, courseCodes).stream()
					.map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExaminationInfo> getExaminationsByExamPeriodCodeAndCourseCodes(Integer examPeriodCode,
			List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examinationRepository.findByExamPeriodCodeAndCourseCodeIn(examPeriodCode, courseCodes).stream()
					.map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYearAndExamPeriodCodeAndCourseCodes(Integer year,
			Integer examPeriodCode, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examinationRepository.findByYearAndExamPeriodCodeAndCourseCodeIn(year, examPeriodCode, courseCodes)
					.stream().map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

}
