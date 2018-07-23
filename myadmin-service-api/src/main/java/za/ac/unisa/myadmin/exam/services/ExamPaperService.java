package za.ac.unisa.myadmin.exam.services;

import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperInfo;

public interface ExamPaperService {

	public List<ExamPaperInfo> getExamPapersByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamPaperInfo> getExamPapersByCourseCodes(List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<ExamPaperInfo> getExamPapersByYearAndCourseCodes(Integer year, List<String> courseCodes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
