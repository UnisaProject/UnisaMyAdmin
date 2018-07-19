package za.ac.unisa.myadmin.services.base.decorators;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.fees.services.StudyFeeQuotationService;
import za.ac.unisa.myadmin.fees.services.dto.StudyFeeQuotationInfo;
import za.ac.unisa.myadmin.fees.services.dto.StudyFeeQuotationRequestInfo;

public class StudyFeeQuotationServiceDecorator implements StudyFeeQuotationService {

	private StudyFeeQuotationService nextDecorator;

	public StudyFeeQuotationService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(StudyFeeQuotationService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	@Override
	public StudyFeeQuotationInfo submitStudyFeeQuotationRequest(StudyFeeQuotationRequestInfo studyQuotationRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().submitStudyFeeQuotationRequest(studyQuotationRequest);
	}

}
