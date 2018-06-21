package za.ac.unisa.myadmin.service.creditcard.payment.validator;

import java.math.BigDecimal;

/**
 * Created by Adrian on 2018-06-19.
 */
public class ValidatorUtil {

	public static boolean isNumeric(String testString) {
		boolean result = true;
		try {
			Long.parseLong(testString);
		} catch (NumberFormatException e){
			result = false;
		}
		return result;
	}

	/**
	 *
	 * @param testString Amount to validate
	 * @param testForZero Amount can't be zero when true
	 * @return
	 */
	public static String validateAmount(String testString, boolean testForZero) {

		// testForZero = 1: input can not be zero
		BigDecimal money;

		// must be A NUMBER
		try {
			money = new BigDecimal(testString);
		} catch (NumberFormatException e) {
			return " is invalid.";
		}
		if (testForZero) {
			// can not be zero
			if (money.compareTo(new BigDecimal("0")) == 0) {
				return "can not be zero.";
			}
		}
		// can not be negative
		int pos = testString.indexOf("-");
		if (pos != -1) {
			return "is invalid.";
		}
		// can not be more than 2 digits after decimal point and 7 before
		pos = testString.indexOf(".");
		if (pos >= 0) {
			String remainder = testString.substring(pos + 1);
			if (remainder.length() > 2) {
				return "is invalid.";
			}
			String number = testString.substring(0, pos - 1);
			if (number.length() > 7) {
				return "is too large.";
			}

		} else {
			if (testString.length() > 7) {
				return "is too large.";
			}
		}

		return "";

	}

}
