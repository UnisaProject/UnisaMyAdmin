package za.ac.unisa.myadmin.exam.service.decorators;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.dto.ExamPaperInfo;
import za.ac.unisa.myadmin.exam.service.ExamPaperService;

/**
 * This decorator ensures that the method request parameters comply to the
 * service expectations. For example, the database service expects the course
 * codes to be all in upper case, so this decorator will change those values to
 * be all upper case.
 * 
 * @author Jannie
 *
 */
@Service("ExamPaperServiceComplianceDecorator")
public class ExamPaperServiceComplianceDecorator implements ExamPaperService {

	@Autowired
	@Qualifier("ExamPaperService")
	private ExamPaperService examPaperService;

	@Override
	public List<ExamPaperInfo> getExamPapersByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return examPaperService.getExamPapersByYear(year);
	}

	@Override
	public List<ExamPaperInfo> getExamPapersByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		courseCodes = courseCodes.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());

		return examPaperService.getExamPapersByCourseCodes(courseCodes);
	}

	@Override
	public List<ExamPaperInfo> getExamPapersByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		courseCodes = courseCodes.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());

		return examPaperService.getExamPapersByYearAndCourseCodes(year, courseCodes);
	}

}
