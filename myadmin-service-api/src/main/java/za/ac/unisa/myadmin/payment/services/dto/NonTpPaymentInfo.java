package za.ac.unisa.myadmin.payment.services.dto;

import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by dev on 2018-06-04.
 */
public class NonTpPaymentInfo implements Serializable {

	private static final long serialVersionUID = -1411828481195058901L;

	private StudentInfo studentInfo;

	private QualificationInfo qualificationInfo;

	private CreditCardInfo creditCardInfo;

	private String regStatus;

	private String regStatusDescription;

	//StudyFee Balance
	private BigDecimal balance;

	private String creditDebitIndicator;

	private BigDecimal libraryFineBalance;

	private String libCreditDebitIndicator;

	// set NON-TP matric +lib fees
	private boolean canChooseLibraryCard;
	private boolean payLibraryFee;    // tick box indicator
	private BigDecimal libraryFee;
	private boolean canChooseMatric;
	private boolean payMatricFirstAppFee;    // tick box indicator
	private BigDecimal matricFirstAppFee;
	private boolean canChooseThreeGDataBundle;
	private boolean payThreeGDataBundleFee;    // tick box indicator
	private BigDecimal threeGDataBundleFee;

	private BigDecimal libraryFineFee;
	private BigDecimal studyFeeAmount;
	//private BigDecimal applyAmount; //??

	private BigDecimal creditCardTotalAmountInput;

	public NonTpPaymentInfo() {
		this.studentInfo = new StudentInfo();
		this.qualificationInfo = new QualificationInfo();
		this.creditCardInfo = new CreditCardInfo();
	}

	public NonTpPaymentInfo(StudentInfo studentInfo, QualificationInfo qualificationInfo) {
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

//	public BigDecimal getApplyAmount() {
//		return applyAmount;
//	}
//
//	public void setApplyAmount(BigDecimal applyAmount) {
//		this.applyAmount = applyAmount;
//	}

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

	public CreditCardInfo getCreditCardInfo() {
		return creditCardInfo;
	}

	public void setCreditCardInfo(CreditCardInfo creditCardInfo) {
		this.creditCardInfo = creditCardInfo;
	}

	public boolean isPayLibraryFee() {
		return payLibraryFee;
	}

	public void setPayLibraryFee(boolean payLibraryFee) {
		this.payLibraryFee = payLibraryFee;
	}

	public boolean isPayMatricFirstAppFee() {
		return payMatricFirstAppFee;
	}

	public void setPayMatricFirstAppFee(boolean payMatricFirstAppFee) {
		this.payMatricFirstAppFee = payMatricFirstAppFee;
	}

	public boolean isPayThreeGDataBundleFee() {
		return payThreeGDataBundleFee;
	}

	public void setPayThreeGDataBundleFee(boolean payThreeGDataBundleFee) {
		this.payThreeGDataBundleFee = payThreeGDataBundleFee;
	}

	public BigDecimal getStudyFeeAmount() {
		return studyFeeAmount;
	}

	public void setStudyFeeAmount(BigDecimal studyFeeAmount) {
		this.studyFeeAmount = studyFeeAmount;
	}

	public BigDecimal getCreditCardTotalAmountInput() {
		return creditCardTotalAmountInput;
	}

	public void setCreditCardTotalAmountInput(BigDecimal creditCardTotalAmountInput) {
		this.creditCardTotalAmountInput = creditCardTotalAmountInput;
	}
}
