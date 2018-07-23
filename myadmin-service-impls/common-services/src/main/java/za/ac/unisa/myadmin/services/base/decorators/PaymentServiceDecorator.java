package za.ac.unisa.myadmin.services.base.decorators;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.payment.services.PaymentService;
import za.ac.unisa.myadmin.payment.services.dto.ApplicationPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.CreditCardPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.NonTpPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.QualPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.SummaryInfo;
import za.ac.unisa.myadmin.payment.services.dto.TpPaymentInfo;

public class PaymentServiceDecorator implements PaymentService {

	private PaymentService nextDecorator;

	public PaymentService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(PaymentService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	@Override
	public CreditCardPaymentInfo processStudentInput(Integer userId) throws OperationFailedException {
		return getNextDecorator().processStudentInput(userId);
	}

	@Override
	public QualPaymentInfo processQualInput(Integer studentNumber, String qualCode) throws OperationFailedException {
		return getNextDecorator().processQualInput(studentNumber, qualCode);
	}

	@Override
	public SummaryInfo processApplicationPayment(ApplicationPaymentInfo paymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().processApplicationPayment(paymentInfo);
	}

	@Override
	public SummaryInfo processNonTpPayment(NonTpPaymentInfo paymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().processNonTpPayment(paymentInfo);
	}

	@Override
	public SummaryInfo processTpPayment(TpPaymentInfo paymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().processTpPayment(paymentInfo);
	}

}
