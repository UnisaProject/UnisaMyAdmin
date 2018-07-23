package za.ac.unisa.myadmin.payment.services;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.payment.services.dto.ApplicationPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.CreditCardPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.NonTpPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.QualPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.SummaryInfo;
import za.ac.unisa.myadmin.payment.services.dto.TpPaymentInfo;

/**
 * Created by dev on 2018-06-04.
 */
public interface PaymentService {

	/**
	 * @param userId
	 * @return
	 * @throws OperationFailedException
	 */
	CreditCardPaymentInfo processStudentInput(Integer userId) throws OperationFailedException;

	/**
	 * @param studentNumber
	 * @param qualCode
	 * @return
	 * @throws OperationFailedException
	 */
	QualPaymentInfo processQualInput(Integer studentNumber, String qualCode) throws OperationFailedException;

	/**
	 * @param paymentInfo
	 * @return
	 * @throws OperationFailedException
	 */
	SummaryInfo processApplicationPayment(ApplicationPaymentInfo paymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	/**
	 * @param paymentInfo
	 * @return
	 * @throws OperationFailedException
	 */
	SummaryInfo processNonTpPayment(NonTpPaymentInfo paymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	/**
	 * @param paymentInfo
	 * @return
	 * @throws OperationFailedException
	 */
	SummaryInfo processTpPayment(TpPaymentInfo paymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
