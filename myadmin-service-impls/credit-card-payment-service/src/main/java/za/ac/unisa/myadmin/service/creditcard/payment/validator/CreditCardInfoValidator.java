package za.ac.unisa.myadmin.service.creditcard.payment.validator;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardInfo;

/**
 * Created by Adrian on 2018-06-11.
 */
@Component("creditCardInfoValidator")
public class CreditCardInfoValidator implements Validator {
	@Override
	public boolean supports(Class<?> aClass) {
		return CreditCardInfo.class.equals(aClass);
	}

	@Override
	public void validate(@Nullable Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNumber", "cardNumber.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardHolder", "cardHolder.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cvvNo", "cvvNo.empty");
		CreditCardInfo creditCardInfo = (CreditCardInfo) obj;
		//creditCardNumber
		if (creditCardInfo.getCardNumber().length() != 16) {
			errors.rejectValue("cardNumber", "cardNumber.length");
		}
		//cvvNumber
		if (creditCardInfo.getCvvNo().length() != 3) {
			errors.rejectValue("cvvNo", "cvvNo.length");
		}

	}
}
