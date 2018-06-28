package za.ac.unisa.myadmin.service.creditcard.payment.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardInfo;
import za.ac.unisa.myadmin.creditcard.payment.TpPaymentInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
		if (!cardStudentInfoValidator.supports(StudentInfo.class)) {
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

		// Check totals
		String errMessage = ValidatorUtil.validateAmount(String.valueOf(tpPaymentInfo.getStudyFeeAmount()), false);
		if (StringUtils.hasText(errMessage)) {
			errors.rejectValue("studyFeeAmount", "tpPaymentInfo.studyFeeAmount.amount", new String[]{errMessage}, null);
			return;
		}
		errMessage = ValidatorUtil.validateAmount(String.valueOf(tpPaymentInfo.getLibraryFineFeeForStudent()), false);
		if (StringUtils.hasText(errMessage)) {
			errors.rejectValue("libraryFineFeeForStudent", "tpPaymentInfo.libraryFineFeeForStudent.amount", new String[]{errMessage}, null);
			return;
		}
		//Study fee amount larger than minimum required for registration
		if (tpPaymentInfo.getMinimumStudyFee().compareTo(BigDecimal.ZERO) > 0) {
			if (tpPaymentInfo.getStudyFeeAmount().compareTo(tpPaymentInfo.getMinimumStudyFee()) < 0) {
				NumberFormat formatter = new DecimalFormat("#0.00");
				errors.rejectValue("studyFeeAmount", "tpPaymentInfo.studyFeeAmount.amount.min", new String[]{String.valueOf(formatter.format(tpPaymentInfo.getMinimumStudyFee()))}, null);
				return;
			}
		}
		errMessage = ValidatorUtil.validateAmount(String.valueOf(tpPaymentInfo.getCreditCardTotalAmountInput()), true);
		if (StringUtils.hasText(errMessage)) {
			errors.rejectValue("creditCardTotalAmountInput", "tpPaymentInfo.creditCardTotalAmountInput.amount.format", new String[]{errMessage}, null);
			return;
		}
		if (errors.hasErrors()) {
			return;
		}
		// Check that fee totals match up
		totalAmount = tpPaymentInfo.getLibraryFeeForStudent().add(tpPaymentInfo.getMatricFeeForStudent()).add(tpPaymentInfo.getStudyFeeAmount()).add(tpPaymentInfo.getLibraryFineFeeForStudent());

		if (totalAmount.compareTo(tpPaymentInfo.getCreditCardTotalAmountInput()) != 0) {
			String totalErrorMessage = validateSumOfTotals(tpPaymentInfo);
			errors.rejectValue("creditCardTotalAmountInput", null, null,"Total amount being paid doesn\'t match total of " + totalErrorMessage);
			//TODO This does not resolve in messages resource for some reason.
			//errors.rejectValue("creditCardTotalAmountInput", "tpPaymentInfo.creditCardTotalAmountInput.amount",	new String[]{totalErrorMessage}, null);
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

	private String validateSumOfTotals(TpPaymentInfo tpPaymentInfo) {
		if (tpPaymentInfo.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
			return "library access, matriculation board , study fees  and library fine.";
		} else if (tpPaymentInfo.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
			return "library access, matriculation board and study fees.";
		} else if (tpPaymentInfo.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && +tpPaymentInfo.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
			return "library access, matriculation board   and library fine.";
		} else if (tpPaymentInfo.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0 && +tpPaymentInfo.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
			return "library access, study fees  and library fine.";
		} else if (tpPaymentInfo.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0 && +tpPaymentInfo.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
			return "matriculation board , study fees  and library fine.";
		} else if (tpPaymentInfo.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
			return "library access  and study fees .";
		} else if (tpPaymentInfo.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
			return "matriculation board   and study fees .";
		} else if (tpPaymentInfo.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
			return "library access  and study fees .";
		} else if (tpPaymentInfo.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
			return "library fine   and study fees .";
		} else if (tpPaymentInfo.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && +tpPaymentInfo.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
			return "matriculation  and library fine .";
		} else if (tpPaymentInfo.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
			return "library access  and matriculation board  fees .";
		} else if (tpPaymentInfo.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
			return "matriculation board and study fees .";
		} else if (tpPaymentInfo.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPaymentInfo.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
			return "matriculation board and library access .";
		} else if (tpPaymentInfo.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
			return "study fees .";
		} else if (tpPaymentInfo.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
			return "Library fine .";
		} else if (tpPaymentInfo.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
			return "matriculation board fee.";
		} else if (tpPaymentInfo.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
			return "library access .";
		}
		return "";
	}
}
