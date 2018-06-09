package za.ac.unisa.myadmin.exam.service;

import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.dto.ExamAdmissionInfo;

public interface ExamAdmissionService {

	public List<ExamAdmissionInfo> getExamAdmissions() throws OperationFailedException;

	public List<ExamAdmissionInfo> getExamAdmissionsByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamAdmissionInfo> getExamAdmissionsByExamPeriodCode(Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamAdmissionInfo> getExamAdmissionsByExamType(Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamAdmissionInfo> getExamAdmissionsByYearAndExamPeriodCode(Integer year, Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamAdmissionInfo> getExamAdmissionsByYearAndExamType(Integer year, Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamAdmissionInfo> getExamAdmissionsByExamPeriodCodeAndExamType(Integer examPeriodCode,
			Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamAdmissionInfo> getExamAdmissionsByYearAndExamPeriodCodeAndExamType(Integer year,
			Integer examPeriodCode, Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
