package za.ac.unisa.myadmin.creditcard.payment;

import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by dev on 2018-06-04.
 */
public class CreditCardPaymentInfo implements Serializable {

	private static final long serialVersionUID = -1411828481195058901L;
	private String regStatus;

	private String regStatusDescription;

	private BigDecimal balance;

	private BigDecimal libraryFineBalance;

	private String libCreditDebitIndicator;

	// set NON-TP matric +lib fees
	private BigDecimal libraryFee;
	private BigDecimal libraryFineFee;
	private BigDecimal threeGDataBundleFee;
	private BigDecimal matricFirstAppFee;

	//TP Fees
	private BigDecimal libraryFeeForStudent;
	private BigDecimal libraryFineFeeForStudent;
	private BigDecimal threeGDataBundleFeeForStudent;
	private BigDecimal matricFeeForStudent;
	private BigDecimal fullAccount;
	private BigDecimal dueImmediately;
	private BigDecimal minimumStudyFee;
	private BigDecimal minimumForReg;

	private boolean canChooseLibraryCard;
	private boolean canChooseMatric;
	private boolean canChooseThreeGDataBundle;
	private BigDecimal applyAmount;

	private String creditDebitIndicator;

	private StudentInfo studentInfo;

	private QualificationInfo qualificationInfo;


	public CreditCardPaymentInfo() {
		this.studentInfo = new StudentInfo();
		this.qualificationInfo = new QualificationInfo();
	}

	public CreditCardPaymentInfo(StudentInfo studentInfo, QualificationInfo qualificationInfo) {
		this.studentInfo = studentInfo;
		this.qualificationInfo = qualificationInfo;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public String getRegStatusDescription() {
		return regStatusDescription;
	}

	public void setRegStatusDescription(String regStatusDescription) {
		this.regStatusDescription = regStatusDescription;
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

	public BigDecimal getLibraryFee() {
		return libraryFee;
	}

	public void setLibraryFee(BigDecimal libraryFee) {
		this.libraryFee = libraryFee;
	}

	public BigDecimal getThreeGDataBundleFee() {
		return threeGDataBundleFee;
	}

	public void setThreeGDataBundleFee(BigDecimal threeGDataBundleFee) {
		this.threeGDataBundleFee = threeGDataBundleFee;
	}

	public BigDecimal getMatricFirstAppFee() {
		return matricFirstAppFee;
	}

	public void setMatricFirstAppFee(BigDecimal matricFirstAppFee) {
		this.matricFirstAppFee = matricFirstAppFee;
	}

	public BigDecimal getLibraryFineFee() {
		return libraryFineFee;
	}

	public void setLibraryFineFee(BigDecimal libraryFineFee) {
		this.libraryFineFee = libraryFineFee;
	}

	public String getCreditDebitIndicator() {
		return creditDebitIndicator;
	}

	public void setCreditDebitIndicator(String creditDebitIndicator) {
		this.creditDebitIndicator = creditDebitIndicator;
	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public QualificationInfo getQualificationInfo() {
		return qualificationInfo;
	}

	public void setQualificationInfo(QualificationInfo qualificationInfo) {
		this.qualificationInfo = qualificationInfo;
	}

	public BigDecimal getLibraryFeeForStudent() {
		return libraryFeeForStudent;
	}

	public void setLibraryFeeForStudent(BigDecimal libraryFeeForStudent) {
		this.libraryFeeForStudent = libraryFeeForStudent;
	}

	public BigDecimal getLibraryFineFeeForStudent() {
		return libraryFineFeeForStudent;
	}

	public void setLibraryFineFeeForStudent(BigDecimal libraryFineFeeForStudent) {
		this.libraryFineFeeForStudent = libraryFineFeeForStudent;
	}

	public BigDecimal getThreeGDataBundleFeeForStudent() {
		return threeGDataBundleFeeForStudent;
	}

	public void setThreeGDataBundleFeeForStudent(BigDecimal threeGDataBundleFeeForStudent) {
		this.threeGDataBundleFeeForStudent = threeGDataBundleFeeForStudent;
	}

	public BigDecimal getMatricFeeForStudent() {
		return matricFeeForStudent;
	}

	public void setMatricFeeForStudent(BigDecimal matricFeeForStudent) {
		this.matricFeeForStudent = matricFeeForStudent;
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

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public boolean isCanChooseLibraryCard() {
		return canChooseLibraryCard;
	}

	public void setCanChooseLibraryCard(boolean canChooseLibraryCard) {
		this.canChooseLibraryCard = canChooseLibraryCard;
	}

	public boolean isCanChooseMatric() {
		return canChooseMatric;
	}

	public void setCanChooseMatric(boolean canChooseMatric) {
		this.canChooseMatric = canChooseMatric;
	}

	public boolean isCanChooseThreeGDataBundle() {
		return canChooseThreeGDataBundle;
	}

	public void setCanChooseThreeGDataBundle(boolean canChooseThreeGDataBundle) {
		this.canChooseThreeGDataBundle = canChooseThreeGDataBundle;
	}

	public String toStringStudent() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		result.append(NEW_LINE + "Stud Nr: " + studentInfo.getStudentNumber() + NEW_LINE);
		result.append("Qual: " + qualificationInfo.getQualCode() + NEW_LINE);
		result.append("Reg status: " + regStatus + NEW_LINE);
		result.append("Email: " + studentInfo.getEmailAddress() + NEW_LINE);

		return result.toString();
	}

}
