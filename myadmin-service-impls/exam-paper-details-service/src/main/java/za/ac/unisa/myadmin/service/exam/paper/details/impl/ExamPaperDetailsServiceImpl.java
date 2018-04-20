package za.ac.unisa.myadmin.service.exam.paper.details.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.paper.details.ExamPaperDetailsInfo;
import za.ac.unisa.myadmin.exam.paper.details.ExamPaperDetailsService;
import za.ac.unisa.myadmin.service.exam.paper.details.dao.ExamPaperDetailsRepository;

@Service
public class ExamPaperDetailsServiceImpl implements ExamPaperDetailsService {

	@Autowired
	private ExamPaperDetailsRepository examPaperDetailsRepository;

	@Override
	public List<ExamPaperDetailsInfo> getPaperDetailsByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPaperDetailsRepository.findByYear(year).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamPaperDetailsInfo> getPaperDetailsByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPaperDetailsRepository.findByCourseCodeIn(courseCodes).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamPaperDetailsInfo> getPaperDetailsByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPaperDetailsRepository.findByYearAndCourseCodeIn(year, courseCodes).stream()
					.map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

}
