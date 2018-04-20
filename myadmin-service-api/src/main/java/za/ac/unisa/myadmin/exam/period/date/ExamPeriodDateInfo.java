package za.ac.unisa.myadmin.exam.period.date;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExamPeriodDateInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer year;

	private Integer examPeriodCode;

	private String courseCode;

	private Integer paperNumber;

	private Date examDate;

	private Date examTime;

	private String storeShelfNumber;

	private String strongRoomShelf;

	private String markReadingCode;

	private Date resultReleaseDate;

	private Integer scriptMarkTotal;

	private Integer yearMarkTotal;

	private Integer markReadTotal;

	private Integer paperMarkTotal;

	private Integer blockAdjustment;

	private Integer markReadAdjustment;

	private Date releaseTime;

	private String lastUser;

	private String lastProgram;

	private Integer noOfQuestions;

	private String changeReason;

	private String comment;

	private BigDecimal paperWeight;

	private Integer paperSubMin;

	private String paperSubMinSuppl;

	private Integer durationDays;

	private Integer durationHours;

	private Integer durationMinutes;

	private Integer noOfPages;

	private String paperType;

	private String electiveType;

	public ExamPeriodDateInfo() {

	}

	public ExamPeriodDateInfo(ExamPeriodDateInfo examPeriodDate) {
		this.year = examPeriodDate.getYear();
		this.examPeriodCode = examPeriodDate.getExamPeriodCode();
		this.courseCode = examPeriodDate.getCourseCode();
		this.paperNumber = examPeriodDate.getPaperNumber();
		if (examPeriodDate.getExamDate() != null) {
			this.examDate = new Date(examPeriodDate.getExamDate().getTime());
		}
		if (examPeriodDate.getExamTime() != null) {
			this.examTime = new Date(examPeriodDate.getExamTime().getTime());
		}
		this.storeShelfNumber = examPeriodDate.getStoreShelfNumber();
		this.strongRoomShelf = examPeriodDate.getStrongRoomShelf();
		this.markReadingCode = examPeriodDate.getMarkReadingCode();
		if (examPeriodDate.getResultReleaseDate() != null) {
			this.resultReleaseDate = new Date(examPeriodDate.getResultReleaseDate().getTime());
		}
		this.scriptMarkTotal = examPeriodDate.getScriptMarkTotal();
		this.yearMarkTotal = examPeriodDate.getYearMarkTotal();
		this.markReadTotal = examPeriodDate.getMarkReadTotal();
		this.paperMarkTotal = examPeriodDate.getPaperMarkTotal();
		this.blockAdjustment = examPeriodDate.getBlockAdjustment();
		this.markReadAdjustment = examPeriodDate.getMarkReadAdjustment();
		if (examPeriodDate.getReleaseTime() != null) {
			this.releaseTime = new Date(examPeriodDate.getReleaseTime().getTime());
		}
		this.lastUser = examPeriodDate.getLastUser();
		this.lastProgram = examPeriodDate.getLastProgram();
		this.noOfQuestions = examPeriodDate.getNoOfQuestions();
		this.changeReason = examPeriodDate.getChangeReason();
		this.comment = examPeriodDate.getComment();
		this.paperWeight = examPeriodDate.getPaperWeight();
		this.paperSubMin = examPeriodDate.getPaperSubMin();
		this.paperSubMinSuppl = examPeriodDate.getPaperSubMinSuppl();
		this.durationDays = examPeriodDate.getDurationDays();
		this.durationHours = examPeriodDate.getDurationHours();
		this.durationMinutes = examPeriodDate.getDurationMinutes();
		this.noOfPages = examPeriodDate.getNoOfPages();
		this.paperType = examPeriodDate.getPaperType();
		this.electiveType = examPeriodDate.getElectiveType();
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

	public Integer getPaperNumber() {
		return paperNumber;
	}

	public void setPaperNumber(Integer paperNumber) {
		this.paperNumber = paperNumber;
	}

	public String getStrongRoomShelf() {
		return strongRoomShelf;
	}

	public void setStrongRoomShelf(String strongRoomShelf) {
		this.strongRoomShelf = strongRoomShelf;
	}

	public String getMarkReadingCode() {
		return markReadingCode;
	}

	public void setMarkReadingCode(String markReadingCode) {
		this.markReadingCode = markReadingCode;
	}

	public Date getResultReleaseDate() {
		return resultReleaseDate;
	}

	public void setResultReleaseDate(Date resultReleaseDate) {
		this.resultReleaseDate = resultReleaseDate;
	}

	public Integer getScriptMarkTotal() {
		return scriptMarkTotal;
	}

	public void setScriptMarkTotal(Integer scriptMarkTotal) {
		this.scriptMarkTotal = scriptMarkTotal;
	}

	public Integer getYearMarkTotal() {
		return yearMarkTotal;
	}

	public void setYearMarkTotal(Integer yearMarkTotal) {
		this.yearMarkTotal = yearMarkTotal;
	}

	public Integer getMarkReadTotal() {
		return markReadTotal;
	}

	public void setMarkReadTotal(Integer markReadTotal) {
		this.markReadTotal = markReadTotal;
	}

	public Integer getPaperMarkTotal() {
		return paperMarkTotal;
	}

	public void setPaperMarkTotal(Integer paperMarkTotal) {
		this.paperMarkTotal = paperMarkTotal;
	}

	public Integer getBlockAdjustment() {
		return blockAdjustment;
	}

	public void setBlockAdjustment(Integer blockAdjustment) {
		this.blockAdjustment = blockAdjustment;
	}

	public Integer getMarkReadAdjustment() {
		return markReadAdjustment;
	}

	public void setMarkReadAdjustment(Integer markReadAdjustment) {
		this.markReadAdjustment = markReadAdjustment;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getLastUser() {
		return lastUser;
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}

	public String getLastProgram() {
		return lastProgram;
	}

	public void setLastProgram(String lastProgram) {
		this.lastProgram = lastProgram;
	}

	public Integer getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(Integer noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getPaperWeight() {
		return paperWeight;
	}

	public void setPaperWeight(BigDecimal paperWeight) {
		this.paperWeight = paperWeight;
	}

	public Integer getPaperSubMin() {
		return paperSubMin;
	}

	public void setPaperSubMin(Integer paperSubMin) {
		this.paperSubMin = paperSubMin;
	}

	public String getPaperSubMinSuppl() {
		return paperSubMinSuppl;
	}

	public void setPaperSubMinSuppl(String paperSubMinSuppl) {
		this.paperSubMinSuppl = paperSubMinSuppl;
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

	public Integer getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(Integer noOfPages) {
		this.noOfPages = noOfPages;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getElectiveType() {
		return electiveType;
	}

	public void setElectiveType(String electiveType) {
		this.electiveType = electiveType;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Date getExamTime() {
		return examTime;
	}

	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}

	public String getStoreShelfNumber() {
		return storeShelfNumber;
	}

	public void setStoreShelfNumber(String storeShelfNumber) {
		this.storeShelfNumber = storeShelfNumber;
	}

}
