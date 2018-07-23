package za.ac.unisa.myadmin.services.base.decorators;

import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamAdmissionService;
import za.ac.unisa.myadmin.exam.services.dto.ExamAdmissionInfo;

public class ExamAdmissionServiceDecorator implements ExamAdmissionService {

	private ExamAdmissionService nextDecorator;

	public ExamAdmissionService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(ExamAdmissionService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissions() throws OperationFailedException {
		return getNextDecorator().getExamAdmissions();
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByYear(Integer year)
			throws InvalidParameterException, OperationFailedException, MissingParameterException {
		return getNextDecorator().getExamAdmissionsByYear(year);
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByExamPeriodCode(Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamAdmissionsByExamPeriodCode(examPeriodCode);
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByExamType(Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamAdmissionsByExamType(examType);
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByYearAndExamPeriodCode(Integer year, Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamAdmissionsByYearAndExamPeriodCode(year, examPeriodCode);
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByYearAndExamType(Integer year, Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamAdmissionsByYearAndExamType(year, examType);
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByExamPeriodCodeAndExamType(Integer examPeriodCode,
			Integer examType) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamAdmissionsByExamPeriodCodeAndExamType(examPeriodCode, examType);
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByYearAndExamPeriodCodeAndExamType(Integer year,
			Integer examPeriodCode, Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamAdmissionsByYearAndExamPeriodCodeAndExamType(year, examPeriodCode, examType);
	}

}
