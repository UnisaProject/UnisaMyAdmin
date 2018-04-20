package za.ac.unisa.myadmin.exam.paper.details;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExamPaperDetailsInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer year;

	private String courseCode;

	private Integer number;

	private Integer examType;

	private Integer durationDays;

	private Integer durationHours;

	private Integer durationMinutes;

	private BigDecimal multiChoiceFactor;

	private Integer reductionPapers;

	private Integer subMinimunPercentage;

	private Integer nrOfPages;

	private Integer nrOfPapersPrinted;

	private Date resultReleaseDate;

	private Integer paperWeight;

	private String paperType;

	public ExamPaperDetailsInfo() {

	}

	public ExamPaperDetailsInfo(ExamPaperDetailsInfo examPeriodDate) {
		this.year = examPeriodDate.getYear();
		this.courseCode = examPeriodDate.getCourseCode();
		this.number = examPeriodDate.getNumber();
		this.examType = examPeriodDate.getExamType();
		this.durationDays = examPeriodDate.getDurationDays();
		this.durationHours = examPeriodDate.getDurationHours();
		this.durationMinutes = examPeriodDate.getDurationMinutes();
		this.multiChoiceFactor = examPeriodDate.getMultiChoiceFactor();
		this.reductionPapers = examPeriodDate.getReductionPapers();
		this.subMinimunPercentage = examPeriodDate.getSubMinimunPercentage();
		this.nrOfPages = examPeriodDate.getNrOfPages();
		this.nrOfPapersPrinted = examPeriodDate.getNrOfPapersPrinted();
		this.resultReleaseDate = new Date(examPeriodDate.getResultReleaseDate().getTime());
		this.paperWeight = examPeriodDate.getPaperWeight();
		this.paperType = examPeriodDate.getPaperType();
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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

	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	public Integer getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(Integer durationDays) {
		this.durationDays = durationDays;
	}

	public Integer getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(Integer durationHours) {
		this.durationHours = durationHours;
	}

	public Integer getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(Integer durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public BigDecimal getMultiChoiceFactor() {
		return multiChoiceFactor;
	}

	public void setMultiChoiceFactor(BigDecimal multiChoiceFactor) {
		this.multiChoiceFactor = multiChoiceFactor;
	}

	public Integer getReductionPapers() {
		return reductionPapers;
	}

	public void setReductionPapers(Integer reductionPapers) {
		this.reductionPapers = reductionPapers;
	}

	public Integer getSubMinimunPercentage() {
		return subMinimunPercentage;
	}

	public void setSubMinimunPercentage(Integer subMinimunPercentage) {
		this.subMinimunPercentage = subMinimunPercentage;
	}

	public Integer getNrOfPages() {
		return nrOfPages;
	}

	public void setNrOfPages(Integer nrOfPages) {
		this.nrOfPages = nrOfPages;
	}

	public Integer getNrOfPapersPrinted() {
		return nrOfPapersPrinted;
	}

	public void setNrOfPapersPrinted(Integer nrOfPapersPrinted) {
		this.nrOfPapersPrinted = nrOfPapersPrinted;
	}

	public Date getResultReleaseDate() {
		return resultReleaseDate;
	}

	public void setResultReleaseDate(Date resultReleaseDate) {
		this.resultReleaseDate = resultReleaseDate;
	}

	public Integer getPaperWeight() {
		return paperWeight;
	}

	public void setPaperWeight(Integer paperWeight) {
		this.paperWeight = paperWeight;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

}
