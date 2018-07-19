package za.ac.unisa.myadmin.student.services.jpa.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class StudentAnnualEntityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="MK_ACADEMIC_YEAR")
	private Integer academicYear;

	@Column(name="MK_ACADEMIC_PERIOD")
	private Integer academicPeriod;

	@Column(name="MK_STUDENT_NR")
	private Integer studentNumber;

	public StudentAnnualEntityId() {
	}

	public StudentAnnualEntityId(Integer academicYear, Integer academicPeriod, Integer studentNumber) {
		this.academicYear = academicYear;
		this.academicPeriod = academicPeriod;
		this.studentNumber = studentNumber;
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
}
