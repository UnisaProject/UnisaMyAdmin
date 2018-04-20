package za.ac.unisa.myadmin.exam.period.date;

import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;

public interface ExamPeriodDateService {

	public List<ExamPeriodDateInfo> getExamPeriodDatesByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamPeriodDateInfo> getExamPeriodDatesByExamPeriodCode(Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamPeriodDateInfo> getExamPeriodDatesByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamPeriodDateInfo> getExamPeriodDatesByYearAndExamPeriodCode(Integer year, Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamPeriodDateInfo> getExamPeriodDatesByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamPeriodDateInfo> getExamPeriodDatesByExamPeriodCodeAndCourseCodes(Integer examPeriodCode,
			List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamPeriodDateInfo> getExamPeriodDatesByYearAndExamPeriodCodeAndCourseCodes(Integer year,
			Integer examPeriodCode, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
