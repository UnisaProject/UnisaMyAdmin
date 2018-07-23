package za.ac.unisa.myadmin.payment.services.decorators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.payment.services.dto.NonTpPaymentInfo;

public class PaymentServiceCustomValidationDecoratorTest {

	@Test
	public void testValidateAmount() {
		PaymentServiceCustomValidationDecorator testService = new PaymentServiceCustomValidationDecorator();

		NonTpPaymentInfo paymentInfo = new NonTpPaymentInfo();

		// CASE 1: test with null value and zero test enabled
		try {
			testService.validateAmount(paymentInfo.getStudyFeeAmount(), "Study fee", true);
		} catch (NullPointerException | InvalidParameterException e) {
			fail("Should not have thrown an error.");
		}

		// CASE 2: test with null value and zero test disabled
		try {
			testService.validateAmount(paymentInfo.getStudyFeeAmount(), "Study fee", false);
		} catch (NullPointerException | InvalidParameterException e) {
			fail("Should not have thrown an error.");
		}

		paymentInfo.setStudyFeeAmount(new BigDecimal(0));
		// CASE 3: test with zero value and zero test disabled
		try {
			testService.validateAmount(paymentInfo.getStudyFeeAmount(), "Study fee", false);
			fail("Should have thrown an error.");
		} catch (NullPointerException | InvalidParameterException e) {
			// Error expected.
		}

		// CASE 4: test with zero value and zero test enabled
		try {
			testService.validateAmount(paymentInfo.getStudyFeeAmount(), "Study fee", true);
			fail("Should have thrown an error.");
		} catch (NullPointerException | InvalidParameterException e) {
			assertEquals("Study fee amount you wish to pay can not be zero.", e.getMessage());
		}

		// CASE 5: test for a negative value
		paymentInfo.setStudyFeeAmount(new BigDecimal(-1));
		try {
			testService.validateAmount(paymentInfo.getStudyFeeAmount(), "Study fee", false);
			fail("Should have thrown an error.");
		} catch (NullPointerException | InvalidParameterException e) {
			assertEquals("Study fee amount you wish to pay is invalid.", e.getMessage());
		}

		// CASE 6: test for a positive value with scale bigger than 2
		paymentInfo.setStudyFeeAmount(new BigDecimal("1.234"));
		try {
			testService.validateAmount(paymentInfo.getStudyFeeAmount(), "Study fee", false);
			fail("Should have thrown an error.");
		} catch (NullPointerException | InvalidParameterException e) {
			assertEquals("Study fee amount you wish to pay is invalid.", e.getMessage());
		}

		// CASE 7: test for a positive value with precision bigger than 9
		paymentInfo.setStudyFeeAmount(new BigDecimal("123456789.11"));
		try {
			testService.validateAmount(paymentInfo.getStudyFeeAmount(), "Study fee", false);
			fail("Should have thrown an error.");
		} catch (NullPointerException | InvalidParameterException e) {
			assertEquals("Study fee amount you wish to pay is too large.", e.getMessage());
		}

		// CASE 8: test for a positive value with the correct precision and scale
		paymentInfo.setStudyFeeAmount(new BigDecimal("1234567.89"));
		try {
			testService.validateAmount(paymentInfo.getStudyFeeAmount(), "Study fee", false);
		} catch (NullPointerException | InvalidParameterException e) {
			fail("Should not have thrown an error.");
		}
	}

	@Test
	public void testValidateNonTpPaymentTotals() {
		PaymentServiceCustomValidationDecorator testService = new PaymentServiceCustomValidationDecorator();

		NonTpPaymentInfo paymentInfo = new NonTpPaymentInfo();

		// CASE 1: test with null values
		try {
			testService.validateNonTpPaymentTotals(paymentInfo);
		} catch (NullPointerException | InvalidParameterException e) {
			fail("Should not have thrown an error.");
		}

		// CASE 2: test with some null values
		paymentInfo.setStudyFeeAmount(new BigDecimal(0));
		paymentInfo.setLibraryFineFee(new BigDecimal(0));
		try {
			testService.validateNonTpPaymentTotals(paymentInfo);
		} catch (NullPointerException | InvalidParameterException e) {
			fail("Should not have thrown an error.");
		}

		// CASE 3: test studyfees amount to be paid - failure
		paymentInfo.setStudyFeeAmount(new BigDecimal("500.50"));
		paymentInfo.setLibraryFineFee(new BigDecimal(0));
		paymentInfo.setCreditCardTotalAmountInput(new BigDecimal("250.00"));
		try {
			testService.validateNonTpPaymentTotals(paymentInfo);
			fail("Should have thrown an error.");
		} catch (NullPointerException | InvalidParameterException e) {
			assertEquals("The value of 'Total amount being paid' doesn't match total of study fees", e.getMessage());
		}

		// CASE 4: test studyfees to be paid - success
		paymentInfo.setStudyFeeAmount(new BigDecimal("500.50"));
		paymentInfo.setLibraryFineFee(new BigDecimal(0));
		paymentInfo.setCreditCardTotalAmountInput(new BigDecimal("500.50"));
		try {
			testService.validateNonTpPaymentTotals(paymentInfo);
		} catch (NullPointerException | InvalidParameterException e) {
			fail("Should not have thrown an error.");
		}

		// CASE 5: test other fees to be paid - failure
		paymentInfo.setPayLibraryFee(true);
		paymentInfo.setLibraryFee(new BigDecimal("50.25"));
		paymentInfo.setCanChooseLibraryCard(true);
		paymentInfo.setPayMatricFirstAppFee(true);
		paymentInfo.setMatricFirstAppFee(new BigDecimal("100.00"));
		paymentInfo.setCanChooseMatric(true);
		paymentInfo.setStudyFeeAmount(new BigDecimal("500.50"));
		paymentInfo.setLibraryFineFee(new BigDecimal(0));
		paymentInfo.setCreditCardTotalAmountInput(new BigDecimal("250.00"));
		try {
			testService.validateNonTpPaymentTotals(paymentInfo);
			fail("Should have thrown an error.");
		} catch (NullPointerException | InvalidParameterException e) {
			assertEquals(
					"The value of 'Total amount being paid' doesn't match total of library access plus matriculation plus study fees",
					e.getMessage());
		}

		// CASE 5: test other fees to be paid - success
		paymentInfo.setPayLibraryFee(true);
		paymentInfo.setLibraryFee(new BigDecimal("50.25"));
		paymentInfo.setCanChooseLibraryCard(true);
		paymentInfo.setPayMatricFirstAppFee(true);
		paymentInfo.setMatricFirstAppFee(new BigDecimal("100.00"));
		paymentInfo.setCanChooseMatric(true);
		paymentInfo.setStudyFeeAmount(new BigDecimal("500.50"));
		paymentInfo.setLibraryFineFee(new BigDecimal(0));
		paymentInfo.setCreditCardTotalAmountInput(new BigDecimal("650.75"));
		try {
			testService.validateNonTpPaymentTotals(paymentInfo);
		} catch (NullPointerException | InvalidParameterException e) {
			fail("Should not have thrown an error.");
		}
	}

}
