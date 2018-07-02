package za.ac.unisa.myadmin.student.services.models;

import za.ac.unisa.myadmin.service.sql.converter.YesOrNoBooleanConverter;
import za.ac.unisa.myadmin.student.services.dto.StudentAnnualInfo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the STUANN database table.
 * 
 */
@Entity
@Table(name="STUANN")
public class StudentAnnualEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StudentAnnualEntityId studentAnnualEntityId;

	@Column(name="MK_STUDENT_NR", insertable = false, updatable = false)
	private Integer studentNumber;

	@Column(name="MK_ACADEMIC_YEAR", insertable = false, updatable = false)
	private Integer academicYear;

	@Column(name="MK_ACADEMIC_PERIOD", insertable = false, updatable = false)
	private Integer academicPeriod;

	@Column(name="MK_CONTACT_CENTRE")
	private Integer contactCentre;

	@Column(name="MK_DISABILITY_TYPE")
	private Integer disabilityType;

	@Column(name="MK_ECONOMIC_SECTOR")
	private String economicSector;

	@Column(name="MK_HIGHEST_QUALIFI")
	private String highestQualification;

	@Column(name="MK_OCCUPATION_CODE")
	private String occupationCode;

	@Column(name="MK_OTHER_EDUCATION")
	private String otherEducation;

	@Column(name="MK_PREV_EDUCATIONA")
	private String prevEducation;

	@Column(name="MK_REGIONAL_OFFICE")
	private Integer regionalOffice;

	@Column(name="FK_ACCOUNT_TYPE")
	private String accountType;

	@Column(name="FK_BRANCH_CODE")
	private Integer branchCode;

	@Column(name="ACCEPT_XTN_NR")
	private String acceptExtensionNumber;

	@Column(name="ACCEPTANCE_FEE_STATUS")
	private String acceptanceFeeStatusCode;

	@Column(name="ACTIVITY_LAST_YEAR")
	private String activityLastYear;

	@Column(name="AVAILABLE_FOR_SRC")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean availableForSrc;

	@Column(name="BOOKSELLERS_PERMIS")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean booksellersPermission;

	@Column(name="BURSARY_AMOUNT")
	private BigDecimal bursaryAmount;

	@Column(name="BURSARY_TYPE")
	private Integer bursaryType;

	@Column(name="CANCEL_REASON")
	private String cancelReason;

	@Column(name="COMMUNICATION_METH")
	private String communicationMethod;

	@Column(name="CURRENT_STAFF")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean currentStaff;

	@Column(name="DEPENDANT_STAFF")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean dependantStaff;

	@Column(name="DESPATCH_METHOD_CO")
	private String despatchMethodCode;

	@Column(name="EXCEED_MAX_MODULES")
	private String exceedMaxModules;

	@Column(name="EXPERIENTIAL_ASSIS")
	private String experientialAssis;

	@Column(name="FELLOW_STUDENT_FLA")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean fellowStudent;

	@Temporal(TemporalType.DATE)
	@Column(name="FINAL_PAYMENT_DATE")
	private Date finalPaymentDate;

	@Column(name="FIRST_REGISTRATION")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean firstRegistration;

	@Column(name="INTERNET_REGISTRAT")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean internetRegistration;

	@Column(name="JOB_RELATED_TO_STU")
	private String jobRelatedToStudent;

	@Column(name="JOB_TITLE")
	private String jobTitle;

	@Column(name="LATE_REGISTRATION")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean lateRegistration;

	@Column(name="LIBRARY_ACCESS_LEV")
	private Integer libraryAccessLevel;

	@Column(name="MATRICULATION_BOAR")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean matriculationBoard;

	@Column(name="OVERRIDE_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean override;

	@Column(name="PERSONNEL_NR")
	private Integer personnelNr;

	@Column(name="PIPELINE_STUDENT")
	private String pipelineStudent;

	@Column(name="PREV_EDUCATIONAL_I")
	private Integer prevEducationalInstitution;

	@Column(name="PREVIOUS_UNISA_FLA")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean previousUnisa;

	@Column(name="PRISONER")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean prisoner;

	@Column(name="QUAL_COMPLETING")
	private String qualCompleting;

	@Column(name="QUAL_EXPLAIN")
	private String qualExplain;

	@Column(name="REG_DELIVERY_METHO")
	private String regDeliveryMethod;

	@Temporal(TemporalType.DATE)
	@Column(name="REGISTRATION_DATE")
	private Date registrationDate;

	@Column(name="REGISTRATION_METHO")
	private String registrationMethod;

	@Column(name="SCHOOL")
	private String school;

	@Column(name="SEM1_LEVY_PAID")
	private String semester1LevyPaid;

	@Column(name="SEM2_LEVY_PAID")
	private String semester2LevyPaid;

	@Column(name="SEMESTER_TYPE")
	private BigDecimal semesterType;

	@Column(name="SM_DELIVERY_METHOD")
	private String deliveryMethod;

	@Column(name="SM_MESSAGE_ALERT")
	private String messageAlert;

	@Column(name="SMS_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean smsFlag;

	@Column(name="SOL_USER_FLAG")
	private String solUserFlag;

	@Column(name="SPECIAL_LIBRARY_AC")
	private Integer specialLibraryAccess;

	@Column(name="SPECIALITY_CODE")
	private String specialityCode;

	@Column(name="STATUS_CODE")
	private String statusCode;

	@Column(name="TEFSA_APPLICATION")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean tefsaApplication;

	@Column(name="TEFSA_INDICATOR")
	private Integer tefsaIndicator;

	@Column(name="TUTORIAL_METHOD")
	private String tutorialMethod;

	@Column(name="VOTERS_ROLL_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean votersRollFlag;

	public StudentAnnualEntity() {
	}

	public StudentAnnualInfo toDto() {
		StudentAnnualInfo info = new StudentAnnualInfo();
		info.setStudentNumber(this.studentNumber);
		info.setAcademicYear(this.academicYear);
		info.setAcademicPeriod(this.academicPeriod);
		info.setHighestQualification(this.highestQualification);
		return info;
	}

	public String getAcceptExtensionNumber() {
		return acceptExtensionNumber;
	}

	public void setAcceptExtensionNumber(String acceptExtensionNumber) {
		this.acceptExtensionNumber = acceptExtensionNumber;
	}

	public String getAcceptanceFeeStatusCode() {
		return acceptanceFeeStatusCode;
	}

	public void setAcceptanceFeeStatusCode(String acceptanceFeeStatusCode) {
		this.acceptanceFeeStatusCode = acceptanceFeeStatusCode;
	}

	public String getActivityLastYear() {
		return activityLastYear;
	}

	public void setActivityLastYear(String activityLastYear) {
		this.activityLastYear = activityLastYear;
	}

	public boolean isAvailableForSrc() {
		return availableForSrc;
	}

	public void setAvailableForSrc(boolean availableForSrc) {
		this.availableForSrc = availableForSrc;
	}

	public boolean isBooksellersPermission() {
		return booksellersPermission;
	}

	public void setBooksellersPermission(boolean booksellersPermission) {
		this.booksellersPermission = booksellersPermission;
	}

	public BigDecimal getBursaryAmount() {
		return bursaryAmount;
	}

	public void setBursaryAmount(BigDecimal bursaryAmount) {
		this.bursaryAmount = bursaryAmount;
	}

	public Integer getBursaryType() {
		return bursaryType;
	}

	public void setBursaryType(Integer bursaryType) {
		this.bursaryType = bursaryType;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getCommunicationMethod() {
		return communicationMethod;
	}

	public void setCommunicationMethod(String communicationMethod) {
		this.communicationMethod = communicationMethod;
	}

	public boolean isCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(boolean currentStaff) {
		this.currentStaff = currentStaff;
	}

	public boolean isDependantStaff() {
		return dependantStaff;
	}

	public void setDependantStaff(boolean dependantStaff) {
		this.dependantStaff = dependantStaff;
	}

	public String getDespatchMethodCode() {
		return despatchMethodCode;
	}

	public void setDespatchMethodCode(String despatchMethodCode) {
		this.despatchMethodCode = despatchMethodCode;
	}

	public String getExceedMaxModules() {
		return exceedMaxModules;
	}

	public void setExceedMaxModules(String exceedMaxModules) {
		this.exceedMaxModules = exceedMaxModules;
	}

	public String getExperientialAssis() {
		return experientialAssis;
	}

	public void setExperientialAssis(String experientialAssis) {
		this.experientialAssis = experientialAssis;
	}

	public boolean isFellowStudent() {
		return fellowStudent;
	}

	public void setFellowStudent(boolean fellowStudent) {
		this.fellowStudent = fellowStudent;
	}

	public Date getFinalPaymentDate() {
		return finalPaymentDate;
	}

	public void setFinalPaymentDate(Date finalPaymentDate) {
		this.finalPaymentDate = finalPaymentDate;
	}

	public boolean isFirstRegistration() {
		return firstRegistration;
	}

	public void setFirstRegistration(boolean firstRegistration) {
		this.firstRegistration = firstRegistration;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Integer getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(Integer branchCode) {
		this.branchCode = branchCode;
	}

	public boolean isInternetRegistration() {
		return internetRegistration;
	}

	public void setInternetRegistration(boolean internetRegistration) {
		this.internetRegistration = internetRegistration;
	}

	public String getJobRelatedToStudent() {
		return jobRelatedToStudent;
	}

	public void setJobRelatedToStudent(String jobRelatedToStudent) {
		this.jobRelatedToStudent = jobRelatedToStudent;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public boolean isLateRegistration() {
		return lateRegistration;
	}

	public void setLateRegistration(boolean lateRegistration) {
		this.lateRegistration = lateRegistration;
	}

	public Integer getLibraryAccessLevel() {
		return libraryAccessLevel;
	}

	public void setLibraryAccessLevel(Integer libraryAccessLevel) {
		this.libraryAccessLevel = libraryAccessLevel;
	}

	public boolean isMatriculationBoard() {
		return matriculationBoard;
	}

	public void setMatriculationBoard(boolean matriculationBoard) {
		this.matriculationBoard = matriculationBoard;
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

	public Integer getContactCentre() {
		return contactCentre;
	}

	public void setContactCentre(Integer contactCentre) {
		this.contactCentre = contactCentre;
	}

	public Integer getDisabilityType() {
		return disabilityType;
	}

	public void setDisabilityType(Integer disabilityType) {
		this.disabilityType = disabilityType;
	}

	public String getEconomicSector() {
		return economicSector;
	}

	public void setEconomicSector(String economicSector) {
		this.economicSector = economicSector;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public String getOccupationCode() {
		return occupationCode;
	}

	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

	public String getOtherEducation() {
		return otherEducation;
	}

	public void setOtherEducation(String otherEducation) {
		this.otherEducation = otherEducation;
	}

	public String getPrevEducation() {
		return prevEducation;
	}

	public void setPrevEducation(String prevEducation) {
		this.prevEducation = prevEducation;
	}

	public Integer getRegionalOffice() {
		return regionalOffice;
	}

	public void setRegionalOffice(Integer regionalOffice) {
		this.regionalOffice = regionalOffice;
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}

	public boolean isOverride() {
		return override;
	}

	public void setOverride(boolean override) {
		this.override = override;
	}

	public Integer getPersonnelNr() {
		return personnelNr;
	}

	public void setPersonnelNr(Integer personnelNr) {
		this.personnelNr = personnelNr;
	}

	public String getPipelineStudent() {
		return pipelineStudent;
	}

	public void setPipelineStudent(String pipelineStudent) {
		this.pipelineStudent = pipelineStudent;
	}

	public Integer getPrevEducationalInstitution() {
		return prevEducationalInstitution;
	}

	public void setPrevEducationalInstitution(Integer prevEducationalInstitution) {
		this.prevEducationalInstitution = prevEducationalInstitution;
	}

	public boolean isPreviousUnisa() {
		return previousUnisa;
	}

	public void setPreviousUnisa(boolean previousUnisa) {
		this.previousUnisa = previousUnisa;
	}

	public boolean isPrisoner() {
		return prisoner;
	}

	public void setPrisoner(boolean prisoner) {
		this.prisoner = prisoner;
	}

	public String getQualCompleting() {
		return qualCompleting;
	}

	public void setQualCompleting(String qualCompleting) {
		this.qualCompleting = qualCompleting;
	}

	public String getQualExplain() {
		return qualExplain;
	}

	public void setQualExplain(String qualExplain) {
		this.qualExplain = qualExplain;
	}

	public String getRegDeliveryMethod() {
		return regDeliveryMethod;
	}

	public void setRegDeliveryMethod(String regDeliveryMethod) {
		this.regDeliveryMethod = regDeliveryMethod;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrationMethod() {
		return registrationMethod;
	}

	public void setRegistrationMethod(String registrationMethod) {
		this.registrationMethod = registrationMethod;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSemester1LevyPaid() {
		return semester1LevyPaid;
	}

	public void setSemester1LevyPaid(String semester1LevyPaid) {
		this.semester1LevyPaid = semester1LevyPaid;
	}

	public String getSemester2LevyPaid() {
		return semester2LevyPaid;
	}

	public void setSemester2LevyPaid(String semester2LevyPaid) {
		this.semester2LevyPaid = semester2LevyPaid;
	}

	public BigDecimal getSemesterType() {
		return semesterType;
	}

	public void setSemesterType(BigDecimal semesterType) {
		this.semesterType = semesterType;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getMessageAlert() {
		return messageAlert;
	}

	public void setMessageAlert(String messageAlert) {
		this.messageAlert = messageAlert;
	}

	public boolean isSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(boolean smsFlag) {
		this.smsFlag = smsFlag;
	}

	public String getSolUserFlag() {
		return solUserFlag;
	}

	public void setSolUserFlag(String solUserFlag) {
		this.solUserFlag = solUserFlag;
	}

	public Integer getSpecialLibraryAccess() {
		return specialLibraryAccess;
	}

	public void setSpecialLibraryAccess(Integer specialLibraryAccess) {
		this.specialLibraryAccess = specialLibraryAccess;
	}

	public String getSpecialityCode() {
		return specialityCode;
	}

	public void setSpecialityCode(String specialityCode) {
		this.specialityCode = specialityCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isTefsaApplication() {
		return tefsaApplication;
	}

	public void setTefsaApplication(boolean tefsaApplication) {
		this.tefsaApplication = tefsaApplication;
	}

	public Integer getTefsaIndicator() {
		return tefsaIndicator;
	}

	public void setTefsaIndicator(Integer tefsaIndicator) {
		this.tefsaIndicator = tefsaIndicator;
	}

	public String getTutorialMethod() {
		return tutorialMethod;
	}

	public void setTutorialMethod(String tutorialMethod) {
		this.tutorialMethod = tutorialMethod;
	}

	public boolean isVotersRollFlag() {
		return votersRollFlag;
	}

	public void setVotersRollFlag(boolean votersRollFlag) {
		this.votersRollFlag = votersRollFlag;
	}

	public StudentAnnualEntityId getStudentAnnualEntityId() {
		return studentAnnualEntityId;
	}

	public void setStudentAnnualEntityId(StudentAnnualEntityId studentAnnualEntityId) {
		this.studentAnnualEntityId = studentAnnualEntityId;
	}
}