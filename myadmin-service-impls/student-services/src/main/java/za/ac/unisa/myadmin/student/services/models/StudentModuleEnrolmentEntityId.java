package za.ac.unisa.myadmin.student.services.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StudentModuleEnrolmentEntityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "FK_ACADEMIC_YEAR")
	private Integer academicYear;

	@Column(name = "FK_ACADEMIC_PERIOD")
	private Integer academicPeriod;

	@Column(name = "MK_STUDY_UNIT_CODE")
	private String studyUnitCode;

	@Column(name = "FK_STUDENT_NR")
	private Integer studentNumber;

	@Column(name = "SEMESTER_PERIOD")
	private Integer semesterPeriod;

	public StudentModuleEnrolmentEntityId() {
	}

	public StudentModuleEnrolmentEntityId(Integer academicYear, Integer academicPeriod, String studyUnitCode, Integer studentNumber, Integer semesterPeriod) {
		this.academicYear = academicYear;
		this.academicPeriod = academicPeriod;
		this.studyUnitCode = studyUnitCode;
		this.studentNumber = studentNumber;
		this.semesterPeriod = semesterPeriod;
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

	public String getStudyUnitCode() {
		return studyUnitCode;
	}

	public void setStudyUnitCode(String studyUnitCode) {
		this.studyUnitCode = studyUnitCode;
	}

	public Integer getSemesterPeriod() {
		return semesterPeriod;
	}

	public void setSemesterPeriod(Integer semesterPeriod) {
		this.semesterPeriod = semesterPeriod;
	}

	@Override
	public String toString() {
		return "StudentModuleEnrolmentEntityId{" +
			"academicYear=" + academicYear +
			", academicPeriod=" + academicPeriod +
			", studyUnitCode='" + studyUnitCode + '\'' +
			", studentNumber=" + studentNumber +
			", semesterPeriod=" + semesterPeriod +
			'}';
	}
}
