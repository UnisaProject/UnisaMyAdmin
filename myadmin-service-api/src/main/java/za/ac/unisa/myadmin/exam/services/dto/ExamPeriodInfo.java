package za.ac.unisa.myadmin.exam.services.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import za.ac.unisa.myadmin.common.dto.DescriptionInfo;

/**
 * The type Exam period info.
 */
public class ExamPeriodInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code;

	private Set<DescriptionInfo> descriptionInfo = new HashSet<>();

	private Integer examYear;

	private String examType;

	/**
	 * Instantiates a new Exam period info.
	 */
	public ExamPeriodInfo() {
	}

	/**
	 * Instantiates a new Exam period info.
	 *
	 * @param info the exam period
	 */
	public ExamPeriodInfo(ExamPeriodInfo info) {
		this.code = info.getCode();
		this.descriptionInfo = info.getDescriptionInfo();
	}

	/**
	 * Instantiates a new Exam period info.
	 *
	 * @param code the code
	 * @param descriptionInfo the description info
	 */
	public ExamPeriodInfo(Integer code, Set<DescriptionInfo> descriptionInfo) {
		this.code = code;
		this.descriptionInfo = descriptionInfo;
	}

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Sets code.
	 *
	 * @param code the code
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * Getter for property 'descriptionInfo'.
	 *
	 * @return Value for property 'descriptionInfo'.
	 */
	public Set<DescriptionInfo> getDescriptionInfo() {
		return descriptionInfo;
	}

	/**
	 * Add description info.
	 *
	 * @param descriptionInfo the description info
	 */
	public void addDescriptionInfo(DescriptionInfo descriptionInfo){
		this.descriptionInfo.add(descriptionInfo);
	}

	/**
	 * Setter for property 'descriptionInfo'.
	 *
	 * @param descriptionInfo Value to set for property 'descriptionInfo'.
	 */
	public void setDescriptionInfo(Set<DescriptionInfo> descriptionInfo) {
		this.descriptionInfo.clear();
		if(Objects.nonNull(descriptionInfo)) {
			this.descriptionInfo.addAll(descriptionInfo);
		}
	}

	/**
	 * Gets exam year.
	 *
	 * @return the exam year
	 */
	public Integer getExamYear() {
		return examYear;
	}

	/**
	 * Sets exam year.
	 *
	 * @param examYear the exam year
	 */
	public void setExamYear(Integer examYear) {
		this.examYear = examYear;
	}

	/**
	 * Gets exam type.
	 *
	 * @return the exam type
	 */
	public String getExamType() {
		return examType;
	}

	/**
	 * Sets exam type.
	 *
	 * @param examType the exam type
	 */
	public void setExamType(String examType) {
		this.examType = examType;
	}

}
