package za.ac.unisa.myadmin.studyquotation.quotation;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class StudyQuotation {

	private static final long serialVersionUID = 1L;

	/** registrationFee property */
	private String registrationFee;

	/** libraryCard property */
	private String libraryCard;

	/** libraryCard property */
	private double libraryCardCost;

	/** matricExemption */
	private String matricExemption;

	/** matricExemption */
	private double matricExemptionCost;

	/** prescribedBooks property */
	private double prescribedBooks;

	/** year property */
	private short academicYear;

	/** qualificationCode property */
	private String qualificationCode;

	/** foreignLevy property */
	private double foreignLevy;

	/** countryCode property */
	private String countryCode;

	/** totalFee property */
	private double totalFee;

	/** paymentDue property */
	private double paymentDue;

	/** qualification property */
	private String qualification;

	/** studyUnits */
	private Vector studyUnits;

	/** studyCodes */
	private List<String> studyCodes;


	public void reset() {
		if (Calendar.getInstance().get(Calendar.MONTH) < 11) {
			academicYear = (short) Calendar.getInstance().get(Calendar.YEAR);
		} else {
			/*Removed for test purposes +1*/
			academicYear = (short) (Calendar.getInstance().get(Calendar.YEAR) + 1 );
		}
		this.setCountryCode("1015");
		this.setQualification("99999");
		this.setLibraryCard("N");
		this.setMatricExemption("N");
	}

	/**
	 * Returns the registrationFee.
	 * @return String
	 */
	public String getRegistrationFee() {
		return registrationFee;
	}

	/**
	 * Set the registrationFee.
	 * @param registrationFee The registrationFee to set
	 */
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}

	/**
	 * Returns the prescribedBooks.
	 * @return String
	 */
	public double getPrescribedBooks() {
		return prescribedBooks;
	}

	/**
	 * Set the prescribedBooks.
	 * @param prescribedBooks The prescribedBooks to set
	 */
	public void setPrescribedBooks(double prescribedBooks) {
		this.prescribedBooks = prescribedBooks;
	}
	/**
	 * Returns the qualificationCode.
	 * @return String
	 */
	public String getQualificationCode() {
		return qualificationCode;
	}

	/**
	 * Set the qualificationCode.
	 * @param qualificationCode The qualificationCode to set
	 */
	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	/**
	 * Returns the foreignLevy.
	 * @return String
	 */
	public double getForeignLevy() {
		return foreignLevy;
	}

	/**
	 * Set the foreignLevy.
	 * @param d The foreignLevy to set
	 */
	public void setForeignLevy(double d) {
		this.foreignLevy = d;
	}

	/**
	 * Returns the countryCode.
	 * @return String
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Set the countryCode.
	 * @param countryCode The countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Returns the totalFee.
	 * @return String
	 */
	public double getTotalFee() {
		return totalFee;
	}

	/**
	 * Set the totalFee.
	 * @param totalFee The totalFee to set
	 */
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	public Vector getStudyUnits() {
		return studyUnits;
	}

	public void setStudyUnits(Vector studyUnits) {
		this.studyUnits = studyUnits;
	}

	public String getMatricExemption() {
		return matricExemption;
	}

	public void setMatricExemption(String matricExemption) {
		this.matricExemption = matricExemption;
	}

	public short getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(short academicYear) {
		this.academicYear = academicYear;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public double getLibraryCardCost() {
		return libraryCardCost;
	}

	public void setLibraryCardCost(double libraryCardCost) {
		this.libraryCardCost = libraryCardCost;
	}

	public String getLibraryCard() {
		return libraryCard;
	}

	public void setLibraryCard(String libraryCard) {
		this.libraryCard = libraryCard;
	}

	public double getMatricExemptionCost() {
		return matricExemptionCost;
	}

	public void setMatricExemptionCost(double matricExemptionCost) {
		this.matricExemptionCost = matricExemptionCost;
	}

	public double getPaymentDue() {
		return paymentDue;
	}

	public void setPaymentDue(double paymentDue) {
		this.paymentDue = paymentDue;
	}

	public List<String> getStudyCodes() {
		return studyCodes;
	}

	public void setStudyCodes(List<String> studyCodes) {
		this.studyCodes.clear();
		if (Objects.nonNull(studyCodes)) {
			this.studyCodes.addAll(studyCodes);
		}
	}
}
