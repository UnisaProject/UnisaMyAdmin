package za.ac.unisa.myadmin.service.base.decorators;

import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamPeriodService;
import za.ac.unisa.myadmin.exam.services.dto.ExamPeriodInfo;

public class ExamPeriodServiceDecorator implements ExamPeriodService {

	private ExamPeriodService nextDecorator;

	public ExamPeriodService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(ExamPeriodService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	@Override
	public ExamPeriodInfo getExamPeriod(Integer code) throws DoesNotExistException, MissingParameterException,
			InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamPeriod(code);
	}

	@Override
	public List<ExamPeriodInfo> getExamPeriods() throws OperationFailedException {
		return getNextDecorator().getExamPeriods();
	}

	@Override
	public List<ExamPeriodInfo> getExamPeriodsByCodes(List<Integer> codes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamPeriodsByCodes(codes);
	}

}
