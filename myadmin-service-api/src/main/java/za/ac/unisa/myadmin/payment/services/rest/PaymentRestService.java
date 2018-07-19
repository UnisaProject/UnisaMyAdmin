package za.ac.unisa.myadmin.payment.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.payment.services.dto.ApplicationPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.CreditCardPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.NonTpPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.QualPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.SummaryInfo;
import za.ac.unisa.myadmin.payment.services.dto.TpPaymentInfo;

@Path("/")
public interface PaymentRestService {

	@GET
	@Path("/payment/studentinput")
	@Produces("application/json")
	@Consumes("application/json")
	public CreditCardPaymentInfo processStudentInput(@QueryParam("studentNumber") Integer studentNumber,
			@Context UriInfo uriInfo) throws OperationFailedException;

	@GET
	@Path("/payment/qualinput")
	@Produces("application/json")
	@Consumes("application/json")
	public QualPaymentInfo processQualInput(@QueryParam("studentNumber") Integer studentNumber,
			@QueryParam("qualCode") String qualCode, @Context UriInfo uriInfo) throws OperationFailedException;

	@POST
	@Path("/payment/applyNonTPPayment")
	@Produces("application/json")
	@Consumes("application/json")
	public SummaryInfo processNonTpPayment(NonTpPaymentInfo nonTpPaymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	@POST
	@Path("/payment/applyTPPayment")
	@Produces("application/json")
	@Consumes("application/json")
	public SummaryInfo processTpPayment(TpPaymentInfo tpPaymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	@POST
	@Path("/payment/applyPayment")
	@Produces("application/json")
	@Consumes("application/json")
	public SummaryInfo processApplyPayment(ApplicationPaymentInfo applicationPaymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
