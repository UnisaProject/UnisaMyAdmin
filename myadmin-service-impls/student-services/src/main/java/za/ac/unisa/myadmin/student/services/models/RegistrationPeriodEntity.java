package za.ac.unisa.myadmin.student.services.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import za.ac.unisa.myadmin.student.services.dto.RegistrationPeriodInfo;

@Entity
@Table(name = "REGDAT")
public class RegistrationPeriodEntity {

	@EmbeddedId
	private RegistrationPeriodEntityId registrationPeriodEntityId;

	@Column(name = "FROM_DATE")
	private Date effectiveDate;

	@Column(name = "TO_DATE")
	private Date expirationDate;

	@Column(name = "TYPE", insertable = false, updatable = false)
	private String type;

	@Column(name = "SEMESTER_PERIOD", insertable = false, updatable = false)
	private int semesterPeriod;

	@Column(name = "ACADEMIC_YEAR", insertable = false, updatable = false)
	private int academicYear;

	@Column(name = "OWNER_GC82")
	private String owner;

	public RegistrationPeriodEntity() {
	}

	public RegistrationPeriodInfo toDto() {
		RegistrationPeriodInfo info = new RegistrationPeriodInfo();
		info.setAcademicYear(this.academicYear);
		info.setSemester(this.semesterPeriod);
		info.setType(this.type);
		info.setOwner(this.getOwner());
		if (this.effectiveDate != null) {
			info.setEffectiveDate(new Date(this.effectiveDate.getTime()));
		}
		if (this.expirationDate != null) {
			info.setExpirationDate(new Date(this.expirationDate.getTime()));
		}
		return info;
	}

	/**
	 * Getter for property 'fromDate'.
	 *
	 * @return Value for property 'fromDate'.
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * Setter for property 'fromDate'.
	 *
	 * @param fromDate Value to set for property 'fromDate'.
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Getter for property 'toDate'.
	 *
	 * @return Value for property 'toDate'.
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Setter for property 'toDate'.
	 *
	 * @param toDate Value to set for property 'toDate'.
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Getter for property 'type'.
	 *
	 * @return Value for property 'type'.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter for property 'type'.
	 *
	 * @param type Value to set for property 'type'.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter for property 'semesterPeriod'.
	 *
	 * @return Value for property 'semesterPeriod'.
	 */
	public int getSemesterPeriod() {
		return semesterPeriod;
	}

	/**
	 * Setter for property 'semesterPeriod'.
	 *
	 * @param semesterPeriod Value to set for property 'semesterPeriod'.
	 */
	public void setSemesterPeriod(int semesterPeriod) {
		this.semesterPeriod = semesterPeriod;
	}

	/**
	 * Getter for property 'academicYear'.
	 *
	 * @return Value for property 'academicYear'.
	 */
	public int getAcademicYear() {
		return academicYear;
	}

	/**
	 * Setter for property 'academicYear'.
	 *
	 * @param academicYear Value to set for property 'academicYear'.
	 */
	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

	/**
	 * Getter for property 'owner'.
	 *
	 * @return Value for property 'owner'.
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Setter for property 'owner'.
	 *
	 * @param owner Value to set for property 'owner'.
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Getter for property 'registrationDateIdentity'.
	 *
	 * @return Value for property 'registrationDateIdentity'.
	 */
	public RegistrationPeriodEntityId getRegistrationPeriodEntityId() {
		return registrationPeriodEntityId;
	}

	/**
	 * Setter for property 'studyQuoteDateIdentity'.
	 *
	 * @param registrationDateIdentity Value to set for property 'studyQuoteDateIdentity'.
	 */

	public void setRegistrationPeriodEntityId(RegistrationPeriodEntityId registrationPeriodEntityId) {
		this.registrationPeriodEntityId = registrationPeriodEntityId;
	}

}
