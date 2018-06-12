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
import za.ac.unisa.myadmin.creditcard.payment.TpPaymentInfo;

import java.math.BigDecimal;

/**
 * Created by Adrian on 2018-06-11.
 */
@Component("TpPaymentInfoValidator")
public class TpPaymentInfoValidator implements Validator {

	@Autowired
	@Qualifier("creditCardInfoValidator")
	Validator creditCardInfoValidator;

	@Autowired
	@Qualifier("cardStudentInfoValidator")
	Validator cardStudentInfoValidator;

	public TpPaymentInfoValidator() {
	}

	public TpPaymentInfoValidator(Validator creditCardInfoValidator, Validator cardStudentInfoValidator) {
		if (creditCardInfoValidator == null) {
			throw new IllegalArgumentException("The supplied Validator[ " + creditCardInfoValidator + " ] is " +
				"required and must not be null.");
		}
		if (cardStudentInfoValidator == null) {
			throw new IllegalArgumentException("The supplied Validator[ " + cardStudentInfoValidator + " ] is " +
				"required and must not be null.");
		}
		if (!creditCardInfoValidator.supports(CreditCardInfo.class)) {
			throw new IllegalArgumentException("The supplied [creditCardInfoValidator] must " +
				"support the validation of [CreditCardInfo] instances.");
		}
		if (!cardStudentInfoValidator.supports(CardStudentInfo.class)) {
			throw new IllegalArgumentException("The supplied [cardStudentInfoValidator] must " +
				"support the validation of [CardStudentInfo] instances.");
		}
		this.creditCardInfoValidator = creditCardInfoValidator;
		this.cardStudentInfoValidator = cardStudentInfoValidator;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return TpPaymentInfo.class.equals(aClass);
	}

	@Override
	public void validate(@Nullable Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCardTotalAmountInput", "creditCardTotalAmountInput.empty");//Invalid total amount being paid.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studyFeeAmount", "studyFeeAmount.empty");//Invalid total amount being paid.
		TpPaymentInfo tpPaymentInfo = (TpPaymentInfo) o;
		//Total Amounts
		BigDecimal totalAmount = BigDecimal.ZERO;
		//Study fee amount larger than minimum required for registration
		if (tpPaymentInfo.getMinimumStudyFee().compareTo(BigDecimal.ZERO) > 0) {
			if (tpPaymentInfo.getStudyFeeAmount().compareTo(tpPaymentInfo.getMinimumStudyFee()) < 0) {
				//NumberFormat formatter = new DecimalFormat("#0.00");
				errors.rejectValue("studyFeeAmount", "tpPaymentInfo.studyFeeAmount.amount", new String[]{String.valueOf(tpPaymentInfo.getMinimumStudyFee())}, null);
				//return "Study fee amount you wish to pay can not be less than R" +tpPaymentInfo.getMinimumStudyFee();
			}
		}
		// Check that fee totals match up
		if (!errors.hasErrors()) {
			totalAmount = tpPaymentInfo.getLibraryFeeForStudent().add(tpPaymentInfo.getMatricFeeForStudent()).add(tpPaymentInfo.getStudyFeeAmount()).add(tpPaymentInfo.getLibraryFineFeeForStudent());
			//totalAmount = totalAmount.add(tpPaymentInfo.getStudyFeeAmount()).add(tpPaymentInfo.getLibraryFineFee());

			if (totalAmount.compareTo(tpPaymentInfo.getCreditCardTotalAmountInput()) != 0) {
				errors.rejectValue("creditCardTotalAmountInput", "tpPaymentInfo.creditCardTotalAmountInput.amount");
			}
		}
		try {
			errors.pushNestedPath("creditCardInfo");
			ValidationUtils.invokeValidator(this.creditCardInfoValidator, tpPaymentInfo.getCreditCardInfo(), errors);
			errors.popNestedPath();
			errors.pushNestedPath("studentInfo");
			ValidationUtils.invokeValidator(this.cardStudentInfoValidator, tpPaymentInfo.getStudentInfo(), errors);
		} finally {
			errors.popNestedPath();
		}
	}
}
