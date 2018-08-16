package za.ac.unisa.myadmin.student.services.jpa.models;

import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.services.utilities.YesOrNoBooleanConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the STUACA database table.
 */
@Entity
@Table(name = "STUACA")
public class StudentAcademicRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "MK_QUALIFICATION_C", insertable = false, updatable = false)
	QualificationEntity qualificationEntity;
	@EmbeddedId
	private StudentAcademicRecordEntityId studentAcademicRecordEntityId;
	@Column(name = "MK_STUDENT_NR", insertable = false, updatable = false)
	private Integer studentNumber;
	@Column(name = "MK_QUALIFICATION_C", insertable = false, updatable = false)
	private String qualificationCode;
	@Column(name = "MK_GRADUATION_CERE")
	private Integer graduationCeremony;

	@Column(name = "FK_QUAL_CODE")
	private String fkQualificationCode;

	@Column(name = "FK_SPECIALITY_CODE")
	private String specialityCode;

	@Column(name = "ACTUAL_COMPLETION")
	private Integer actualCompletion;

	@Column(name = "ADMISSION_REQUIREM")
	private String admissionRequirement;

	@Column(name = "AUDIT_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean audit;

	@Column(name = "AVERAGE_PERCENT")
	private Integer averagePercent;

	@Column(name = "CO_REMOVED_CODE")
	private String coRemovedCode;

	@Column(name = "COMMENT1")
	private String comment1;

	@Column(name = "COMMENT2")
	private String comment2;

	@Column(name = "CONTROL_TOTAL1")
	private Integer controlTotal1;

	@Column(name = "CONTROL_TOTAL2")
	private Integer controlTotal2;

	@Column(name = "DISTINCTION_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean distinction;

	@Column(name = "EARLIEST_EXEMPTIO0")
	private Integer earliestExemptio0;

	@Column(name = "EARLIEST_EXEMPTION")
	private Integer earliestExemption;

	@Column(name = "ENDORSEMENT")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean endorsement;

	@Column(name = "FI_VERIFIED_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean fiVerifiedFlag;

	@Temporal(TemporalType.DATE)
	@Column(name = "FIRST_REGISTRATION")
	private Date firstRegistration;

	@Temporal(TemporalType.DATE)
	@Column(name = "GRADUATION_CEREMON")
	private Date graduationCeremonyDate;

	@Column(name = "LANGUAGE_PROF")
	private String languageProf;

	@Column(name = "LAST_ACADEMIC_REGI")
	private Integer lastAcademicRegi;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_EXAM_DATE")
	private Date lastExamDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_REGISTRATION")
	private Date lastRegistrationDate;

	@Column(name = "LAST_USER_CODE")
	private Integer lastUserCode;

	@Column(name = "OTHER_ADMISSION_RE")
	private String otherAdmissionRe;

	@Column(name = "PIPELINE_FROM")
	private Integer pipelineFrom;

	@Column(name = "PRESENT_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean present;

	@Column(name = "PREV_QUAL_REGISTRA")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean prevQualRegistration;

	@Column(name = "REQV_STATUS")
	private Integer reqvStatus;

	@Column(name = "STATUS_CODE")
	private String statusCode;

	@Column(name = "TEMPORARY_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean temporary;

	@Column(name = "TEMPORARY_STATUS_C")
	private String temporaryStatusCode;

	@Column(name = "WEEKS_EXPERIENCE")
	private Integer weeksExperience;

	@Column(name = "YEARS_REGISTERED")
	private Integer yearsRegistered;

	public StudentAcademicRecordEntity() {
	}

	public StudentAcademicQualificationRecordInfo toDto() {
		StudentAcademicQualificationRecordInfo info = new StudentAcademicQualificationRecordInfo();
		info.setStudentNumber(this.studentNumber);
		info.setQualificationCode(this.qualificationCode);
		if (this.qualificationEntity != null) {
			info.setQualShortDescription(this.qualificationEntity.getShortDescription());
			info.setQualDisplayDescription(this.qualificationEntity.getEngDescription());
			info.setQualLongDescription(this.qualificationEntity.getLongEngDescription());
		}
		if (this.firstRegistration != null) {
			info.setFirstRegistrationDate(new Date(this.firstRegistration.getTime()));
		}
		if (this.lastRegistrationDate != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(this.lastRegistrationDate);
			info.setLastRegistrationYear(cal.get(Calendar.YEAR));
		}
		info.setAuditFlag(this.audit);
		info.setStatus(this.statusCode);
		if (this.getGraduationCeremonyDate() != null) {
			info.setGraduationCeremonyDate(new Date(this.getGraduationCeremonyDate().getTime()));
		}
		return info;
	}

	public StudentAcademicRecordEntityId getStudentAcademicRecordEntityId() {
		return studentAcademicRecordEntityId;
	}

	public void setStudentAcademicRecordEntityId(StudentAcademicRecordEntityId studentAcademicRecordEntityId) {
		this.studentAcademicRecordEntityId = studentAcademicRecordEntityId;
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public QualificationEntity getQualificationEntity() {
		return qualificationEntity;
	}

	public void setQualificationEntity(QualificationEntity qualificationEntity) {
		this.qualificationEntity = qualificationEntity;
	}

	public Integer getGraduationCeremony() {
		return graduationCeremony;
	}

	public void setGraduationCeremony(Integer graduationCeremony) {
		this.graduationCeremony = graduationCeremony;
	}

	public String getFkQualificationCode() {
		return fkQualificationCode;
	}

	public void setFkQualificationCode(String fkQualificationCode) {
		this.fkQualificationCode = fkQualificationCode;
	}

	public String getSpecialityCode() {
		return specialityCode;
	}

	public void setSpecialityCode(String specialityCode) {
		this.specialityCode = specialityCode;
	}

	public Integer getActualCompletion() {
		return actualCompletion;
	}

	public void setActualCompletion(Integer actualCompletion) {
		this.actualCompletion = actualCompletion;
	}

	public String getAdmissionRequirement() {
		return admissionRequirement;
	}

	public void setAdmissionRequirement(String admissionRequirement) {
		this.admissionRequirement = admissionRequirement;
	}

	public boolean isAudit() {
		return audit;
	}

	public void setAudit(boolean audit) {
		this.audit = audit;
	}

	public Integer getAveragePercent() {
		return averagePercent;
	}

	public void setAveragePercent(Integer averagePercent) {
		this.averagePercent = averagePercent;
	}

	public String getCoRemovedCode() {
		return coRemovedCode;
	}

	public void setCoRemovedCode(String coRemovedCode) {
		this.coRemovedCode = coRemovedCode;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public String getComment2() {
		return comment2;
	}

	public void setComment2(String comment2) {
		this.comment2 = comment2;
	}

	public Integer getControlTotal1() {
		return controlTotal1;
	}

	public void setControlTotal1(Integer controlTotal1) {
		this.controlTotal1 = controlTotal1;
	}

	public Integer getControlTotal2() {
		return controlTotal2;
	}

	public void setControlTotal2(Integer controlTotal2) {
		this.controlTotal2 = controlTotal2;
	}

	public boolean isDistinction() {
		return distinction;
	}

	public void setDistinction(boolean distinction) {
		this.distinction = distinction;
	}

	public Integer getEarliestExemptio0() {
		return earliestExemptio0;
	}

	public void setEarliestExemptio0(Integer earliestExemptio0) {
		this.earliestExemptio0 = earliestExemptio0;
	}

	public Integer getEarliestExemption() {
		return earliestExemption;
	}

	public void setEarliestExemption(Integer earliestExemption) {
		this.earliestExemption = earliestExemption;
	}

	public boolean isEndorsement() {
		return endorsement;
	}

	public void setEndorsement(boolean endorsement) {
		this.endorsement = endorsement;
	}

	public boolean isFiVerifiedFlag() {
		return fiVerifiedFlag;
	}

	public void setFiVerifiedFlag(boolean fiVerifiedFlag) {
		this.fiVerifiedFlag = fiVerifiedFlag;
	}

	public Date getFirstRegistration() {
		return firstRegistration;
	}

	public void setFirstRegistration(Date firstRegistration) {
		this.firstRegistration = firstRegistration;
	}

	public Date getGraduationCeremonyDate() {
		return graduationCeremonyDate;
	}

	public void setGraduationCeremonyDate(Date graduationCeremonyDate) {
		this.graduationCeremonyDate = graduationCeremonyDate;
	}

	public String getLanguageProf() {
		return languageProf;
	}

	public void setLanguageProf(String languageProf) {
		this.languageProf = languageProf;
	}

	public Integer getLastAcademicRegi() {
		return lastAcademicRegi;
	}

	public void setLastAcademicRegi(Integer lastAcademicRegi) {
		this.lastAcademicRegi = lastAcademicRegi;
	}

	public Date getLastExamDate() {
		return lastExamDate;
	}

	public void setLastExamDate(Date lastExamDate) {
		this.lastExamDate = lastExamDate;
	}

	public Date getLastRegistrationDate() {
		return lastRegistrationDate;
	}

	public void setLastRegistrationDate(Date lastRegistrationDate) {
		this.lastRegistrationDate = lastRegistrationDate;
	}

	public Integer getLastUserCode() {
		return lastUserCode;
	}

	public void setLastUserCode(Integer lastUserCode) {
		this.lastUserCode = lastUserCode;
	}

	public String getOtherAdmissionRe() {
		return otherAdmissionRe;
	}

	public void setOtherAdmissionRe(String otherAdmissionRe) {
		this.otherAdmissionRe = otherAdmissionRe;
	}

	public Integer getPipelineFrom() {
		return pipelineFrom;
	}

	public void setPipelineFrom(Integer pipelineFrom) {
		this.pipelineFrom = pipelineFrom;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public boolean isPrevQualRegistration() {
		return prevQualRegistration;
	}

	public void setPrevQualRegistration(boolean prevQualRegistration) {
		this.prevQualRegistration = prevQualRegistration;
	}

	public Integer getReqvStatus() {
		return reqvStatus;
	}

	public void setReqvStatus(Integer reqvStatus) {
		this.reqvStatus = reqvStatus;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isTemporary() {
		return temporary;
	}

	public void setTemporary(boolean temporary) {
		this.temporary = temporary;
	}

	public String getTemporaryStatusCode() {
		return temporaryStatusCode;
	}

	public void setTemporaryStatusCode(String temporaryStatusCode) {
		this.temporaryStatusCode = temporaryStatusCode;
	}

	public Integer getWeeksExperience() {
		return weeksExperience;
	}

	public void setWeeksExperience(Integer weeksExperience) {
		this.weeksExperience = weeksExperience;
	}

	public Integer getYearsRegistered() {
		return yearsRegistered;
	}

	public void setYearsRegistered(Integer yearsRegistered) {
		this.yearsRegistered = yearsRegistered;
	}
}