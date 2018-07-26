package za.ac.unisa.myadmin.module.services.dto;

import java.io.Serializable;
import java.util.Date;

public class StudentAcademicStudyUnitResultInfo implements Serializable {

	private String resultTypeDescr;

	private String studyUnit;

	private String mark;

	private String studyUnitDescription;

	private Date examDate;

	public StudentAcademicStudyUnitResultInfo() {
	}

	public String getResultTypeDescr() {
		return resultTypeDescr;
	}

	public void setResultTypeDescr(String resultTypeDescr) {
		this.resultTypeDescr = resultTypeDescr;
	}

	public String getStudyUnit() {
		return studyUnit;
	}

	public void setStudyUnit(String studyUnit) {
		this.studyUnit = studyUnit;
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

