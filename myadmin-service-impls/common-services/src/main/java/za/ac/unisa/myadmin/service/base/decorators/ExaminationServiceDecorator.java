package za.ac.unisa.myadmin.service.base.decorators;

import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExaminationService;
import za.ac.unisa.myadmin.exam.services.dto.ExaminationInfo;

public class ExaminationServiceDecorator implements ExaminationService {

	private ExaminationService nextDecorator;

	public ExaminationService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(ExaminationService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExaminationsByYear(year);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByExamPeriodCode(Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExaminationsByExamPeriodCode(examPeriodCode);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExaminationsByCourseCodes(courseCodes);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYearAndExamPeriodCode(Integer year, Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExaminationsByYearAndExamPeriodCode(year, examPeriodCode);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExaminationsByYearAndCourseCodes(year, courseCodes);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByExamPeriodCodeAndCourseCodes(Integer examPeriodCode,
			List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExaminationsByExamPeriodCodeAndCourseCodes(examPeriodCode, courseCodes);
	}

	@Override
	public List<ExaminationInfo> getExaminationsByYearAndExamPeriodCodeAndCourseCodes(Integer year,
			Integer examPeriodCode, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExaminationsByYearAndExamPeriodCodeAndCourseCodes(year, examPeriodCode,
				courseCodes);
	}

}
