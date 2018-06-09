package za.ac.unisa.myadmin.exam.service.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import za.ac.unisa.myadmin.exam.dto.ExaminationInfo;

@Embeddable
public class ExaminationEntityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "FK_EXAM_YEAR")
	private Integer year;

	@Column(name = "MK_EXAM_PERIOD_COD")
	private Integer examPeriodCode;

	@Column(name = "FK_STUDY_UNIT_CODE")
	private String courseCode;

	@Column(name = "FK_NR")
	private Integer number;

	public ExaminationEntityId() {

	}

	public ExaminationEntityId(ExaminationInfo dto) {
		this.year = dto.getYear();
		this.examPeriodCode = dto.getExamPeriodCode();
		this.courseCode = dto.getCourseCode();
		this.number = dto.getPaperNumber();
	}

	public ExaminationEntityId(Integer year, Integer examPeriodCode, String courseCode, Integer number) {
		this.year = year;
		this.examPeriodCode = examPeriodCode;
		this.courseCode = courseCode;
		this.number = number;
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

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
