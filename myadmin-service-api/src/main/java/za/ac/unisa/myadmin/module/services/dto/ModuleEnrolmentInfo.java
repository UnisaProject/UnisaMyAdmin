package za.ac.unisa.myadmin.module.services.dto;

import java.io.Serializable;

/**
 * Created by Adrian on 2018-06-28.
 */
public class ModuleEnrolmentInfo implements Serializable {

	private static final long serialVersionUID = -3176352211543647932L;

	private Integer studentNumber;

	private Integer academicYear;

	private Integer academicPeriod;

	private String studyUnitCode;

	private String qualificationCode;

	private String statusCode;

	private Integer semesterPeriod;

	private String semesterPeriodCode;

	public ModuleEnrolmentInfo() {
	}

	public String getStudyUnitCode() {
		return studyUnitCode;
	}

	public void setStudyUnitCode(String studyUnitCode) {
		this.studyUnitCode = studyUnitCode;
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

	public Integer getSemesterPeriod() {
		return semesterPeriod;
	}

	public void setSemesterPeriod(Integer semesterPeriod) {
		this.semesterPeriod = semesterPeriod;
	}

	public String getSemesterPeriodCode() {
		return semesterPeriodCode;
	}

	public void setSemesterPeriodCode(String semesterPeriodCode) {
		this.semesterPeriodCode = semesterPeriodCode;
	}

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}
}
