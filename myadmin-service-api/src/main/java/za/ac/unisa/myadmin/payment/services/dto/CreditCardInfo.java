package za.ac.unisa.myadmin.payment.services.dto;

import java.io.Serializable;

/**
 * Created by Adrian on 2018-06-06.
 */
public class CreditCardInfo implements Serializable {

	private static final long serialVersionUID = 7149742267665580040L;

	private String cardNumber;

	private String cardHolder;

	private String cvvNo;

	private String expiryMonth;

	private String expiryYear;

	private String budgetPeriod;

	public CreditCardInfo() {
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getCvvNo() {
		return cvvNo;
	}

	public void setCvvNo(String cvvNo) {
		this.cvvNo = cvvNo;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	public String getBudgetPeriod() {
		return budgetPeriod;
	}

	public void setBudgetPeriod(String budgetPeriod) {
		this.budgetPeriod = budgetPeriod;
	}

}
