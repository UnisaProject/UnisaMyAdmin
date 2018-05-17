package za.ac.unisa.myadmin.studyquotation.quotation;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * The type Study quotation request.
 */
public class StudyQuotationRequestInfo {

	private static final long serialVersionUID = 1L;

	/** libraryCard property */
	private boolean libraryCard;

	/** matricExemption */
	private boolean matricExemption;

	/** year property */
	private int academicYear;

	/** qualificationCode property */
	private String qualificationCode;

	/** countryCode property */
	private String countryCode;

	/** qualification property */
	private String qualification;

	/** studyCodes */
	private final List<String> studyCodes = new ArrayList<>();

	/**
	 * Instantiates a new Study quotation request.
	 */
	public StudyQuotationRequestInfo() {
	}

	/**
	 * Instantiates a new Study quotation request.
	 *
	 * @param studyQuotationRequestInfo the study quotation request
	 */
	public StudyQuotationRequestInfo(StudyQuotationRequestInfo studyQuotationRequestInfo) {
		this.setLibraryCard(studyQuotationRequestInfo.isLibraryCard());
		this.setMatricExemption(studyQuotationRequestInfo.isMatricExemption());
		this.setAcademicYear(studyQuotationRequestInfo.getAcademicYear());
		this.setQualificationCode(studyQuotationRequestInfo.getQualificationCode());
		this.setCountryCode(studyQuotationRequestInfo.getCountryCode());
		this.setQualification(studyQuotationRequestInfo.getQualification());
		this.setStudyCodes(studyQuotationRequestInfo.getStudyCodes());
	}

	/**
	 * Reset.
	 */
	public void reset() {
		if (Calendar.getInstance().get(Calendar.MONTH) < 11) {
			academicYear = Instant.now().get(ChronoField.YEAR);
		} else {
			/*Removed for test purposes +1*/
			academicYear = (Calendar.getInstance().get(Calendar.YEAR) + 1 );
		}
		this.setCountryCode("1015");
		this.setQualification("99999");
		this.setLibraryCard(false);
		this.setMatricExemption(false);
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
	public int getAcademicYear() {
		return academicYear;
	}

	/**
	 * Sets academic year.
	 *
	 * @param academicYear the academic year
	 */
	public void setAcademicYear(int academicYear) {
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
	public List<String> getStudyCodes() {
		return studyCodes;
	}

	/**
	 * Sets study codes.
	 *
	 * @param studyCodes the study codes
	 */
	public void setStudyCodes(List<String> studyCodes) {
		this.studyCodes.clear();
		if (Objects.nonNull(studyCodes)) {
			this.studyCodes.addAll(studyCodes);
		}
	}
}
