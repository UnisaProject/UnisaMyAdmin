package za.ac.unisa.myadmin.studyquotation.quotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Study quotation.
 */
public class StudyQuotationInfo extends StudyQuotationRequestInfo {

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

	/** studyUnitInfos */
	private final List<StudyUnitInfo> studyUnitInfos = new ArrayList<>();

	/** coolgenErrorMsg property */
	private String coolgenErrorMsg;


	/**
	 * Instantiates a new Study quotation.
	 */
	public StudyQuotationInfo(){}

	/**
	 * Instantiates a new Study quotation.
	 *
	 * @param request the request
	 */
	public StudyQuotationInfo(StudyQuotationRequestInfo request){
		super(request);
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
	public List<StudyUnitInfo> getStudyUnitInfos() {
		return studyUnitInfos;
	}

	/**
	 * Add study unit.
	 *
	 * @param studyUnitInfo the study unit
	 */
	public void addStudyUnit(StudyUnitInfo studyUnitInfo){
		this.studyUnitInfos.add(studyUnitInfo);
	}

	/**
	 * Sets study units.
	 *
	 * @param studyUnitInfos the study units
	 */
	public void setStudyUnitInfos(List<StudyUnitInfo> studyUnitInfos) {
		this.studyUnitInfos.clear();
		if(Objects.nonNull(studyUnitInfos)){
			this.studyUnitInfos.addAll(studyUnitInfos);
		}
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


	/**
	 * Gets coolgenErrorMsg.
	 *
	 * @return
	 */
	public String getCoolgenErrorMsg() {
		return coolgenErrorMsg;
	}

	/**
	 * Sets coolgenErrorMsg.
	 *
	 * @param coolgenErrorMsg
	 */
	public void setCoolgenErrorMsg(String coolgenErrorMsg) {
		this.coolgenErrorMsg = coolgenErrorMsg;
	}
}