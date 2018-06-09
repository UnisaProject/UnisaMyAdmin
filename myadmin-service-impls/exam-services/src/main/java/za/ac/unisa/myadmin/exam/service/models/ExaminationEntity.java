package za.ac.unisa.myadmin.exam.service.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import za.ac.unisa.myadmin.exam.dto.ExaminationInfo;

@Entity
@Table(name = "XAMDAT")
public class ExaminationEntity {

	@EmbeddedId
	private ExaminationEntityId examinationEntityId;

	@Column(name = "FK_EXAM_YEAR", insertable = false, updatable = false)
	private Integer year;

	@Column(name = "MK_EXAM_PERIOD_COD", insertable = false, updatable = false)
	private Integer examPeriodCode;

	@Column(name = "FK_STUDY_UNIT_CODE", insertable = false, updatable = false)
	private String courseCode;

	@Column(name = "FK_NR", insertable = false, updatable = false)
	private Integer paperNumber;

	@Column(name = "DATE0")
	private Date examDate;

	@Column(name = "STARTING_TIME")
	private Date examTime;

	@Column(name = "STORE_SHELF_NR")
	private String storeShelfNumber;

	@Column(name = "STRONG_ROOM_SHELF")
	private String strongRoomShelf;

	@Column(name = "MARKREADING_CODE")
	private String markReadingCode;

	@Column(name = "RESULT_RELEASE_DAT")
	private Date resultReleaseDate;

	@Column(name = "SCRIPT_MARK_TOT")
	private Integer scriptMarkTotal;

	@Column(name = "YEAR_MARK_TOT")
	private Integer yearMarkTotal;

	@Column(name = "MARKREAD_TOT")
	private Integer markReadTotal;

	@Column(name = "PAPER_MARK_TOT")
	private Integer paperMarkTotal;

	@Column(name = "BLOCK_ADJUST")
	private Integer blockAdjustment;

	@Column(name = "MARKREAD_ADJUST")
	private Integer markReadAdjustment;

	@Column(name = "RELEASE_TIME")
	private Date releaseTime;

	@Column(name = "LAST_USER")
	private String lastUser;

	@Column(name = "LAST_PROGRAM_GC3")
	private String lastProgram;

	@Column(name = "NO_OF_QUESTIONS")
	private Integer noOfQuestions;

	@Column(name = "CHANGE_REASON_GC8")
	private String changeReason;

	@Column(name = "COMMENT0")
	private String comment;

	@Column(name = "PAPER_WEIGHT")
	private BigDecimal paperWeight;

	@Column(name = "PAPER_SUBMIN")
	private Integer paperSubMin;

	@Column(name = "PAPER_SUBMIN_SUPPL")
	private String paperSubMinSuppl;

	@Column(name = "DURATION_DAYS")
	private Integer durationDays;

	@Column(name = "DURATION_HOURS")
	private Integer durationHours;

	@Column(name = "DURATION_MINUTES")
	private Integer durationMinutes;

	@Column(name = "NO_OF_PAGES")
	private Integer noOfPages;

	@Column(name = "PAPER_TYPE_GC22")
	private String paperType;

	@Column(name = "ELECTIVE_TYPE_GC268")
	private String electiveType;

	public ExaminationEntity() {
	}

	public ExaminationEntity(ExaminationInfo dto) {
		this.examinationEntityId = new ExaminationEntityId(dto.getYear(), dto.getExamPeriodCode(),
				dto.getCourseCode(), dto.getPaperNumber());
		this.fromDto(dto);
	}

	public void fromDto(ExaminationInfo dto) {
		this.blockAdjustment = dto.getBlockAdjustment();
		this.changeReason = dto.getChangeReason();
		this.comment = dto.getComment();
		this.courseCode = dto.getCourseCode();
		if (dto.getExamDate() != null) {
			this.examDate = dto.getExamDate();
		}
		if (dto.getExamTime() != null) {
			this.examTime = dto.getExamTime();
		}
		this.storeShelfNumber = dto.getStoreShelfNumber();
		this.strongRoomShelf = dto.getStrongRoomShelf();
		this.durationDays = dto.getDurationDays();
		this.durationHours = dto.getDurationHours();
		this.durationMinutes = dto.getDurationMinutes();
		this.electiveType = dto.getElectiveType();
		this.examPeriodCode = dto.getExamPeriodCode();
		this.lastProgram = dto.getLastProgram();
		this.lastUser = dto.getLastUser();
		this.markReadAdjustment = dto.getMarkReadAdjustment();
		this.markReadingCode = dto.getMarkReadingCode();
		this.markReadTotal = dto.getMarkReadTotal();
		this.noOfPages = dto.getNoOfPages();
		this.noOfQuestions = dto.getNoOfQuestions();
		this.paperNumber = dto.getPaperNumber();
		this.paperMarkTotal = dto.getPaperMarkTotal();
		this.paperSubMin = dto.getPaperSubMin();
		this.paperSubMinSuppl = dto.getPaperSubMinSuppl();
		this.paperType = dto.getPaperType();
		this.paperWeight = dto.getPaperWeight();
		if (dto.getReleaseTime() != null) {
			this.releaseTime = new Date(dto.getReleaseTime().getTime());
		}
		if (dto.getResultReleaseDate() != null) {
			this.resultReleaseDate = new Date(dto.getResultReleaseDate().getTime());
		}
		this.scriptMarkTotal = dto.getScriptMarkTotal();
		this.year = dto.getYear();
		this.yearMarkTotal = dto.getYearMarkTotal();
	}

	public ExaminationInfo toDto() {
		ExaminationInfo info = new ExaminationInfo();
		info.setBlockAdjustment(this.blockAdjustment);
		info.setChangeReason(this.changeReason);
		info.setComment(this.comment);
		info.setCourseCode(this.courseCode);
		if (this.examDate != null) {
			info.setExamDate(this.examDate);
		}
		if (this.examTime != null) {
			info.setExamTime(this.examTime);
		}
		info.setStoreShelfNumber(this.storeShelfNumber);
		info.setStrongRoomShelf(this.strongRoomShelf);
		info.setDurationDays(this.durationDays);
		info.setDurationHours(this.durationHours);
		info.setDurationMinutes(this.durationMinutes);
		info.setElectiveType(this.electiveType);
		info.setExamPeriodCode(this.examPeriodCode);
		info.setLastProgram(this.lastProgram);
		info.setLastUser(this.lastUser);
		info.setMarkReadAdjustment(this.markReadAdjustment);
		info.setMarkReadingCode(this.markReadingCode);
		info.setMarkReadTotal(this.markReadTotal);
		info.setNoOfPages(this.noOfPages);
		info.setNoOfQuestions(this.noOfQuestions);
		info.setPaperNumber(this.paperNumber);
		info.setPaperMarkTotal(this.paperMarkTotal);
		info.setPaperSubMin(this.paperSubMin);
		info.setPaperSubMinSuppl(this.paperSubMinSuppl);
		info.setPaperType(this.paperType);
		info.setPaperWeight(this.paperWeight);
		if (this.releaseTime != null) {
			info.setReleaseTime(new Date(this.releaseTime.getTime()));
		}
		if (this.resultReleaseDate != null) {
			info.setResultReleaseDate(new Date(this.resultReleaseDate.getTime()));
		}
		info.setScriptMarkTotal(this.scriptMarkTotal);
		info.setYear(this.year);
		info.setYearMarkTotal(this.yearMarkTotal);
		return info;
	}

	public ExaminationEntityId getExaminationEntityId() {
		return examinationEntityId;
	}

	public void setExaminationEntityId(ExaminationEntityId examinationEntityId) {
		this.examinationEntityId = examinationEntityId;
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