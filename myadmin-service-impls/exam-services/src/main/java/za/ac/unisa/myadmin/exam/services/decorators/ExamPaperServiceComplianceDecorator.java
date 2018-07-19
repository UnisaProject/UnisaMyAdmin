package za.ac.unisa.myadmin.exam.services.decorators;

import java.util.List;
import java.util.stream.Collectors;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamPaperService;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperInfo;
import za.ac.unisa.myadmin.services.base.decorators.ExamPaperServiceDecorator;

/**
 * This decorator ensures that the method request parameters comply to the
 * service expectations. For example, the database service expects the course
 * codes to be all in upper case, so this decorator will change those values to
 * be all upper case.
 * 
 * @author Jannie Louwrens
 *
 */
public class ExamPaperServiceComplianceDecorator extends ExamPaperServiceDecorator implements ExamPaperService {

	@Override
	public List<ExamPaperInfo> getExamPapersByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamPapersByYear(year);
	}

	@Override
	public List<ExamPaperInfo> getExamPapersByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		List<String> upperCaseCourseCodes = convertCodesToUpperCase(courseCodes);

		return getNextDecorator().getExamPapersByCourseCodes(upperCaseCourseCodes);
	}

	@Override
	public List<ExamPaperInfo> getExamPapersByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		List<String> upperCaseCourseCodes = convertCodesToUpperCase(courseCodes);

		return getNextDecorator().getExamPapersByYearAndCourseCodes(year, upperCaseCourseCodes);
	}

	private List<String> convertCodesToUpperCase(List<String> courseCodes) {
		return courseCodes.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());
	}

}
