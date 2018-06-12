package za.ac.unisa.myadmin.service.creditcard.payment.validator;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import za.ac.unisa.myadmin.creditcard.payment.CardStudentInfo;

import java.util.regex.Pattern;

/**
 * Created by Adrian on 2018-06-11.
 */
@Component("cardStudentInfoValidator")
public class CardStudentInfoValidator implements Validator {

	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public boolean supports(Class<?> aClass) {
		return CardStudentInfo.class.equals(aClass);
	}

	@Override
	public void validate(@Nullable Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studentNumber", "studentNumber.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "emailAddress.empty");
		CardStudentInfo studentInfo = (CardStudentInfo) o;
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		//studentNumber
		if (String.valueOf(studentInfo.getStudentNumber()).length() < 7) {
			errors.rejectValue("studentNumber", "studentNumber.length.less.than");
		}
		if (String.valueOf(studentInfo.getStudentNumber()).length() > 8) {
			errors.rejectValue("studentNumber", "studentNumber.length.more.than");
		}
		//emailAddress
		if (!pattern.matcher(studentInfo.getEmailAddress()).matches()) {
			errors.rejectValue("emailAddress", "emailAddress.invalid");
		}
	}
}
