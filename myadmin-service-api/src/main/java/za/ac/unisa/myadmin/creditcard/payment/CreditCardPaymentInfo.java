package za.ac.unisa.myadmin.creditcard.payment;

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

	private BigDecimal librayFineBalance;

	private String libCreditDebitIndicator;

	private BigDecimal libraryFee;

	private BigDecimal threeGDataBundleFee;

	private BigDecimal matricFirstAppFee;

	private BigDecimal libraryFineFee;

	private BigDecimal libraryFeeForStudent;
	private BigDecimal libraryFeeAmount;
	private BigDecimal libraryFineFeeAmount;
	private BigDecimal threeGDataBundleFeeAmount;
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

	private CardStudentInfo studentInfo;

	private QualificationInfo qualificationInfo;

	public CreditCardPaymentInfo() {
		this.studentInfo = new CardStudentInfo();
		this.qualificationInfo = new QualificationInfo();
	}

	public CreditCardPaymentInfo(CardStudentInfo studentInfo, QualificationInfo qualificationInfo) {
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

	public BigDecimal getLibrayFineBalance() {
		return librayFineBalance;
	}

	public void setLibrayFineBalance(BigDecimal librayFineBalance) {
		this.librayFineBalance = librayFineBalance;
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

	public BigDecimal getLibraryFeeForStudent() {
		return libraryFeeForStudent;
	}

	public void setLibraryFeeForStudent(BigDecimal libraryFeeForStudent) {
		this.libraryFeeForStudent = libraryFeeForStudent;
	}

	public BigDecimal getLibraryFeeAmount() {
		return libraryFeeAmount;
	}

	public void setLibraryFeeAmount(BigDecimal libraryFeeAmount) {
		this.libraryFeeAmount = libraryFeeAmount;
	}

	public BigDecimal getLibraryFineFeeAmount() {
		return libraryFineFeeAmount;
	}

	public void setLibraryFineFeeAmount(BigDecimal libraryFineFeeAmount) {
		this.libraryFineFeeAmount = libraryFineFeeAmount;
	}

	public BigDecimal getThreeGDataBundleFeeAmount() {
		return threeGDataBundleFeeAmount;
	}

	public void setThreeGDataBundleFeeAmount(BigDecimal threeGDataBundleFeeAmount) {
		this.threeGDataBundleFeeAmount = threeGDataBundleFeeAmount;
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

}
