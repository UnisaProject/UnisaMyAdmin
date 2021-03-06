package za.ac.unisa.myadmin.fees.services.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Study quotation.
 */
public class StudyFeeQuotationInfo extends StudyFeeQuotationRequestInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** registrationFee property */
	private String registrationFee;

	/** libraryCard property */
	private double libraryCardCost;

	/** matricExemption */
	private double matricExemptionCost;

	/** prescribedBooks property */
	private double prescribedBooks;

	/** foreignLevy property */
	private double foreignLevy;

	/** totalFee property */
	private double totalFee;

	/** paymentDue property */
	private double paymentDue;

	private String message;

	/** studyUnitInfos */
	private List<StudyUnitInfo> studyUnits;

	/**
	 * Instantiates a new Study quotation.
	 */
	public StudyFeeQuotationInfo() {

	}

	public StudyFeeQuotationInfo(StudyFeeQuotationRequestInfo requestInfo) {
		super(requestInfo);
	}

	public StudyFeeQuotationInfo(StudyFeeQuotationInfo info) {
		super(info);
		this.registrationFee = info.getRegistrationFee();
		this.libraryCardCost = info.getLibraryCardCost();
		this.matricExemptionCost = info.getMatricExemptionCost();
		this.prescribedBooks = info.getPrescribedBooks();
		this.foreignLevy = info.getForeignLevy();
		this.totalFee = info.getTotalFee();
		this.paymentDue = info.getPaymentDue();
		this.message = info.getMessage();
		if (info.getStudyUnits() != null) {
			this.studyUnits = new ArrayList<StudyUnitInfo>(info.getStudyUnits());
		}
	}


	/**
	 * Returns the registrationFee.
	 *
	 * @return String registration fee
	 */
	public String getRegistrationFee() {
		return registrationFee;
	}

	/**
	 * Set the registrationFee.
	 *
	 * @param registrationFee The registrationFee to set
	 */
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}

	/**
	 * Returns the prescribedBooks.
	 *
	 * @return String prescribed books
	 */
	public double getPrescribedBooks() {
		return prescribedBooks;
	}

	/**
	 * Set the prescribedBooks.
	 *
	 * @param prescribedBooks The prescribedBooks to set
	 */
	public void setPrescribedBooks(double prescribedBooks) {
		this.prescribedBooks = prescribedBooks;
	}


	/**
	 * Returns the foreignLevy.
	 *
	 * @return String foreign levy
	 */
	public double getForeignLevy() {
		return foreignLevy;
	}

	/**
	 * Set the foreignLevy.
	 *
	 * @param d The foreignLevy to set
	 */
	public void setForeignLevy(double d) {
		this.foreignLevy = d;
	}


	/**
	 * Returns the totalFee.
	 *
	 * @return String total fee
	 */
	public double getTotalFee() {
		return totalFee;
	}

	/**
	 * Set the totalFee.
	 *
	 * @param totalFee The totalFee to set
	 */
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * Gets study units.
	 *
	 * @return the study units
	 */
	public List<StudyUnitInfo> getStudyUnits() {
		if (studyUnits == null) {
			studyUnits = new ArrayList<StudyUnitInfo>();
		}
		return studyUnits;
	}

	/**
	 * Sets study units.
	 *
	 * @param studyUnitInfos the study units
	 */
	public void setStudyUnitInfos(List<StudyUnitInfo> studyUnits) {
		this.studyUnits = studyUnits;
	}

	/**
	 * Gets library card cost.
	 *
	 * @return the library card cost
	 */
	public double getLibraryCardCost() {
		return libraryCardCost;
	}

	/**
	 * Sets library card cost.
	 *
	 * @param libraryCardCost the library card cost
	 */
	public void setLibraryCardCost(double libraryCardCost) {
		this.libraryCardCost = libraryCardCost;
	}


	/**
	 * Gets matric exemption cost.
	 *
	 * @return the matric exemption cost
	 */
	public double getMatricExemptionCost() {
		return matricExemptionCost;
	}

	/**
	 * Sets matric exemption cost.
	 *
	 * @param matricExemptionCost the matric exemption cost
	 */
	public void setMatricExemptionCost(double matricExemptionCost) {
		this.matricExemptionCost = matricExemptionCost;
	}

	/**
	 * Gets payment due.
	 *
	 * @return the payment due
	 */
	public double getPaymentDue() {
		return paymentDue;
	}

	/**
	 * Sets payment due.
	 *
	 * @param paymentDue the payment due
	 */
	public void setPaymentDue(double paymentDue) {
		this.paymentDue = paymentDue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
