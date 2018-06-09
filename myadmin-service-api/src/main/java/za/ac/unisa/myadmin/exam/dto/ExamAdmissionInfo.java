package za.ac.unisa.myadmin.exam.dto;

import java.io.Serializable;

public class ExamAdmissionInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer year;

	private Integer examPeriodCode;

	private boolean admissionDone;

	private Integer examType;

	private boolean examArrangement;

	public ExamAdmissionInfo() {

	}

	public ExamAdmissionInfo(ExamAdmissionInfo info) {
		this.year = info.getYear();
		this.examPeriodCode = info.getExamPeriodCode();
		this.admissionDone = info.isAdmissionDone();
		this.examType = info.getExamType();
		this.examArrangement = info.isExamArrangement();
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getExamPeriodCode() {
		return examPeriodCode;
	}

	public void setExamPeriodCode(Integer examPeriodCode) {
		this.examPeriodCode = examPeriodCode;
	}

	public boolean isAdmissionDone() {
		return admissionDone;
	}

	public void setAdmissionDone(boolean admissionDone) {
		this.admissionDone = admissionDone;
	}

	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	public boolean isExamArrangement() {
		return examArrangement;
	}

	public void setExamArrangement(boolean examArrangement) {
		this.examArrangement = examArrangement;
	}

}
