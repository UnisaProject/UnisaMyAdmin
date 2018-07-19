package za.ac.unisa.myadmin.fees.services;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.fees.services.dto.StudyFeeQuotationInfo;
import za.ac.unisa.myadmin.fees.services.dto.StudyFeeQuotationRequestInfo;

public interface StudyFeeQuotationService {

	/**
	 * Processes a request to get a quote of the study fees
	 * 
	 * @param request
	 *            The request parameters for the quotation
	 * @return A quotation for the requested inputs
	 * @throws MissingParameterException,
	 *             InvalidParameterException, OperationFailedException
	 */
	public StudyFeeQuotationInfo submitStudyFeeQuotationRequest(StudyFeeQuotationRequestInfo studyQuotationRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;
}
