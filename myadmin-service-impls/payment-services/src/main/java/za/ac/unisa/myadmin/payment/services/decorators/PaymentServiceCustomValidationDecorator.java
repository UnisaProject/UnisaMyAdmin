package za.ac.unisa.myadmin.payment.services.decorators;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.payment.services.PaymentService;
import za.ac.unisa.myadmin.payment.services.dto.NonTpPaymentInfo;
import za.ac.unisa.myadmin.payment.services.dto.SummaryInfo;
import za.ac.unisa.myadmin.payment.services.dto.TpPaymentInfo;
import za.ac.unisa.myadmin.services.base.decorators.PaymentServiceDecorator;

public class PaymentServiceCustomValidationDecorator extends PaymentServiceDecorator implements PaymentService {

	@Override
	public SummaryInfo processNonTpPayment(NonTpPaymentInfo paymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		validateAmount(paymentInfo.getStudyFeeAmount(), "Study fee", false);
		validateAmount(paymentInfo.getLibraryFineFee(), "Library fine", false);
		validateAmount(paymentInfo.getCreditCardTotalAmountInput(), "Total", true);
		validateNonTpPaymentTotals(paymentInfo);

		return getNextDecorator().processNonTpPayment(paymentInfo);
	}

	@Override
	public SummaryInfo processTpPayment(TpPaymentInfo paymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		validateAmount(paymentInfo.getStudyFeeAmount(), "Study fee", false);
		validateAmount(paymentInfo.getLibraryFeeForStudent(), "Library fine", false);

		if (paymentInfo.getMinimumStudyFee().compareTo(BigDecimal.ZERO) > 0) {
			if (paymentInfo.getStudyFeeAmount().compareTo(paymentInfo.getMinimumStudyFee()) < 0) {
				NumberFormat formatter = new DecimalFormat("#0.00");
				throw new InvalidParameterException(
						"Study fee amount you wish to pay can not be less than R"
								+ String.valueOf(formatter.format(paymentInfo.getMinimumStudyFee())));
			}
		}

		validateAmount(paymentInfo.getCreditCardTotalAmountInput(), "Total", true);
		validateTpPaymentTotals(paymentInfo);

		return getNextDecorator().processTpPayment(paymentInfo);
	}

	protected void validateAmount(BigDecimal amount, String fieldName, boolean testForZero)
			throws InvalidParameterException {
		if (amount == null) {
			return;
		}
		if (testForZero && amount.signum() == 0) {
			throw new InvalidParameterException(fieldName + " amount you wish to pay can not be zero.");
		}
		if (amount.signum() == -1) {
			throw new InvalidParameterException(fieldName + " amount you wish to pay is invalid.");
		}
		if (amount.scale() != 2) {
			throw new InvalidParameterException(fieldName + " amount you wish to pay is invalid.");
		}
		if (amount.stripTrailingZeros().precision() > 9) {
			throw new InvalidParameterException(fieldName + " amount you wish to pay is too large.");
		}
	}

	protected void validateNonTpPaymentTotals(NonTpPaymentInfo nonTpPayment) throws InvalidParameterException {
		if (nonTpPayment.getStudyFeeAmount() == null || nonTpPayment.getLibraryFineFee() == null
				|| nonTpPayment.getCreditCardTotalAmountInput() == null) {
			return;
		}
		BigDecimal totalAmount = BigDecimal.ZERO;
		if (nonTpPayment.isPayLibraryFee() && nonTpPayment.getLibraryFee() != null) {
			totalAmount = totalAmount.add(nonTpPayment.getLibraryFee());
		}
		if (nonTpPayment.isPayMatricFirstAppFee() && nonTpPayment.getMatricFirstAppFee() != null) {
			totalAmount = totalAmount.add(nonTpPayment.getMatricFirstAppFee());
		}
		if (nonTpPayment.isPayThreeGDataBundleFee() && nonTpPayment.getThreeGDataBundleFee() != null) {
			totalAmount = totalAmount.add(nonTpPayment.getThreeGDataBundleFee());
		}

		totalAmount = totalAmount.add(nonTpPayment.getStudyFeeAmount()).add(nonTpPayment.getLibraryFineFee());

		if (totalAmount.compareTo(nonTpPayment.getCreditCardTotalAmountInput()) != 0) {
			StringBuilder label = new StringBuilder();
			if (nonTpPayment.isCanChooseThreeGDataBundle()) {
				if (nonTpPayment.isPayThreeGDataBundleFee()) {
					label.append("3G Data bundle");
				}
			}
			if (nonTpPayment.isCanChooseLibraryCard()) {
				if (nonTpPayment.isPayLibraryFee()) {
					if (label.length() > 0) {
						label.append(" plus ");
					}
					label.append("library access");
				}
			}
			if (nonTpPayment.isCanChooseMatric()) {
				if (nonTpPayment.isPayMatricFirstAppFee()) {
					if (label.length() > 0) {
						label.append(" plus ");
					}
					label.append("matriculation");
				}
			}
			if (nonTpPayment.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
				if (label.length() > 0) {
					label.append(" plus ");
				}
				label.append("study fees");
			}
			if (nonTpPayment.getLibraryFineFee().compareTo(BigDecimal.ZERO) > 0) {
				if (label.length() > 0) {
					label.append(" plus ");
				}
				label.append("library fine");
			}

			StringBuilder message = new StringBuilder("The value of 'Total amount being paid' doesn't match total of ")
					.append(label);
			throw new InvalidParameterException(message.toString());
		}
	}

	protected void validateTpPaymentTotals(TpPaymentInfo tpPayment) throws InvalidParameterException {
		BigDecimal totalAmount = tpPayment.getLibraryFeeForStudent().add(tpPayment.getMatricFeeForStudent())
				.add(tpPayment.getStudyFeeAmount()).add(tpPayment.getLibraryFineFeeForStudent());

		if (totalAmount.compareTo(tpPayment.getCreditCardTotalAmountInput()) != 0) {
			StringBuilder label = new StringBuilder();
			if (tpPayment.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
				label.append("library access, matriculation board , study fees  and library fine.");
			} else if (tpPayment.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
				label.append("library access, matriculation board and study fees.");
			} else if (tpPayment.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && +tpPayment.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
				label.append("library access, matriculation board   and library fine.");
			} else if (tpPayment.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0 && +tpPayment.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
				label.append("library access, study fees  and library fine.");
			} else if (tpPayment.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0 && +tpPayment.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
				label.append("matriculation board , study fees  and library fine.");
			} else if (tpPayment.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
				label.append("library access  and study fees .");
			} else if (tpPayment.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
				label.append("matriculation board   and study fees .");
			} else if (tpPayment.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
				label.append("library access  and study fees .");
			} else if (tpPayment.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
				label.append("library fine   and study fees .");
			} else if (tpPayment.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && +tpPayment.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
				label.append("matriculation  and library fine .");
			} else if (tpPayment.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
				label.append("library access  and matriculation board  fees .");
			} else if (tpPayment.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
				label.append("matriculation board and study fees .");
			} else if (tpPayment.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0 && tpPayment.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
				label.append("matriculation board and library access .");
			} else if (tpPayment.getStudyFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
				label.append("study fees .");
			} else if (tpPayment.getLibraryFineFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
				label.append("Library fine .");
			} else if (tpPayment.getMatricFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
				label.append("matriculation board fee.");
			} else if (tpPayment.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
				label.append("library access .");
			}

			StringBuilder message = new StringBuilder("Total amount being paid doesn\\'t match total of ")
					.append(label);
			throw new InvalidParameterException(message.toString());
		}
	}
	
}
