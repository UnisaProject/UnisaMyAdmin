package za.ac.unisa.myadmin.registration.services.dto;

import java.io.Serializable;
import java.util.Date;

public class RegistrationPeriodInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer academicYear;

	private Integer semester;

	private String type;

	private Date effectiveDate;

	private Date expirationDate;

	private String owner;

	public RegistrationPeriodInfo() {

	}

	public RegistrationPeriodInfo(RegistrationPeriodInfo info) {
		this.academicYear = info.getAcademicYear();
		this.semester = info.getSemester();
		this.type = info.getType();
		this.owner = info.getOwner();

		if (info.getEffectiveDate() != null) {
			this.effectiveDate = new Date(info.getEffectiveDate().getTime());
		}

		if (info.getExpirationDate() != null) {
			this.expirationDate = new Date(info.getExpirationDate().getTime());
		}
	}

	public Integer getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
