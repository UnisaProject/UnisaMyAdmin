package za.ac.unisa.myadmin.service.creditcard.payment.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import za.ac.unisa.myadmin.creditcard.payment.CardStudentInfo;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardInfo;
import za.ac.unisa.myadmin.creditcard.payment.NonTpPaymentInfo;

import java.math.BigDecimal;

/**
 * Created by Adrian on 2018-06-11.
 */
@Component("NonTpPaymentInfoValidator")
public class NonTpPaymentInfoValidator implements Validator {

	@Autowired
	@Qualifier("creditCardInfoValidator")
	Validator creditCardInfoValidator;

	@Autowired
	@Qualifier("cardStudentInfoValidator")
	Validator cardStudentInfoValidator;

	public NonTpPaymentInfoValidator() {
	}

	public NonTpPaymentInfoValidator(Validator creditCardInfoValidator, Validator cardStudentInfoValidator) {
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
				"support the validation of [Address] instances.");
		}
		if (!cardStudentInfoValidator.supports(CardStudentInfo.class)) {
			throw new IllegalArgumentException("The supplied [Validator] must " +
				"support the validation of [Address] instances.");
		}
		this.creditCardInfoValidator = creditCardInfoValidator;
		this.cardStudentInfoValidator = cardStudentInfoValidator;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return NonTpPaymentInfo.class.equals(aClass);
	}

	@Override
	public void validate(@Nullable Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCardTotalAmountInput", "creditCardTotalAmountInput.empty");//Invalid total amount being paid.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studyFeeAmount", "studyFeeAmount.empty");//Invalid total amount being paid.
		NonTpPaymentInfo nonTPInfo = (NonTpPaymentInfo) o;
		//Total Amounts
		BigDecimal totalAmount = BigDecimal.ZERO;
		if (nonTPInfo.isPayLibraryFee()) {
			totalAmount = totalAmount.add(nonTPInfo.getLibraryFee());
		}
		if (nonTPInfo.isPayMatricFirstAppFee()) {
			totalAmount = totalAmount.add(nonTPInfo.getMatricFirstAppFee());
		}
		if (nonTPInfo.isPayThreeGDataBundleFee()) {
			totalAmount = totalAmount.add(nonTPInfo.getThreeGDataBundleFee());
		}

		// Check that fee totals match up
		if (!errors.hasErrors()) {
			totalAmount = totalAmount.add(nonTPInfo.getStudyFeeAmount()).add(nonTPInfo.getLibraryFineFee());

			if (totalAmount.compareTo(nonTPInfo.getCreditCardTotalAmountInput()) != 0) {
				errors.rejectValue("creditCardTotalAmountInput", "nonTpPaymentInfo.creditCardTotalAmountInput.amount");//Credit card number must consist of 16 digits.
			}
		}
		try {
			errors.pushNestedPath("creditCardInfo");
			ValidationUtils.invokeValidator(this.creditCardInfoValidator, nonTPInfo.getCreditCardInfo(), errors);
			errors.popNestedPath();
			errors.pushNestedPath("studentInfo");
			ValidationUtils.invokeValidator(this.cardStudentInfoValidator, nonTPInfo.getStudentInfo(), errors);
		} finally {
			errors.popNestedPath();
		}
	}
}
