package za.ac.unisa.myadmin.exam.service.decorators;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.dto.ExaminationInfo;
import za.ac.unisa.myadmin.exam.service.ExaminationService;

/**
 * This decorator ensures that the method request parameters comply to the
 * service expectations. For example, the database service expects the course
 * codes to be all in upper case, so this decorator will change those values to
 * be all upper case.
 * 
 * @author Jannie
 *
 */
@Service("ExaminationServiceComplianceDecorator")
public class ExaminationServiceComplianceDecorator implements ExaminationService {

	@Autowired
	@Qualifier("ExaminationService")
	private ExaminationService examinationService;

	@Override
	public List<ExaminationInfo> getExaminationsByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return examinationService.getExaminationsByYear(year);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByExamPeriodCode(Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return examinationService.getExaminationsByExamPeriodCode(examPeriodCode);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		courseCodes = courseCodes.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());

		return examinationService.getExaminationsByCourseCodes(courseCodes);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYearAndExamPeriodCode(Integer year, Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return examinationService.getExaminationsByYearAndExamPeriodCode(year, examPeriodCode);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		courseCodes = courseCodes.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());

		return examinationService.getExaminationsByYearAndCourseCodes(year, courseCodes);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByExamPeriodCodeAndCourseCodes(Integer examPeriodCode,
			List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		courseCodes = courseCodes.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());

		return examinationService.getExaminationsByExamPeriodCodeAndCourseCodes(examPeriodCode, courseCodes);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYearAndExamPeriodCodeAndCourseCodes(Integer year,
			Integer examPeriodCode, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		courseCodes = courseCodes.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());

		return examinationService.getExaminationsByYearAndExamPeriodCodeAndCourseCodes(year, examPeriodCode,
				courseCodes);
	}

}
