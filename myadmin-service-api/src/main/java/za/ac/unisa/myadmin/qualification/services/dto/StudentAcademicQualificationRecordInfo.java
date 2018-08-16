package za.ac.unisa.myadmin.qualification.services.dto;

import java.io.Serializable;
import java.util.Date;


public class StudentAcademicQualificationRecordInfo implements Serializable {

	private static final long serialVersionUID = 1263696536604710816L;

	private Integer studentNumber;

	private String qualificationCode;

	private Date firstRegistrationDate;

	private String qualShortDescription;

	private String qualDisplayDescription;

	private String qualLongDescription;

	private String status;

	private Integer lastRegistrationYear;

	private Date graduationCeremonyDate;

	private boolean auditFlag;

	/*academicRequestEmailFlag values
	 * E - enable button
	 * D - disable button
	 * T - temporary disabled
	 * Enable Academic email buttons*/
	private String academicRequestEmailFlag;

	public StudentAcademicQualificationRecordInfo() {
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public Date getFirstRegistrationDate() {
		return firstRegistrationDate;
	}

	public void setFirstRegistrationDate(Date firstRegistrationDate) {
		this.firstRegistrationDate = firstRegistrationDate;
	}

	public String getQualShortDescription() {
		return qualShortDescription;
	}

	public void setQualShortDescription(String qualShortDescription) {
		this.qualShortDescription = qualShortDescription;
	}

	public String getQualDisplayDescription() {
		return qualDisplayDescription;
	}

	public void setQualDisplayDescription(String qualDisplayDescription) {
		this.qualDisplayDescription = qualDisplayDescription;
	}

	public String getQualLongDescription() {
		return qualLongDescription;
	}

	public void setQualLongDescription(String qualLongDescription) {
		this.qualLongDescription = qualLongDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getLastRegistrationYear() {
		return lastRegistrationYear;
	}

	public void setLastRegistrationYear(Integer lastRegistrationYear) {
		this.lastRegistrationYear = lastRegistrationYear;
	}

	public Date getGraduationCeremonyDate() {
		return graduationCeremonyDate;
	}

	public void setGraduationCeremonyDate(Date graduationCeremonyDate) {
		this.graduationCeremonyDate = graduationCeremonyDate;
	}

	public boolean isAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(boolean auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getAcademicRequestEmailFlag() {
		return academicRequestEmailFlag;
	}

	public void setAcademicRequestEmailFlag(String academicRequestEmailFlag) {
		this.academicRequestEmailFlag = academicRequestEmailFlag;
	}
}

