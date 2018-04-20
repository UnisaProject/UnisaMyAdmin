package za.ac.unisa.myadmin.exam.period;

import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;

public interface ExamPeriodService {

	public ExamPeriodInfo getExamPeriod(Integer code) throws DoesNotExistException, MissingParameterException,
			InvalidParameterException, OperationFailedException;

	public List<ExamPeriodInfo> getExamPeriods() throws OperationFailedException;

	public List<ExamPeriodInfo> getExamPeriodByCodes(List<Integer> codes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
