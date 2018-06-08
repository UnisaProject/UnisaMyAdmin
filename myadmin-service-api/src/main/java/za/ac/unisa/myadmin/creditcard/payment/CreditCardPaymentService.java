package za.ac.unisa.myadmin.creditcard.payment;

import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;

/**
 * Created by dev on 2018-06-04.
 */
public interface CreditCardPaymentService {

	/**
	 * @param userId
	 * @return
	 * @throws OperationFailedException
	 */
	CreditCardPaymentInfo processStudentInput(Integer userId) throws OperationFailedException;

	String getSmartCardValue(Integer userId) throws OperationFailedException;

	/**
	 * @param paymentInfo
	 * @return
	 * @throws OperationFailedException
	 */
	SummaryInfo processApplicationPayment(ApplicationPaymentInfo paymentInfo) throws OperationFailedException;

	/**
	 * @param paymentInfo
	 * @return
	 * @throws OperationFailedException
	 */
	SummaryInfo processNonTpPayment(NonTpPaymentInfo paymentInfo) throws OperationFailedException;

	/**
	 * @param paymentInfo
	 * @return
	 * @throws OperationFailedException
	 */
	SummaryInfo processTpPayment(TpPaymentInfo paymentInfo) throws OperationFailedException;

	int updateSmartCardValue(String smartCard, Integer studentNumber);

	CreditCardPaymentInfo processQualInput(Integer studentNumber, String qualCode) throws OperationFailedException;
}
