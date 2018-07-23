package za.ac.unisa.myadmin.student.services.dto;

import java.io.Serializable;

/**
 * Created by Adrian on 2018-06-26.
 */
public class StudentAnnualInfo implements Serializable {

	private static final long serialVersionUID = 1058017142460191890L;

	private Integer studentNumber;

	private Integer academicYear;

	private Integer academicPeriod;

	private String highestQualification;

	public StudentAnnualInfo() {
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}

	public Integer getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}

	public Integer getAcademicPeriod() {
		return academicPeriod;
	}

	public void setAcademicPeriod(Integer academicPeriod) {
		this.academicPeriod = academicPeriod;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}
}
