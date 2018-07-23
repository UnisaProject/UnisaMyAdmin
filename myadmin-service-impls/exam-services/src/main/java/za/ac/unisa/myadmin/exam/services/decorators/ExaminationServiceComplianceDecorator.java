package za.ac.unisa.myadmin.exam.services.decorators;

import java.util.List;
import java.util.stream.Collectors;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExaminationService;
import za.ac.unisa.myadmin.exam.services.dto.ExaminationInfo;
import za.ac.unisa.myadmin.services.base.decorators.ExaminationServiceDecorator;

/**
 * This decorator ensures that the method request parameters comply to the
 * service expectations. For example, the database service expects the course
 * codes to be all in upper case, so this decorator will change those values to
 * be all upper case.
 * 
 * @author Jannie Louwrens
 *
 */
public class ExaminationServiceComplianceDecorator extends ExaminationServiceDecorator implements ExaminationService {

	@Override
	public List<ExaminationInfo> getExaminationsByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		List<String> upperCaseCourseCodes = convertCodesToUpperCase(courseCodes);

		return getNextDecorator().getExaminationsByCourseCodes(upperCaseCourseCodes);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		List<String> upperCaseCourseCodes = convertCodesToUpperCase(courseCodes);

		return getNextDecorator().getExaminationsByYearAndCourseCodes(year, upperCaseCourseCodes);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByExamPeriodCodeAndCourseCodes(Integer examPeriodCode,
			List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		List<String> upperCaseCourseCodes = convertCodesToUpperCase(courseCodes);

		return getNextDecorator().getExaminationsByExamPeriodCodeAndCourseCodes(examPeriodCode, upperCaseCourseCodes);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYearAndExamPeriodCodeAndCourseCodes(Integer year,
			Integer examPeriodCode, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		List<String> upperCaseCourseCodes = convertCodesToUpperCase(courseCodes);

		return getNextDecorator().getExaminationsByYearAndExamPeriodCodeAndCourseCodes(year, examPeriodCode,
				upperCaseCourseCodes);
	}
	
	private List<String> convertCodesToUpperCase(List<String> courseCodes) {
		return courseCodes.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());
	}

}
