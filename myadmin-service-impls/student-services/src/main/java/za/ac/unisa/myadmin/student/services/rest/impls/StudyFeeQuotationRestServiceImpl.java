package za.ac.unisa.myadmin.student.services.rest.impls;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.fees.services.dto.StudyFeeQuotationInfo;
import za.ac.unisa.myadmin.fees.services.dto.StudyFeeQuotationRequestInfo;
import za.ac.unisa.myadmin.fees.services.rest.StudyFeeQuotationRestService;
import za.ac.unisa.myadmin.services.base.decorators.StudyFeeQuotationServiceDecorator;

public class StudyFeeQuotationRestServiceImpl extends StudyFeeQuotationServiceDecorator
		implements StudyFeeQuotationRestService {

	@Override
	public StudyFeeQuotationInfo submitStudyFeeQuotationRequest(StudyFeeQuotationRequestInfo studyFeeQuotationRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().submitStudyFeeQuotationRequest(studyFeeQuotationRequest);
	}

}
