package za.ac.unisa.myadmin.exam.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamPaperService;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperInfo;
import za.ac.unisa.myadmin.exam.services.repositories.ExamPaperRepository;

public class ExamPaperServiceImpl implements ExamPaperService {

	private ExamPaperRepository examPaperRepository;

	public void setExamPaperRepository(ExamPaperRepository examPaperRepository) {
		this.examPaperRepository = examPaperRepository;
	}

	@Override
	public List<ExamPaperInfo> getExamPapersByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPaperRepository.findByYear(year).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamPaperInfo> getExamPapersByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPaperRepository.findByCourseCodeIn(courseCodes).stream().map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ExamPaperInfo> getExamPapersByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return examPaperRepository.findByYearAndCourseCodeIn(year, courseCodes).stream()
					.map(entity -> entity.toDto()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

}
