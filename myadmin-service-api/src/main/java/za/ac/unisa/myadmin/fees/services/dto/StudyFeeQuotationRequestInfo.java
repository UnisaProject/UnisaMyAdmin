package za.ac.unisa.myadmin.fees.services.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Study quotation request.
 */
public class StudyFeeQuotationRequestInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** libraryCard property */
	private boolean libraryCard;

	/** matricExemption */
	private boolean matricExemption;

	/** year property */
	private Integer academicYear;

	/** qualificationCode property */
	private String qualificationCode;

	/** countryCode property */
	private String countryCode;

	/** qualification property */
	private String qualification;

	/** studyCodes */
	private List<String> courseCodes;

	/**
	 * Instantiates a new Study quotation request.
	 */
	public StudyFeeQuotationRequestInfo() {

	}

	/**
	 * Instantiates a new Study quotation request.
	 *
	 * @param info the study quotation request
	 */
	public StudyFeeQuotationRequestInfo(StudyFeeQuotationRequestInfo info) {
		this.libraryCard = info.isLibraryCard();
		this.matricExemption = info.isMatricExemption();
		this.academicYear = info.getAcademicYear();
		this.qualificationCode = info.getQualificationCode();
		this.countryCode = info.getCountryCode();
		this.qualification = info.getQualification();
		if (info.getCourseCodes() != null) {
			this.courseCodes = new ArrayList<String>(info.getCourseCodes());
		}
	}

	/**
	 * Returns the qualificationCode.
	 *
	 * @return String qualification code
	 */
	public String getQualificationCode() {
		return qualificationCode;
	}

	/**
	 * Set the qualificationCode.
	 *
	 * @param qualificationCode The qualificationCode to set
	 */
	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}


	/**
	 * Returns the countryCode.
	 *
	 * @return String country code
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Set the countryCode.
	 *
	 * @param countryCode The countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Is matric exemption boolean.
	 *
	 * @return the boolean
	 */
	public boolean isMatricExemption() {
		return matricExemption;
	}

	/**
	 * Sets matric exemption.
	 *
	 * @param matricExemption the matric exemption
	 */
	public void setMatricExemption(boolean matricExemption) {
		this.matricExemption = matricExemption;
	}

	/**
	 * Gets academic year.
	 *
	 * @return the academic year
	 */
	public Integer getAcademicYear() {
		return academicYear;
	}

	/**
	 * Sets academic year.
	 *
	 * @param academicYear the academic year
	 */
	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}

	/**
	 * Gets qualification.
	 *
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * Sets qualification.
	 *
	 * @param qualification the qualification
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * Is library card boolean.
	 *
	 * @return the boolean
	 */
	public boolean isLibraryCard() {
		return libraryCard;
	}

	/**
	 * Sets library card.
	 *
	 * @param libraryCard the library card
	 */
	public void setLibraryCard(boolean libraryCard) {
		this.libraryCard = libraryCard;
	}


	/**
	 * Gets study codes.
	 *
	 * @return the study codes
	 */
	public List<String> getCourseCodes() {
		if (courseCodes == null) {
			courseCodes = new ArrayList<String>();
		}
		return courseCodes;
	}

	/**
	 * Sets study codes.
	 *
	 * @param studyCodes the study codes
	 */
	public void setCourseCodes(List<String> courseCodes) {
		this.courseCodes = courseCodes;
	}
}
