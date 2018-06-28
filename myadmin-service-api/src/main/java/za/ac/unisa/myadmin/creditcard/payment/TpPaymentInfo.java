package za.ac.unisa.myadmin.creditcard.payment;

import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by dev on 2018-06-04.
 */
public class TpPaymentInfo implements Serializable {

	private static final long serialVersionUID = -1411828481195058901L;

	private StudentInfo studentInfo;

	private QualificationInfo qualificationInfo;

	private CreditCardInfo creditCardInfo;

	private String regStatus;

	private String regStatusDescription;

	private BigDecimal balance;

	private String creditDebitIndicator;

	private BigDecimal libraryFineBalance;

	private String libCreditDebitIndicator;

	private BigDecimal dueImmediately;
	private BigDecimal minimumForReg;
	private BigDecimal fullAccount;

	//TP Fees
	private BigDecimal libraryFineFeeForStudent;

	private BigDecimal minimumStudyFee;
	private BigDecimal studyFeeAmount;

	private BigDecimal libraryFeeForStudent;
	// field to indicate whether a TP student can cancel his smartcard request
	//private boolean canSmartCardCancel = false;
	//private boolean cancelSmartCard = false;

	private BigDecimal matricFeeForStudent;

	//private BigDecimal threeGDataBundleFeeForStudent;
	//private boolean threeGDataBundleChoice = false;

	//	private boolean canChooseLibraryCard;
	//	private boolean canChooseMatric;
	//	private boolean canChooseThreeGDataBundle;
	//
	//	private BigDecimal applyAmount;

	private BigDecimal creditCardTotalAmountInput;

	public TpPaymentInfo() {
		this.studentInfo = new StudentInfo();
		this.qualificationInfo = new QualificationInfo();
		this.creditCardInfo = new CreditCardInfo();
	}

	public TpPaymentInfo(StudentInfo studentInfo, QualificationInfo qualificationInfo) {
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

//	public BigDecimal getThreeGDataBundleFeeForStudent() {
//		return threeGDataBundleFeeForStudent;
//	}
//
//	public void setThreeGDataBundleFeeForStudent(BigDecimal threeGDataBundleFeeForStudent) {
//		this.threeGDataBundleFeeForStudent = threeGDataBundleFeeForStudent;
//	}

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

	public BigDecimal getStudyFeeAmount() {
		return studyFeeAmount;
	}

	public void setStudyFeeAmount(BigDecimal studyFeeAmount) {
		this.studyFeeAmount = studyFeeAmount;
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

//	public BigDecimal getApplyAmount() {
//		return applyAmount;
//	}
//
//	public void setApplyAmount(BigDecimal applyAmount) {
//		this.applyAmount = applyAmount;
//	}
//
//	public boolean isCanChooseLibraryCard() {
//		return canChooseLibraryCard;
//	}
//
//	public void setCanChooseLibraryCard(boolean canChooseLibraryCard) {
//		this.canChooseLibraryCard = canChooseLibraryCard;
//	}
//
//	public boolean isCanChooseMatric() {
//		return canChooseMatric;
//	}
//
//	public void setCanChooseMatric(boolean canChooseMatric) {
//		this.canChooseMatric = canChooseMatric;
//	}
//
//	public boolean isCanChooseThreeGDataBundle() {
//		return canChooseThreeGDataBundle;
//	}
//
//	public void setCanChooseThreeGDataBundle(boolean canChooseThreeGDataBundle) {
//		this.canChooseThreeGDataBundle = canChooseThreeGDataBundle;
//	}

	public CreditCardInfo getCreditCardInfo() {
		return creditCardInfo;
	}

	public void setCreditCardInfo(CreditCardInfo creditCardInfo) {
		this.creditCardInfo = creditCardInfo;
	}

//	public boolean isCanSmartCardCancel() {
//		return canSmartCardCancel;
//	}
//
//	public void setCanSmartCardCancel(boolean canSmartCardCancel) {
//		this.canSmartCardCancel = canSmartCardCancel;
//	}
//
//	public boolean isCancelSmartCard() {
//		return cancelSmartCard;
//	}
//
//	public void setCancelSmartCard(boolean cancelSmartCard) {
//		this.cancelSmartCard = cancelSmartCard;
//	}

//	public boolean isThreeGDataBundleChoice() {
//		return threeGDataBundleChoice;
//	}
//
//	public void setThreeGDataBundleChoice(boolean threeGDataBundleChoice) {
//		this.threeGDataBundleChoice = threeGDataBundleChoice;
//	}

	public BigDecimal getCreditCardTotalAmountInput() {
		return creditCardTotalAmountInput;
	}

	public void setCreditCardTotalAmountInput(BigDecimal creditCardTotalAmountInput) {
		this.creditCardTotalAmountInput = creditCardTotalAmountInput;
	}
}
