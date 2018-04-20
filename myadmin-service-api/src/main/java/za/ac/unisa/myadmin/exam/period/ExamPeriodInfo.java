package za.ac.unisa.myadmin.exam.period;

import java.io.Serializable;

import za.ac.unisa.myadmin.common.dto.DescriptionInfo;

public class ExamPeriodInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code;

	private DescriptionInfo description;

	private DescriptionInfo shortDescription;

	private Integer examYear;

	private String examType;

	public ExamPeriodInfo() {
	}

	public ExamPeriodInfo(ExamPeriodInfo examPeriod) {
		this.code = examPeriod.getCode();
		this.description = examPeriod.getDescription();
		this.shortDescription = examPeriod.getShortDescription();
	}

	public ExamPeriodInfo(Integer code, DescriptionInfo description, DescriptionInfo shortDescription) {
		this.code = code;
		this.description = description;
		this.shortDescription = shortDescription;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public DescriptionInfo getDescription() {
		return description;
	}

	public void setDescription(DescriptionInfo description) {
		this.description = description;
	}

	public DescriptionInfo getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(DescriptionInfo shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Integer getExamYear() {
		return examYear;
	}

	public void setExamYear(Integer examYear) {
		this.examYear = examYear;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

}
