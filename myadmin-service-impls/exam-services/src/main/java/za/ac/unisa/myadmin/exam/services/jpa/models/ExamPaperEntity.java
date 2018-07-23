package za.ac.unisa.myadmin.exam.services.jpa.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import za.ac.unisa.myadmin.exam.services.dto.ExamPaperInfo;

@Entity
@Table(name = "XAMPAP")
public class ExamPaperEntity {

	@EmbeddedId
	private ExamPaperEntityId examPaperEntityId;

	@Column(name = "EXAM_YEAR", insertable = false, updatable = false)
	private Integer year;

	@Column(name = "MK_STUDY_UNIT_CODE", insertable = false, updatable = false)
	private String courseCode;

	@Column(name = "NR")
	private Integer number;

	@Column(name = "MK_EXAM_TYPE_CODE")
	private Integer examType;

	@Column(name = "DURATION_DAYS")
	private Integer durationDays;

	@Column(name = "DURATION_HOURS")
	private Integer durationHours;

	@Column(name = "DURATION_MINUTES")
	private Integer durationMinutes;

	@Column(name = "MULTI_CHOICE_FACTO")
	private BigDecimal multiChoiceFactor;

	@Column(name = "REDUCTION_PAPERS_P")
	private Integer reductionPapers;

	@Column(name = "SUB_MINIMUM_PERCEN")
	private Integer subMinimumPercentage;

	@Column(name = "NR_OF_PAGES")
	private Integer nrOfPages;

	@Column(name = "NR_PAPERS_PRINTED")
	private Integer nrOfPapersPrinted;

	@Column(name = "RESULT_RELEASE_DAT")
	private Date resultReleaseDate;

	@Column(name = "PAPER_WEIGHT")
	private Integer paperWeight;

	@Column(name = "PAPER_TYPE_GC22")
	private String paperType;

	public ExamPaperEntity() {
	}

	public ExamPaperEntity(ExamPaperInfo dto) {
		this.examPaperEntityId = new ExamPaperEntityId(dto.getYear(), dto.getCourseCode());
		this.fromDto(dto);
	}

	public void fromDto(ExamPaperInfo dto) {
		this.year = dto.getYear();
		this.courseCode = dto.getCourseCode();
		this.durationDays = dto.getDurationDays();
		this.durationHours = dto.getDurationHours();
		this.durationMinutes = dto.getDurationMinutes();
		this.number = dto.getNumber();
		this.paperType = dto.getPaperType();
		this.paperWeight = dto.getPaperWeight();
		if (dto.getResultReleaseDate() != null) {
			this.resultReleaseDate = new Date(dto.getResultReleaseDate().getTime());
		}
		this.examType = dto.getExamType();
		this.multiChoiceFactor = dto.getMultiChoiceFactor();
		this.reductionPapers = dto.getReductionPapers();
		this.subMinimumPercentage = dto.getSubMinimunPercentage();
		this.nrOfPages = dto.getNrOfPages();
		this.nrOfPapersPrinted = dto.getNrOfPapersPrinted();
	}

	public ExamPaperInfo toDto() {
		ExamPaperInfo info = new ExamPaperInfo();
		info.setYear(this.year);
		info.setCourseCode(this.courseCode);
		info.setDurationDays(this.durationDays);
		info.setDurationHours(this.durationHours);
		info.setDurationMinutes(this.durationMinutes);
		info.setNumber(this.number);
		info.setPaperType(this.paperType);
		info.setPaperWeight(this.paperWeight);
		if (this.resultReleaseDate != null) {
			info.setResultReleaseDate(new Date(this.resultReleaseDate.getTime()));
		}
		info.setExamType(this.examType);
		info.setMultiChoiceFactor(this.multiChoiceFactor);
		info.setReductionPapers(this.reductionPapers);
		info.setSubMinimunPercentage(this.subMinimumPercentage);
		info.setNrOfPages(this.nrOfPages);
		info.setNrOfPapersPrinted(this.nrOfPapersPrinted);
		return info;
	}

	public ExamPaperEntityId getExamPaperEntityId() {
		return examPaperEntityId;
	}

	public void setExamPaperEntityId(ExamPaperEntityId examPaperEntityId) {
		this.examPaperEntityId = examPaperEntityId;
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

	public Integer getSubMinimumPercentage() {
		return subMinimumPercentage;
	}

	public void setSubMinimumPercentage(Integer subMinimumPercentage) {
		this.subMinimumPercentage = subMinimumPercentage;
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