package za.ac.unisa.myadmin.student.services.jpa.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.services.utilities.YesOrNoBooleanConverter;

/**
 * The persistent class for the STUSUN database table.
 */
@Entity
@Table(name = "STUSUN")
public class ModuleEnrolmentEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	ModuleEnrolmentEntityId moduleEnrolmentEntityId;

	@Column(name = "MK_STUDY_UNIT_CODE", insertable = false, updatable = false)
	private String studyUnitCode;

	@Column(name = "MK_COURSE_STUDY_UN")
	private String courseStudyUnit;

	@Column(name = "MK_QUALIFICATION_C")
	private String qualificationCode;

	@Column(name = "MK_EXAM_CENTRE_COD")
	private String mkExamCentreCode;

	@Column(name = "MK_EXAM_PERIOD")
	private Integer examPeriod;

	@Column(name = "MK_STUDY_UNIT_OPTI")
	private String studyUnitOption;

	@Column(name = "FK_STUDENT_NR", insertable = false, updatable = false)
	private Integer studentNumber;

	@Column(name = "FK_ACADEMIC_YEAR", insertable = false, updatable = false)
	private Integer academicYear;

	@Column(name = "FK_ACADEMIC_PERIOD", insertable = false, updatable = false)
	private Integer academicPeriod;

	@Column(name = "SEMESTER_PERIOD", insertable = false, updatable = false)
	private Integer semesterPeriod;

	@Column(name = "ASSIGNMENTS_FLAG")
	private String assignmentsFlag;

	@Temporal(TemporalType.DATE)
	@Column(name = "CANCELLATION_DATE")
	private Date cancellationDate;

	@Column(name = "CONTACT_FLAG")
	private String contactFlag;

	@Column(name = "DESPATCH_COMMENT")
	private String despatchComment;

	@Column(name = "DISCUSSION_CLASS")
	private String discussionClass;

	@Column(name = "EXAM_ADMISSION_COD")
	private Integer examAdmissionCode;

	@Column(name = "EXAM_ARRANGEMENT_F")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean examArrangement;

	@Column(name = "EXAM_REFUSAL_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean examRefusalFlag;

	@Column(name = "EXAM_YEAR")
	private Integer examYear;

	@Column(name = "FOREIGN_LEVY_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean foreignLevyFlag;

	@Column(name = "LANGUAGE_CODE")
	private String languageCode;

	@Column(name = "NR_OF_REPEATS")
	private BigDecimal nrOfRepeats;

	@Column(name = "OVERRIDE_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean overrideFlag;

	@Temporal(TemporalType.DATE)
	@Column(name = "REGISTRATION_DATE")
	private Date registrationDate;

	@Column(name = "STATUS_CODE")
	private String statusCode;

	@Column(name = "SUPPLEMENTARY_EXAM")
	private Integer supplementaryExam;

	@Column(name = "TUTORIAL_FLAG")
	private String tutorialFlag;

	public ModuleEnrolmentEntity() {
	}

	public ModuleEnrolmentInfo toDto() {
		ModuleEnrolmentInfo info = new ModuleEnrolmentInfo();
		info.setStudyUnitCode(this.studyUnitCode);
		info.setStudentNumber(this.studentNumber);
		info.setAcademicYear(this.academicYear);
		info.setAcademicPeriod(this.academicPeriod);
		info.setSemesterPeriod(this.semesterPeriod);
		info.setSemesterPeriodCode(this.semesterPeriod == 1 ? "S1" : (this.semesterPeriod == 2 ? "S2" : (this.semesterPeriod == 0 ? "Y1" : (this.semesterPeriod == 6 ? "Y2" : null))));
		info.setQualificationCode(this.qualificationCode);
		info.setStatusCode(this.statusCode);
		return info;
	}

	public String getAssignmentsFlag() {
		return assignmentsFlag;
	}

	public void setAssignmentsFlag(String assignmentsFlag) {
		this.assignmentsFlag = assignmentsFlag;
	}

	public Date getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	public String getContactFlag() {
		return contactFlag;
	}

	public void setContactFlag(String contactFlag) {
		this.contactFlag = contactFlag;
	}

	public String getDespatchComment() {
		return despatchComment;
	}

	public void setDespatchComment(String despatchComment) {
		this.despatchComment = despatchComment;
	}

	public String getDiscussionClass() {
		return discussionClass;
	}

	public void setDiscussionClass(String discussionClass) {
		this.discussionClass = discussionClass;
	}

	public Integer getExamAdmissionCode() {
		return examAdmissionCode;
	}

	public void setExamAdmissionCode(Integer examAdmissionCode) {
		this.examAdmissionCode = examAdmissionCode;
	}

	public boolean isExamArrangement() {
		return examArrangement;
	}

	public void setExamArrangement(boolean examArrangement) {
		this.examArrangement = examArrangement;
	}

	public boolean isExamRefusalFlag() {
		return examRefusalFlag;
	}

	public void setExamRefusalFlag(boolean examRefusalFlag) {
		this.examRefusalFlag = examRefusalFlag;
	}

	public Integer getExamYear() {
		return examYear;
	}

	public void setExamYear(Integer examYear) {
		this.examYear = examYear;
	}

	public Integer getAcademicPeriod() {
		return academicPeriod;
	}

	public void setAcademicPeriod(Integer academicPeriod) {
		this.academicPeriod = academicPeriod;
	}

	public Integer getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}

	public boolean isForeignLevyFlag() {
		return foreignLevyFlag;
	}

	public void setForeignLevyFlag(boolean foreignLevyFlag) {
		this.foreignLevyFlag = foreignLevyFlag;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getCourseStudyUnit() {
		return courseStudyUnit;
	}

	public void setCourseStudyUnit(String courseStudyUnit) {
		this.courseStudyUnit = courseStudyUnit;
	}

	public String getMkExamCentreCode() {
		return mkExamCentreCode;
	}

	public void setMkExamCentreCode(String mkExamCentreCode) {
		this.mkExamCentreCode = mkExamCentreCode;
	}

	public Integer getExamPeriod() {
		return examPeriod;
	}

	public void setExamPeriod(Integer examPeriod) {
		this.examPeriod = examPeriod;
	}

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public String getStudyUnitCode() {
		return studyUnitCode;
	}

	public void setStudyUnitCode(String studyUnitCode) {
		this.studyUnitCode = studyUnitCode;
	}

	public String getStudyUnitOption() {
		return studyUnitOption;
	}

	public void setStudyUnitOption(String studyUnitOption) {
		this.studyUnitOption = studyUnitOption;
	}

	public BigDecimal getNrOfRepeats() {
		return nrOfRepeats;
	}

	public void setNrOfRepeats(BigDecimal nrOfRepeats) {
		this.nrOfRepeats = nrOfRepeats;
	}

	public boolean isOverrideFlag() {
		return overrideFlag;
	}

	public void setOverrideFlag(boolean overrideFlag) {
		this.overrideFlag = overrideFlag;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Integer getSemesterPeriod() {
		return semesterPeriod;
	}

	public void setSemesterPeriod(Integer semesterPeriod) {
		this.semesterPeriod = semesterPeriod;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getSupplementaryExam() {
		return supplementaryExam;
	}

	public void setSupplementaryExam(Integer supplementaryExam) {
		this.supplementaryExam = supplementaryExam;
	}

	public String getTutorialFlag() {
		return tutorialFlag;
	}

	public void setTutorialFlag(String tutorialFlag) {
		this.tutorialFlag = tutorialFlag;
	}

	public ModuleEnrolmentEntityId getModuleEnrolmentEntityId() {
		return moduleEnrolmentEntityId;
	}

	public void setModuleEnrolmentEntityId(ModuleEnrolmentEntityId moduleEnrolmentEntityId) {
		this.moduleEnrolmentEntityId = moduleEnrolmentEntityId;
	}
}