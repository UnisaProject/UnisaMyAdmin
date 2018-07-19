package za.ac.unisa.myadmin.payment.services.rest.impls;

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
import za.ac.unisa.myadmin.payment.services.rest.PaymentRestService;
import za.ac.unisa.myadmin.services.base.decorators.PaymentServiceDecorator;

/**
 * Created by Adrian on 2018-06-04.
 */
public class PaymentRestServiceImpl extends PaymentServiceDecorator implements PaymentRestService {

	@Override
	public CreditCardPaymentInfo processStudentInput(Integer studentNumber, UriInfo uriInfo)
			throws OperationFailedException {
		return getNextDecorator().processStudentInput(studentNumber);
	}

	@Override
	public QualPaymentInfo processQualInput(Integer studentNumber, String qualCode, UriInfo uriInfo)
			throws OperationFailedException {
		return getNextDecorator().processQualInput(studentNumber, qualCode);
	}

	@Override
	public SummaryInfo processNonTpPayment(NonTpPaymentInfo nonTpPaymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().processNonTpPayment(nonTpPaymentInfo);
	}

	@Override
	public SummaryInfo processTpPayment(TpPaymentInfo tpPaymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().processTpPayment(tpPaymentInfo);
	}

	@Override
	public SummaryInfo processApplyPayment(ApplicationPaymentInfo applicationPaymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().processApplicationPayment(applicationPaymentInfo);
	}

}
