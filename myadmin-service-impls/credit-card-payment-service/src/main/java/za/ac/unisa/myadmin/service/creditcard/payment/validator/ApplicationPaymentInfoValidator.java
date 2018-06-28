package za.ac.unisa.myadmin.service.creditcard.payment.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import za.ac.unisa.myadmin.creditcard.payment.ApplicationPaymentInfo;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

/**
 * Created by Adrian on 2018-06-11.
 */
@Component("ApplicationPaymentInfoValidator")
public class ApplicationPaymentInfoValidator implements Validator {

	@Autowired
	@Qualifier("creditCardInfoValidator")
	Validator creditCardInfoValidator;

	@Autowired
	@Qualifier("cardStudentInfoValidator")
	Validator cardStudentInfoValidator;

	public ApplicationPaymentInfoValidator() {
	}

	public ApplicationPaymentInfoValidator(Validator creditCardInfoValidator, Validator cardStudentInfoValidator) {
		if (creditCardInfoValidator == null) {
			throw new IllegalArgumentException("The supplied Validator[ " + creditCardInfoValidator + " ] is " +
				"required and must not be null.");
		}
		if (cardStudentInfoValidator == null) {
			throw new IllegalArgumentException("The supplied Validator[ " + cardStudentInfoValidator + " ] is " +
				"required and must not be null.");
		}
		if (!creditCardInfoValidator.supports(CreditCardInfo.class)) {
			throw new IllegalArgumentException("The supplied [Validator] must " +
				"support the validation of [CreditCardInfo] instances.");
		}
		if (!cardStudentInfoValidator.supports(StudentInfo.class)) {
			throw new IllegalArgumentException("The supplied [Validator] must " +
				"support the validation of [StudentInfo] instances.");
		}
		this.creditCardInfoValidator = creditCardInfoValidator;
		this.cardStudentInfoValidator = cardStudentInfoValidator;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return ApplicationPaymentInfo.class.equals(aClass);
	}

	@Override
	public void validate(@Nullable Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCardTotalAmountInput", "creditCardTotalAmountInput.empty");
		ApplicationPaymentInfo paymentInfo = (ApplicationPaymentInfo) o;
		try {
			errors.pushNestedPath("cardInfo");
			ValidationUtils.invokeValidator(this.creditCardInfoValidator, paymentInfo.getCardInfo(), errors);
			errors.popNestedPath();
			errors.pushNestedPath("studentInfo");
			ValidationUtils.invokeValidator(this.cardStudentInfoValidator, paymentInfo.getStudentInfo(), errors);
		} finally {
			errors.popNestedPath();
		}
	}
}
