package za.ac.unisa.myadmin.exam.services.jpa.models;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import za.ac.unisa.myadmin.exam.services.dto.ExamAdmissionInfo;
import za.ac.unisa.myadmin.services.utilities.YesOrNoBooleanConverter;

@Entity
@Table(name = "XADDON")
public class ExamAdmissionEntity {

	@EmbeddedId
	private ExamAdmissionEntityId examAdmissionEntityId;

	@Column(name = "MK_EXAM_YEAR", insertable = false, updatable = false)
	private Integer year;

	@Column(name = "MK_EXAM_PERIOD", insertable = false, updatable = false)
	private Integer examPeriodCode;

	@Column(name = "MK_EXAM_TYPE_CODE", insertable = false, updatable = false)
	private Integer examType;

	@Column(name = "ADMISSION_DONE_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean admissionDone;

	@Column(name = "XAMARR_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean examArrangement;

	public ExamAdmissionEntity() {
	}

	public ExamAdmissionEntity(ExamAdmissionInfo dto) {
		this.examAdmissionEntityId = new ExamAdmissionEntityId(dto.getYear(), dto.getExamPeriodCode(),
				dto.getExamType());
		this.fromDto(dto);
	}

	public void fromDto(ExamAdmissionInfo dto) {
		this.year = dto.getYear();
		this.examPeriodCode = dto.getExamPeriodCode();
		this.examType = dto.getExamType();
		this.admissionDone = dto.isAdmissionDone();
		this.examArrangement = dto.isExamArrangement();
	}

	public ExamAdmissionInfo toDto() {
		ExamAdmissionInfo info = new ExamAdmissionInfo();
		info.setYear(this.year);
		info.setExamPeriodCode(this.examPeriodCode);
		info.setExamType(this.examType);
		info.setAdmissionDone(this.admissionDone);
		info.setExamArrangement(this.examArrangement);
		return info;
	}

	public ExamAdmissionEntityId getExamAdmissionEntityId() {
		return examAdmissionEntityId;
	}

	public void setExamAdmissionEntityId(ExamAdmissionEntityId examAdmissionEntityId) {
		this.examAdmissionEntityId = examAdmissionEntityId;
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

	public boolean isAdmissionDone() {
		return admissionDone;
	}

	public void setAdmissionDone(boolean admissionDone) {
		this.admissionDone = admissionDone;
	}

	public boolean isExamArrangement() {
		return examArrangement;
	}

	public void setExamArrangement(boolean examArrangement) {
		this.examArrangement = examArrangement;
	}

}
