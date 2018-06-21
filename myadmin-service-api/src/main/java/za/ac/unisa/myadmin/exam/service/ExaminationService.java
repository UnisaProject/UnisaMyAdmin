package za.ac.unisa.myadmin.exam.service;

import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.dto.ExaminationInfo;

public interface ExaminationService {

	public List<ExaminationInfo> getExaminationsByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExaminationInfo> getExaminationsByExamPeriodCode(Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExaminationInfo> getExaminationsByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExaminationInfo> getExaminationsByYearAndExamPeriodCode(Integer year, Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExaminationInfo> getExaminationsByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExaminationInfo> getExaminationsByExamPeriodCodeAndCourseCodes(Integer examPeriodCode,
			List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExaminationInfo> getExaminationsByYearAndExamPeriodCodeAndCourseCodes(Integer year,
			Integer examPeriodCode, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
