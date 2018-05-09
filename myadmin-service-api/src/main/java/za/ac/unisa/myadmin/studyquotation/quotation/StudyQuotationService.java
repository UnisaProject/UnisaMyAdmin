package za.ac.unisa.myadmin.studyquotation.quotation;

import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;

public interface StudyQuotationService {

	/**
	 * Processes a request to get a study quotation
	 * @param request The request parameters for the quotation
	 * @return A quotation for the requested inputs
	 * @throws OperationFailedException
	 */
	StudyQuotation calculateStudyQuotation(StudyQuotationRequest request) throws OperationFailedException;
}
