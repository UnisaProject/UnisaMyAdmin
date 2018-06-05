package za.ac.unisa.myadmin.creditcard.payment;

import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;

import java.beans.PropertyVetoException;

/**
 * Created by dev on 2018-06-04.
 */
public interface CreditCardPaymentService {

	/**
	 * @param userId
	 * @return
	 * @throws OperationFailedException
	 */
	CreditCardPaymentInfo processStudentInput(Integer userId) throws OperationFailedException, PropertyVetoException;
}
