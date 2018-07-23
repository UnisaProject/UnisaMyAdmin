package za.ac.unisa.myadmin.student.services.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RegistrationPeriodEntityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "SEMESTER_PERIOD")
	private int semesterPeriod;

	@Column(name = "ACADEMIC_YEAR")
	private int academicYear;

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
}
