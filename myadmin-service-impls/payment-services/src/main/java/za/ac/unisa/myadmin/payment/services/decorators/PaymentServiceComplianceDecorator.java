package za.ac.unisa.myadmin.payment.services.decorators;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.payment.services.PaymentService;
import za.ac.unisa.myadmin.payment.services.dto.ApplicationPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.NonTpPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.SummaryInfo;
import za.ac.unisa.myadmin.payment.services.dto.TpPaymentInfo;
import za.ac.unisa.myadmin.services.base.decorators.PaymentServiceDecorator;

public class PaymentServiceComplianceDecorator extends PaymentServiceDecorator implements PaymentService {

	@Override
	public SummaryInfo processApplicationPayment(ApplicationPaymentInfo paymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		if (paymentInfo.getCreditCardTotalAmountInput() == null) {
			throw new MissingParameterException("creditCardTotalAmountInput is required");
		}
		if (paymentInfo.getCardInfo() == null) {
			throw new MissingParameterException("creditCardInfo is required");
		}
		if (paymentInfo.getCardInfo().getCardNumber() == null) {
			throw new MissingParameterException("creditCardNumber is required");
		}
		if (paymentInfo.getCardInfo().getCardHolder() == null) {
			throw new MissingParameterException("creditCardHolder is required");
		}
		if (paymentInfo.getCardInfo().getCvvNo() == null) {
			throw new MissingParameterException("creditCardCVV is required");
		}
		if (paymentInfo.getStudentInfo() == null) {
			throw new MissingParameterException("studentInfo is required");
		}
		if (paymentInfo.getStudentInfo().getStudentNumber() == null) {
			throw new MissingParameterException("studentNumber is required");
		}
		if (paymentInfo.getStudentInfo().getEmailAddress() == null) {
			throw new MissingParameterException("studentEmailAddress is required");
		}

		return getNextDecorator().processApplicationPayment(paymentInfo);
	}

	@Override
	public SummaryInfo processNonTpPayment(NonTpPaymentInfo paymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		if (paymentInfo.getCreditCardTotalAmountInput() == null) {
			throw new MissingParameterException("creditCardTotalAmountInput is required");
		}
		if (paymentInfo.getStudyFeeAmount() == null) {
			throw new MissingParameterException("studyFeeAmount is required");
		}
		if (paymentInfo.getCreditCardInfo() == null) {
			throw new MissingParameterException("creditCardInfo is required");
		}
		if (paymentInfo.getCreditCardInfo().getCardNumber() == null) {
			throw new MissingParameterException("creditCardNumber is required");
		}
		if (paymentInfo.getCreditCardInfo().getCardHolder() == null) {
			throw new MissingParameterException("creditCardHolder is required");
		}
		if (paymentInfo.getCreditCardInfo().getCvvNo() == null) {
			throw new MissingParameterException("creditCardCVV is required");
		}
		if (paymentInfo.getStudentInfo() == null) {
			throw new MissingParameterException("studentInfo is required");
		}
		if (paymentInfo.getStudentInfo().getStudentNumber() == null) {
			throw new MissingParameterException("studentNumber is required");
		}
		if (paymentInfo.getStudentInfo().getEmailAddress() == null) {
			throw new MissingParameterException("studentEmailAddress is required");
		}

		return getNextDecorator().processNonTpPayment(paymentInfo);
	}

	@Override
	public SummaryInfo processTpPayment(TpPaymentInfo paymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		if (paymentInfo.getCreditCardTotalAmountInput() == null) {
			throw new MissingParameterException("creditCardTotalAmountInput is required");
		}
		if (paymentInfo.getStudyFeeAmount() == null) {
			throw new MissingParameterException("studyFeeAmount is required");
		}
		if (paymentInfo.getCreditCardInfo() == null) {
			throw new MissingParameterException("creditCardInfo is required");
		}
		if (paymentInfo.getCreditCardInfo().getCardNumber() == null) {
			throw new MissingParameterException("creditCardNumber is required");
		}
		if (paymentInfo.getCreditCardInfo().getCardHolder() == null) {
			throw new MissingParameterException("creditCardHolder is required");
		}
		if (paymentInfo.getCreditCardInfo().getCvvNo() == null) {
			throw new MissingParameterException("creditCardCVV is required");
		}
		if (paymentInfo.getStudentInfo() == null) {
			throw new MissingParameterException("studentInfo is required");
		}
		if (paymentInfo.getStudentInfo().getStudentNumber() == null) {
			throw new MissingParameterException("studentNumber is required");
		}
		if (paymentInfo.getStudentInfo().getEmailAddress() == null) {
			throw new MissingParameterException("studentEmailAddress is required");
		}

		return getNextDecorator().processTpPayment(paymentInfo);
	}

}
