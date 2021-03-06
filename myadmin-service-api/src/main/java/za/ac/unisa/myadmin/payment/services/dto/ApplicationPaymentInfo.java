package za.ac.unisa.myadmin.payment.services.dto;

import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Adrian on 2018-06-04.
 */
public class ApplicationPaymentInfo implements Serializable {

	private static final long serialVersionUID = -1411828481195058901L;

	private StudentInfo studentInfo;

	private BigDecimal applyAmountInput;

	private BigDecimal creditCardTotalAmountInput;

	private CreditCardInfo cardInfo;

	public ApplicationPaymentInfo() {
		this.studentInfo = new StudentInfo();
		this.cardInfo = new CreditCardInfo();
	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public BigDecimal getApplyAmountInput() {
		return applyAmountInput;
	}

	public void setApplyAmountInput(BigDecimal applyAmountInput) {
		this.applyAmountInput = applyAmountInput;
	}

	public BigDecimal getCreditCardTotalAmountInput() {
		return creditCardTotalAmountInput;
	}

	public void setCreditCardTotalAmountInput(BigDecimal creditCardTotalAmountInput) {
		this.creditCardTotalAmountInput = creditCardTotalAmountInput;
	}

	public CreditCardInfo getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(CreditCardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}

	@Override
	public String toString() {
		return "ApplicationPaymentInfo{" +
			"studentInfo=" + studentInfo.toString() +
			", applyAmountInput=" + applyAmountInput +
			", creditCardTotalAmountInput=" + creditCardTotalAmountInput +
			", cardInfo=" + cardInfo +
			'}';
	}
}
