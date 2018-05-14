package za.ac.unisa.myadmin.service.studyquotation.date.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "REGDAT")
public class RegistrationDateEntity implements Serializable {

	@EmbeddedId
	private RegistrationDateIdentity registrationDateIdentity;

	@Column(name = "TO_DATE")
	private Date toDate;

	@Column(name = "FROM_DATE")
	private Date fromDate;

	@Column(name = "TYPE", insertable = false, updatable = false)
	private String type;

	@Column(name = "SEMESTER_PERIOD", insertable = false, updatable = false)
	private int semesterPeriod;

	@Column(name = "ACADEMIC_YEAR", insertable = false, updatable = false)
	private int academicYear;

	@Column(name = "OWNER_GC82")
	private String owner;

	/**
	 * Getter for property 'toDate'.
	 *
	 * @return Value for property 'toDate'.
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * Setter for property 'toDate'.
	 *
	 * @param toDate Value to set for property 'toDate'.
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * Getter for property 'fromDate'.
	 *
	 * @return Value for property 'fromDate'.
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * Setter for property 'fromDate'.
	 *
	 * @param fromDate Value to set for property 'fromDate'.
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
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
	public RegistrationDateIdentity getRegistrationDateIdentity() {
		return registrationDateIdentity;
	}

	/**
	 * Setter for property 'studyQuoteDateIdentity'.
	 *
	 * @param registrationDateIdentity Value to set for property 'studyQuoteDateIdentity'.
	 */

	public void setRegistrationDateIdentity(RegistrationDateIdentity registrationDateIdentity) {
		this.registrationDateIdentity = registrationDateIdentity;
	}

}
