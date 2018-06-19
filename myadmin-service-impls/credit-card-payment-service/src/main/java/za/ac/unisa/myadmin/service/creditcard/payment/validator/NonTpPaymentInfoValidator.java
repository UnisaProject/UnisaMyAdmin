package za.ac.unisa.myadmin.service.creditcard.payment.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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

		// Check totals
		String errMessage = ValidatorUtil.validateAmount(String.valueOf(nonTPInfo.getStudyFeeAmount()), false);
		if (StringUtils.hasText(errMessage)) {
			errors.rejectValue("studyFeeAmount", "nonTpPaymentInfo.studyFeeAmount.amount", new String[]{errMessage}, null);
			return;
		}
		errMessage = ValidatorUtil.validateAmount(String.valueOf(nonTPInfo.getLibraryFineFee()), false);
		if (StringUtils.hasText(errMessage)) {
			errors.rejectValue("libraryFineFee", "nonTpPaymentInfo.libraryFineFee.amount", new String[]{errMessage}, null);
			return;
		}
		errMessage = ValidatorUtil.validateAmount(String.valueOf(nonTPInfo.getCreditCardTotalAmountInput()), true);
		if (StringUtils.hasText(errMessage)) {
			errors.rejectValue("creditCardTotalAmountInput", "nonTpPaymentInfo.creditCardTotalAmountInput.amount.format", new String[]{errMessage}, null);
			return;
		}
		if (errors.hasErrors()) {
			return;
		}
		totalAmount = totalAmount.add(nonTPInfo.getStudyFeeAmount()).add(nonTPInfo.getLibraryFineFee());

		if (totalAmount.compareTo(nonTPInfo.getCreditCardTotalAmountInput()) != 0) {
			String totalErrormsg = verifyEnteredvsCalculatedTotals(totalAmount, nonTPInfo);
			errors.rejectValue("creditCardTotalAmountInput", "nonTpPaymentInfo.creditCardTotalAmountInput.amount", new String[]{totalErrormsg}, null);
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

	private String verifyEnteredvsCalculatedTotals(BigDecimal totalAmount, NonTpPaymentInfo paymentInfo) {
		String errorString = new String();
		int intitalLenth = errorString.length();
		if (totalAmount.compareTo(paymentInfo.getCreditCardTotalAmountInput()) != 0) {

			String threegtempStr = check3GBeingPaid(paymentInfo);
			errorString += threegtempStr;
			String libtempStr = checkLibraryCardBeingPaid(paymentInfo);
			if (!libtempStr.trim().equals("")) {
				if (intitalLenth < errorString.length()) {
					libtempStr = " plus " + libtempStr;
				}
			}
			errorString += libtempStr;
			String matricBoardTempStr = matricBoardBeingPaid(paymentInfo);
			if (!matricBoardTempStr.trim().equals("")) {
				if (intitalLenth < errorString.length()) {
					matricBoardTempStr = " plus " + matricBoardTempStr;
				}
			}
			errorString += matricBoardTempStr;
			String studFeetempStr = checkStudyFeesBeingPaid(paymentInfo);
			if (!studFeetempStr.trim().equals("")) {
				if (intitalLenth < errorString.length()) {
					studFeetempStr = " plus " + studFeetempStr;
				}
			}
			errorString += studFeetempStr;
			String libfinetempStr = checkLibraryFineBeingPaid(paymentInfo);
			if (!libfinetempStr.trim().equals("")) {
				if (intitalLenth < errorString.length()) {
					libfinetempStr = " plus " + libfinetempStr;
				}
			}
			errorString += libfinetempStr;
		} else {
			return "";
		}
		return errorString;
	}

	private String matricBoardBeingPaid(NonTpPaymentInfo creditForm) {
		if (creditForm.isCanChooseMatric()) {
			if (creditForm.isPayMatricFirstAppFee()) {
				return "matriculation ";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	private String check3GBeingPaid(NonTpPaymentInfo creditForm) {
		if (creditForm.isCanChooseThreeGDataBundle()) {
			if (creditForm.isPayThreeGDataBundleFee()) {
				return "3G Data bundle ";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	private String checkLibraryCardBeingPaid(NonTpPaymentInfo creditForm) {
		if (creditForm.isCanChooseLibraryCard()) {
			if (creditForm.isPayLibraryFee()) {
				return "library access ";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	private String checkStudyFeesBeingPaid(NonTpPaymentInfo creditForm) {
		if (creditForm.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
			return "study fees ";
		} else {
			return "";
		}
	}

	private String checkLibraryFineBeingPaid(NonTpPaymentInfo creditForm) {
		if (creditForm.getLibraryFineFee().compareTo(BigDecimal.ZERO) > 0) {
			return "library fine ";
		} else {
			return "";
		}
	}
}
