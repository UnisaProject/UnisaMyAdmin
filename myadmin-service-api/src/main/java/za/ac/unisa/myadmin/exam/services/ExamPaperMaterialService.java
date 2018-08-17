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
	 * Get available exam paper material for the specified module code.
	 * @param moduleCode
	 * @return
	 */
	List<ExamPaperMaterialInfo> getExamPapersByModuleCode(String moduleCode)
		throws MissingParameterException, InvalidParameterException, OperationFailedException;
}
