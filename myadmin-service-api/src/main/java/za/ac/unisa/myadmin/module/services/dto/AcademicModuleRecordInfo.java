package za.ac.unisa.myadmin.module.services.dto;

import java.io.Serializable;
import java.util.Date;

public class AcademicModuleRecordInfo implements Serializable {

	private Integer studentNumber;

	private String qualificationCode;

	private Integer academicYear;

	private String studyUnitCode;

	private Integer academicPeriod;

	private String resultTypeDescription;

	private String mark;

	private String studyUnitDescription;

	private Date examDate;

	private Date resultDate;

	public AcademicModuleRecordInfo() {
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

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public String getResultTypeDescription() {
		return resultTypeDescription;
	}

	public void setResultTypeDescription(String resultTypeDescription) {
		this.resultTypeDescription = resultTypeDescription;
	}

	public String getStudyUnitCode() {
		return studyUnitCode;
	}

	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public void setStudyUnitCode(String studyUnitCode) {
		this.studyUnitCode = studyUnitCode;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getStudyUnitDescription() {
		return studyUnitDescription;
	}

	public void setStudyUnitDescription(String studyUnitDescription) {
		this.studyUnitDescription = studyUnitDescription;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
}

