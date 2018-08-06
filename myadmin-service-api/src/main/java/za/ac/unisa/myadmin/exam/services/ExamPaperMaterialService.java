package za.ac.unisa.myadmin.exam.services;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperMaterialInfo;

import java.util.List;

/**
 * Service to retrieve material for previous exam papers.
 */
public interface ExamPaperMaterialService {


	/**
	 * Get available exam paper material for the specified course code.
	 * @param courseCode
	 * @return
	 */
	List<ExamPaperMaterialInfo> getExamPapersByCourseCode(String courseCode)
		throws MissingParameterException, InvalidParameterException, OperationFailedException;
}
