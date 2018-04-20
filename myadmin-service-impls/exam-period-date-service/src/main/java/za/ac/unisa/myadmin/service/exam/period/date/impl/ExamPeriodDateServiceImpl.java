package za.ac.unisa.myadmin.service.exam.period.date.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.period.date.ExamPeriodDateInfo;
import za.ac.unisa.myadmin.exam.period.date.ExamPeriodDateService;
import za.ac.unisa.myadmin.service.exam.period.date.dao.ExamPeriodDateRepository;

@Service
public class ExamPeriodDateServiceImpl implements ExamPeriodDateService {

	@Autowired
	private ExamPeriodDateRepository examPeriodDateRepository;

	@Override
	public List<ExamPeriodDateInfo> getExamPeriodDatesByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPeriodDateRepository.findByYear(year).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamPeriodDateInfo> getExamPeriodDatesByExamPeriodCode(Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPeriodDateRepository.findByExamPeriodCode(examPeriodCode).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamPeriodDateInfo> getExamPeriodDatesByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPeriodDateRepository.findByCourseCodeIn(courseCodes).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamPeriodDateInfo> getExamPeriodDatesByYearAndExamPeriodCode(Integer year, Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPeriodDateRepository.findByYearAndExamPeriodCode(year, examPeriodCode).stream()
					.map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamPeriodDateInfo> getExamPeriodDatesByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPeriodDateRepository.findByYearAndCourseCodeIn(year, courseCodes).stream()
					.map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamPeriodDateInfo> getExamPeriodDatesByExamPeriodCodeAndCourseCodes(Integer examPeriodCode,
			List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPeriodDateRepository.findByExamPeriodCodeAndCourseCodeIn(examPeriodCode, courseCodes).stream()
					.map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamPeriodDateInfo> getExamPeriodDatesByYearAndExamPeriodCodeAndCourseCodes(Integer year,
			Integer examPeriodCode, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPeriodDateRepository
					.findByYearAndExamPeriodCodeAndCourseCodeIn(year, examPeriodCode, courseCodes).stream()
					.map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

}
