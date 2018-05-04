package za.ac.unisa.myadmin.studyquotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Adrian on 2018-05-04.
 */
public class StudyQuotationInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer academicYear;

	private String countryCode;

	private String qualificationType;

	private String qualificationCode;

	private String libraryCard;

	private String matricExemption;

	private List<String> studyCodes;

	private List<StudyUnit> studyUnits;

	public Integer getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getQualificationType() {
		return qualificationType;
	}

	public void setQualificationType(String qualificationType) {
		this.qualificationType = qualificationType;
	}

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public String getLibraryCard() {
		return libraryCard;
	}

	public void setLibraryCard(String libraryCard) {
		this.libraryCard = libraryCard;
	}

	public String getMatricExemption() {
		return matricExemption;
	}

	public void setMatricExemption(String matricExemption) {
		this.matricExemption = matricExemption;
	}

	public List<String> getStudyCodes() {
		return studyCodes;
	}

	public void setStudyCodes(List<String> studyCodes) {
		this.studyCodes = studyCodes;
	}

	public List<StudyUnit> getStudyUnits() {
		return studyUnits;
	}

	public void setStudyUnits(List<StudyUnit> studyUnits) {
		this.studyUnits = studyUnits;
	}
}
