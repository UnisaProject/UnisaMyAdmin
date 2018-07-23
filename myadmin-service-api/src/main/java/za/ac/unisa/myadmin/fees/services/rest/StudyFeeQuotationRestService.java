package za.ac.unisa.myadmin.fees.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.fees.services.dto.StudyFeeQuotationInfo;
import za.ac.unisa.myadmin.fees.services.dto.StudyFeeQuotationRequestInfo;

@Path("/")
public interface StudyFeeQuotationRestService {

	@POST
	@Path("/quotationrequest/submission")
	@Produces("application/json")
	@Consumes("application/json")
	public StudyFeeQuotationInfo submitStudyFeeQuotationRequest(StudyFeeQuotationRequestInfo studyFeeQuotationRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
