package za.ac.unisa.myadmin.exam.paper.details;

import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;

public interface ExamPaperDetailsService {

	public List<ExamPaperDetailsInfo> getPaperDetailsByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamPaperDetailsInfo> getPaperDetailsByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamPaperDetailsInfo> getPaperDetailsByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
