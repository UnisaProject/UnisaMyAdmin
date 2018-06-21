package za.ac.unisa.myadmin.creditcard.payment;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by dev on 2018-06-04.
 */
public class QualPaymentInfo implements Serializable {

	private static final long serialVersionUID = -1411828481195058901L;

	private BigDecimal balance;

	private String creditDebitIndicator;

	private BigDecimal libraryFineBalance;

	private String libCreditDebitIndicator;

	private BigDecimal fullAccount;
	private BigDecimal dueImmediately;
	private BigDecimal minimumStudyFee;
	private BigDecimal minimumForReg;

	private CardStudentInfo studentInfo;

	private QualificationInfo qualificationInfo;


	public QualPaymentInfo() {
		this.studentInfo = new CardStudentInfo();
		this.qualificationInfo = new QualificationInfo();
	}

	public QualPaymentInfo(CardStudentInfo studentInfo, QualificationInfo qualificationInfo) {
		this.studentInfo = studentInfo;
		this.qualificationInfo = qualificationInfo;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getLibraryFineBalance() {
		return libraryFineBalance;
	}

	public void setLibraryFineBalance(BigDecimal libraryFineBalance) {
		this.libraryFineBalance = libraryFineBalance;
	}

	public String getLibCreditDebitIndicator() {
		return libCreditDebitIndicator;
	}

	public void setLibCreditDebitIndicator(String libCreditDebitIndicator) {
		this.libCreditDebitIndicator = libCreditDebitIndicator;
	}

	public String getCreditDebitIndicator() {
		return creditDebitIndicator;
	}

	public void setCreditDebitIndicator(String creditDebitIndicator) {
		this.creditDebitIndicator = creditDebitIndicator;
	}

	public CardStudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(CardStudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public QualificationInfo getQualificationInfo() {
		return qualificationInfo;
	}

	public void setQualificationInfo(QualificationInfo qualificationInfo) {
		this.qualificationInfo = qualificationInfo;
	}

	public BigDecimal getFullAccount() {
		return fullAccount;
	}

	public void setFullAccount(BigDecimal fullAccount) {
		this.fullAccount = fullAccount;
	}

	public BigDecimal getDueImmediately() {
		return dueImmediately;
	}

	public void setDueImmediately(BigDecimal dueImmediately) {
		this.dueImmediately = dueImmediately;
	}

	public BigDecimal getMinimumStudyFee() {
		return minimumStudyFee;
	}

	public void setMinimumStudyFee(BigDecimal minimumStudyFee) {
		this.minimumStudyFee = minimumStudyFee;
	}

	public BigDecimal getMinimumForReg() {
		return minimumForReg;
	}

	public void setMinimumForReg(BigDecimal minimumForReg) {
		this.minimumForReg = minimumForReg;
	}

	public String toStringStudent() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		result.append(NEW_LINE + "Stud Nr: " + studentInfo.getStudentNumber() + NEW_LINE);
		result.append("Qual: " + qualificationInfo.getQualCode() + NEW_LINE);
//		result.append("Reg status: " + regStatus + NEW_LINE);
		result.append("Email: " + studentInfo.getEmailAddress() + NEW_LINE);

		return result.toString();
	}

}
