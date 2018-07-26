package za.ac.unisa.myadmin.qualification.services.dto;

import java.io.Serializable;
import java.util.Date;


public class StudentAcademicQualificationRecordInfo implements Serializable {

	private static final long serialVersionUID = 1263696536604710816L;

	private String qualificationCode;

	private Date firstRegistrationDate;

	private String qualShortDescription;

	private String status;

	private Integer lastRegistrationYear;

	private String graduationCeremonyDate;

	private boolean auditFlag;

	public StudentAcademicQualificationRecordInfo() {
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

	public String getGraduationCeremonyDate() {
		return graduationCeremonyDate;
	}

	public void setGraduationCeremonyDate(String graduationCeremonyDate) {
		this.graduationCeremonyDate = graduationCeremonyDate;
	}

	public boolean isAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(boolean auditFlag) {
		this.auditFlag = auditFlag;
	}
}

