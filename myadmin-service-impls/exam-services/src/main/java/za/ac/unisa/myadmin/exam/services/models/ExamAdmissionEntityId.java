package za.ac.unisa.myadmin.exam.services.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import za.ac.unisa.myadmin.exam.services.dto.ExamAdmissionInfo;

@Embeddable
public class ExamAdmissionEntityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MK_EXAM_YEAR")
	private Integer year;

	@Column(name = "MK_EXAM_PERIOD")
	private Integer examPeriodCode;

	@Column(name = "MK_EXAM_TYPE_CODE")
	private Integer examType;

	public ExamAdmissionEntityId() {

	}

	public ExamAdmissionEntityId(ExamAdmissionInfo dto) {
		this.year = dto.getYear();
		this.examPeriodCode = dto.getExamPeriodCode();
		this.examType = dto.getExamType();
	}

	public ExamAdmissionEntityId(Integer year, Integer examPeriodCode, Integer examType) {
		this.year = year;
		this.examPeriodCode = examPeriodCode;
		this.examType = examType;
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

	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

}
