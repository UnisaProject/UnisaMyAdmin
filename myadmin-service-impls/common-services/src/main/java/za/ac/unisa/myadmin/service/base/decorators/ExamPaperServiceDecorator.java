package za.ac.unisa.myadmin.service.base.decorators;

import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamPaperService;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperInfo;

public class ExamPaperServiceDecorator implements ExamPaperService {

	private ExamPaperService nextDecorator;

	public ExamPaperService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(ExamPaperService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	@Override
	public List<ExamPaperInfo> getExamPapersByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamPapersByYear(year);
	}

	@Override
	public List<ExamPaperInfo> getExamPapersByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamPapersByCourseCodes(courseCodes);
	}

	@Override
	public List<ExamPaperInfo> getExamPapersByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamPapersByYearAndCourseCodes(year, courseCodes);
	}

}
