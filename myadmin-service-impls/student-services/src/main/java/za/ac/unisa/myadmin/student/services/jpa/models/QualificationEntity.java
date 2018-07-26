package za.ac.unisa.myadmin.student.services.jpa.models;

import za.ac.unisa.myadmin.services.utilities.YesOrNoBooleanConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The persistent class for the GRD database table.
 */
@Entity
@Table(name = "GRD")
public class QualificationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODE", insertable = false, updatable = false)
	private String code;

	@Column(name = "CLASSIFICATION")
	private String classification;

	@Column(name = "MK_DEPARTMENT_CODE", insertable = false, updatable = false)
	private Integer departmentCode;

	@Column(name = "MK_FACULTY_CODE", insertable = false, updatable = false)
	private Integer facultyCode;

	@Column(name = "TYPE", insertable = false, updatable = false)
	private String type;

	@Column(name = "ADMIN_SECTION")
	private String adminSection;

	@Column(name = "ADMISSION_REQUIREM")
	private String admissionRequirement;

	@Column(name = "AFR_DESCRIPTION")
	private String afrDescription;

	@Column(name = "AFR_DIRECTION")
	private String afrDirection;

	@Column(name = "ALLOWED_TEFSA_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean allowedTefsa;

	@Column(name = "APPROVAL_NEEDED")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean approvalNeeded;

	@Column(name = "APPROVED_NAME")
	private String approvedName;

	@Column(name = "APPROVED_QUAL_ID")
	private String approvedQualId;

	@Column(name = "APS_SCORE")
	private Integer apsScore;

	@Column(name = "B_YEARS_FLAG")
	private String bYearsFlag;

	@Column(name = "BBI_CATEGORY_CODE")
	private Integer bbiCategoryCode;

	@Column(name = "BEGIN_YEAR_DESCRIPTION")
	private Integer beginYearDescription;

	@Column(name = "CESM_HANDLING_INDI")
	private String cesmHandlingIndicator;

	@Column(name = "CESM1_CODE")
	private Integer cesmOneCode;

	@Column(name = "CESM2_CODE")
	private Integer cesmTwoCode;

	@Column(name = "CESM3_CODE")
	private Integer cesmThreeCode;

	@Column(name = "COLLEGE_CODE")
	private Integer collegeCode;

	@Column(name = "COMMENT0")
	private String comment0;

	@Column(name = "COMMENTS")
	private String comments;

	@Column(name = "CONCURRENT_REGISTR")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean concurrentRegistration;

	@Column(name = "CONTACT_PHONE_NO_1")
	private String contactPhoneNumber;

	@Column(name = "CONTACT_PHONE_NO_2")
	private String altContactPhoneNumber;

	@Column(name = "COURSE_LEVEL_MAJOR")
	private Integer courseLevelMajor;

	@Column(name = "DELIVERY_MODE")
	private String deliveryMode;

	@Column(name = "DELIVERY_MODE_GC186")
	private String deliveryModeGc186;

	@Column(name = "DESIGNATOR")
	private String designator;

	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name = "ENG_DESCRIPTION")
	private String engDescription;

	@Column(name = "ENG_DIRECTION")
	private String engDirection;

	@Column(name = "EXPERIENTIAL_MONTH")
	private Integer experientialMonth;

	@Column(name = "EXPERT_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean expert;

	@Column(name = "EXTERNAL_CODE")
	private String externalCode;

	@Column(name = "FAX_NUMBER")
	private String faxNumber;

	@Column(name = "FEES_CODE")
	private String feesCode;

	@Column(name = "FK_GRDGRPNAME")
	private String qualificationGroupName;

	@Column(name = "FK_KATCODE")
	private Integer categoryCode;

	@Column(name = "FK_QUALSIGCODE")
	private Integer fkQualsigcode;

	@Column(name = "FOUNDATION_FLAG")
	private String foundationFlag;

	@Column(name = "FROM_YEAR")
	private Integer effectiveYear;

	@Column(name = "TO_YEAR")
	private Integer expirationYear;

	@Column(name = "IGNORE_CREDITS_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean ignoreCredits;

	@Column(name = "IN_USE_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean inUse;

	@Column(name = "INSTITUTION_CODE")
	private String institutionCode;

	@Column(name = "LIBRARY_ACCESS_LEV")
	private Integer libraryAccessLevy;

	@Column(name = "LIBRARY_USE_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean libraryUse;

	@Column(name = "LONG_AFR_DESCRIPTI")
	private String longAfrDescription;

	@Column(name = "LONG_ENG_DESCRIPTI")
	private String longEngDescription;

	@Column(name = "MAJOR_REQUIRED")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean majorRequired;

	@Column(name = "MIN_EXPERIENTIAL_T")
	private BigDecimal minExperientialT;

	@Column(name = "MINIMUM_TIME")
	private BigDecimal minimumTime;

	@Column(name = "MK_ACAD_DEPT_CODE")
	private Integer academicDepartmentCode;

	@Column(name = "MK_ACADEMIC_YEAR")
	private Integer academicYear;

	@Column(name = "MK_CESM_CATEGORY")
	private Integer cesmCategory;

	@Column(name = "MK_CESM_SUB_CATEGO")
	private Integer cesmSubCategory;

	@Column(name = "MOAP_TEST_ACTIVATE")
	private String moapTestActivate;

	@Column(name = "NDP_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean ndpFlag;

	@Column(name = "NO_OF_FINAL_YEAR")
	private Integer noOfFinalYear;

	@Column(name = "NQF_CREDITS")
	private Integer nqfCredits;

	@Column(name = "NQF_EXIT_LEVEL")
	private Integer nqfExitLevel;

	@Column(name = "NR_COURSES_PER_YEA")
	private Integer nrCoursesPerYear;

	@Column(name = "NSFAS_TEACHING")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean nsfasTeaching;

	@Column(name = "NUMBER_CANCELLED")
	private Integer numberCancelled;

	@Column(name = "NUMBER_REGISTERED")
	private Integer numberRegistered;

	@Column(name = "OLD_CODE")
	private String oldCode;

	@Column(name = "PQM_COMPLIANT_FLAG")
	private String pqmCompliantFlag;

	@Column(name = "PQM_NQF_LEVEL")
	private Integer pqmNqfLevel;

	@Column(name = "PURPOSE")
	private String purpose;

	@Column(name = "QUALIFICATION_TYPE")
	private String qualificationType;

	@Column(name = "QUALIFIER")
	private String qualifier;

	@Column(name = "RC_CODE")
	private Integer rcCode;

	@Column(name = "REPEATERS_FROM_YEA")
	private Integer repeatersFromYear;

	@Column(name = "REPEATERS_ONLY")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean repeatersOnly;

	@Column(name = "REPLACED_BY_CODE")
	private String replacedByCode;

	@Column(name = "RESEARCH_DEGREE_FLAG")
	private String researchDegreeFlag;

	@Column(name = "SAQA_ID")
	private String saqaId;

	@Column(name = "SCHOOL_CODE")
	private Integer schoolCode;

	@Column(name = "SECOND_QUALIFIER")
	private String secondQualifier;

	@Column(name = "SERIES_CODE")
	private String seriesCode;

	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;

	@Column(name = "SPECIAL_ADMISSION")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean specialAdmission;

	@Column(name = "SPECIAL_FOREIGN_LE")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean specialForeignLevy;

	@Column(name = "SPECIAL_MASTERS_FL")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean specialMasters;

	@Column(name = "SPECIALISATION_FLA")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean specialisation;

	@Column(name = "STUDY_MATERIAL_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean studyMaterial;

	@Column(name = "SUB_DEPT_CODE")
	private Integer subDeptCode;

	@Column(name = "SUBSIDY_TYPE")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean subsidyType;

	@Column(name = "TEACHING_FOREIGN_F")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean teachingForeign;

	@Column(name = "TEFSA_CO_ALLOWED_F")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean tefsaCoAllowed;

	@Column(name = "TRANSFERS_ONLY_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean transfersOnly;

	@Column(name = "UNDER_POST_CATEGOR")
	private String underPostCategory;

	@Column(name = "WEB_CALENDAR_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean webCalendar;

	@Column(name = "WEB_REGISTRATION_F")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean webRegistration;

	public QualificationEntity() {
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(Integer departmentCode) {
		this.departmentCode = departmentCode;
	}

	public Integer getFacultyCode() {
		return facultyCode;
	}

	public void setFacultyCode(Integer facultyCode) {
		this.facultyCode = facultyCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAdminSection() {
		return adminSection;
	}

	public void setAdminSection(String adminSection) {
		this.adminSection = adminSection;
	}

	public String getAdmissionRequirement() {
		return admissionRequirement;
	}

	public void setAdmissionRequirement(String admissionRequirement) {
		this.admissionRequirement = admissionRequirement;
	}

	public String getAfrDescription() {
		return afrDescription;
	}

	public void setAfrDescription(String afrDescription) {
		this.afrDescription = afrDescription;
	}

	public String getAfrDirection() {
		return afrDirection;
	}

	public void setAfrDirection(String afrDirection) {
		this.afrDirection = afrDirection;
	}

	public boolean isAllowedTefsa() {
		return allowedTefsa;
	}

	public void setAllowedTefsa(boolean allowedTefsa) {
		this.allowedTefsa = allowedTefsa;
	}

	public boolean isApprovalNeeded() {
		return approvalNeeded;
	}

	public void setApprovalNeeded(boolean approvalNeeded) {
		this.approvalNeeded = approvalNeeded;
	}

	public String getApprovedName() {
		return approvedName;
	}

	public void setApprovedName(String approvedName) {
		this.approvedName = approvedName;
	}

	public String getApprovedQualId() {
		return approvedQualId;
	}

	public void setApprovedQualId(String approvedQualId) {
		this.approvedQualId = approvedQualId;
	}

	public Integer getApsScore() {
		return apsScore;
	}

	public void setApsScore(Integer apsScore) {
		this.apsScore = apsScore;
	}

	public String getbYearsFlag() {
		return bYearsFlag;
	}

	public void setbYearsFlag(String bYearsFlag) {
		this.bYearsFlag = bYearsFlag;
	}

	public Integer getBbiCategoryCode() {
		return bbiCategoryCode;
	}

	public void setBbiCategoryCode(Integer bbiCategoryCode) {
		this.bbiCategoryCode = bbiCategoryCode;
	}

	public Integer getBeginYearDescription() {
		return beginYearDescription;
	}

	public void setBeginYearDescription(Integer beginYearDescription) {
		this.beginYearDescription = beginYearDescription;
	}

	public String getCesmHandlingIndicator() {
		return cesmHandlingIndicator;
	}

	public void setCesmHandlingIndicator(String cesmHandlingIndicator) {
		this.cesmHandlingIndicator = cesmHandlingIndicator;
	}

	public Integer getCesmOneCode() {
		return cesmOneCode;
	}

	public void setCesmOneCode(Integer cesmOneCode) {
		this.cesmOneCode = cesmOneCode;
	}

	public Integer getCesmTwoCode() {
		return cesmTwoCode;
	}

	public void setCesmTwoCode(Integer cesmTwoCode) {
		this.cesmTwoCode = cesmTwoCode;
	}

	public Integer getCesmThreeCode() {
		return cesmThreeCode;
	}

	public void setCesmThreeCode(Integer cesmThreeCode) {
		this.cesmThreeCode = cesmThreeCode;
	}

	public Integer getCollegeCode() {
		return collegeCode;
	}

	public void setCollegeCode(Integer collegeCode) {
		this.collegeCode = collegeCode;
	}

	public String getComment0() {
		return comment0;
	}

	public void setComment0(String comment0) {
		this.comment0 = comment0;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isConcurrentRegistration() {
		return concurrentRegistration;
	}

	public void setConcurrentRegistration(boolean concurrentRegistration) {
		this.concurrentRegistration = concurrentRegistration;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public String getAltContactPhoneNumber() {
		return altContactPhoneNumber;
	}

	public void setAltContactPhoneNumber(String altContactPhoneNumber) {
		this.altContactPhoneNumber = altContactPhoneNumber;
	}

	public Integer getCourseLevelMajor() {
		return courseLevelMajor;
	}

	public void setCourseLevelMajor(Integer courseLevelMajor) {
		this.courseLevelMajor = courseLevelMajor;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public String getDeliveryModeGc186() {
		return deliveryModeGc186;
	}

	public void setDeliveryModeGc186(String deliveryModeGc186) {
		this.deliveryModeGc186 = deliveryModeGc186;
	}

	public String getDesignator() {
		return designator;
	}

	public void setDesignator(String designator) {
		this.designator = designator;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEngDescription() {
		return engDescription;
	}

	public void setEngDescription(String engDescription) {
		this.engDescription = engDescription;
	}

	public String getEngDirection() {
		return engDirection;
	}

	public void setEngDirection(String engDirection) {
		this.engDirection = engDirection;
	}

	public Integer getExperientialMonth() {
		return experientialMonth;
	}

	public void setExperientialMonth(Integer experientialMonth) {
		this.experientialMonth = experientialMonth;
	}

	public boolean isExpert() {
		return expert;
	}

	public void setExpert(boolean expert) {
		this.expert = expert;
	}

	public String getExternalCode() {
		return externalCode;
	}

	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getFeesCode() {
		return feesCode;
	}

	public void setFeesCode(String feesCode) {
		this.feesCode = feesCode;
	}

	public String getQualificationGroupName() {
		return qualificationGroupName;
	}

	public void setQualificationGroupName(String qualificationGroupName) {
		this.qualificationGroupName = qualificationGroupName;
	}

	public Integer getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Integer categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Integer getFkQualsigcode() {
		return fkQualsigcode;
	}

	public void setFkQualsigcode(Integer fkQualsigcode) {
		this.fkQualsigcode = fkQualsigcode;
	}

	public String getFoundationFlag() {
		return foundationFlag;
	}

	public void setFoundationFlag(String foundationFlag) {
		this.foundationFlag = foundationFlag;
	}

	public Integer getEffectiveYear() {
		return effectiveYear;
	}

	public void setEffectiveYear(Integer effectiveYear) {
		this.effectiveYear = effectiveYear;
	}

	public Integer getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	public boolean isIgnoreCredits() {
		return ignoreCredits;
	}

	public void setIgnoreCredits(boolean ignoreCredits) {
		this.ignoreCredits = ignoreCredits;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	public Integer getLibraryAccessLevy() {
		return libraryAccessLevy;
	}

	public void setLibraryAccessLevy(Integer libraryAccessLevy) {
		this.libraryAccessLevy = libraryAccessLevy;
	}

	public boolean isLibraryUse() {
		return libraryUse;
	}

	public void setLibraryUse(boolean libraryUse) {
		this.libraryUse = libraryUse;
	}

	public String getLongAfrDescription() {
		return longAfrDescription;
	}

	public void setLongAfrDescription(String longAfrDescription) {
		this.longAfrDescription = longAfrDescription;
	}

	public String getLongEngDescription() {
		return longEngDescription;
	}

	public void setLongEngDescription(String longEngDescription) {
		this.longEngDescription = longEngDescription;
	}

	public boolean isMajorRequired() {
		return majorRequired;
	}

	public void setMajorRequired(boolean majorRequired) {
		this.majorRequired = majorRequired;
	}

	public BigDecimal getMinExperientialT() {
		return minExperientialT;
	}

	public void setMinExperientialT(BigDecimal minExperientialT) {
		this.minExperientialT = minExperientialT;
	}

	public BigDecimal getMinimumTime() {
		return minimumTime;
	}

	public void setMinimumTime(BigDecimal minimumTime) {
		this.minimumTime = minimumTime;
	}

	public Integer getAcademicDepartmentCode() {
		return academicDepartmentCode;
	}

	public void setAcademicDepartmentCode(Integer academicDepartmentCode) {
		this.academicDepartmentCode = academicDepartmentCode;
	}

	public Integer getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}

	public Integer getCesmCategory() {
		return cesmCategory;
	}

	public void setCesmCategory(Integer cesmCategory) {
		this.cesmCategory = cesmCategory;
	}

	public Integer getCesmSubCategory() {
		return cesmSubCategory;
	}

	public void setCesmSubCategory(Integer cesmSubCategory) {
		this.cesmSubCategory = cesmSubCategory;
	}

	public String getMoapTestActivate() {
		return moapTestActivate;
	}

	public void setMoapTestActivate(String moapTestActivate) {
		this.moapTestActivate = moapTestActivate;
	}

	public boolean isNdpFlag() {
		return ndpFlag;
	}

	public void setNdpFlag(boolean ndpFlag) {
		this.ndpFlag = ndpFlag;
	}

	public Integer getNoOfFinalYear() {
		return noOfFinalYear;
	}

	public void setNoOfFinalYear(Integer noOfFinalYear) {
		this.noOfFinalYear = noOfFinalYear;
	}

	public Integer getNqfCredits() {
		return nqfCredits;
	}

	public void setNqfCredits(Integer nqfCredits) {
		this.nqfCredits = nqfCredits;
	}

	public Integer getNqfExitLevel() {
		return nqfExitLevel;
	}

	public void setNqfExitLevel(Integer nqfExitLevel) {
		this.nqfExitLevel = nqfExitLevel;
	}

	public Integer getNrCoursesPerYear() {
		return nrCoursesPerYear;
	}

	public void setNrCoursesPerYear(Integer nrCoursesPerYear) {
		this.nrCoursesPerYear = nrCoursesPerYear;
	}

	public boolean isNsfasTeaching() {
		return nsfasTeaching;
	}

	public void setNsfasTeaching(boolean nsfasTeaching) {
		this.nsfasTeaching = nsfasTeaching;
	}

	public Integer getNumberCancelled() {
		return numberCancelled;
	}

	public void setNumberCancelled(Integer numberCancelled) {
		this.numberCancelled = numberCancelled;
	}

	public Integer getNumberRegistered() {
		return numberRegistered;
	}

	public void setNumberRegistered(Integer numberRegistered) {
		this.numberRegistered = numberRegistered;
	}

	public String getOldCode() {
		return oldCode;
	}

	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}

	public String getPqmCompliantFlag() {
		return pqmCompliantFlag;
	}

	public void setPqmCompliantFlag(String pqmCompliantFlag) {
		this.pqmCompliantFlag = pqmCompliantFlag;
	}

	public Integer getPqmNqfLevel() {
		return pqmNqfLevel;
	}

	public void setPqmNqfLevel(Integer pqmNqfLevel) {
		this.pqmNqfLevel = pqmNqfLevel;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getQualificationType() {
		return qualificationType;
	}

	public void setQualificationType(String qualificationType) {
		this.qualificationType = qualificationType;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public Integer getRcCode() {
		return rcCode;
	}

	public void setRcCode(Integer rcCode) {
		this.rcCode = rcCode;
	}

	public Integer getRepeatersFromYear() {
		return repeatersFromYear;
	}

	public void setRepeatersFromYear(Integer repeatersFromYear) {
		this.repeatersFromYear = repeatersFromYear;
	}

	public boolean isRepeatersOnly() {
		return repeatersOnly;
	}

	public void setRepeatersOnly(boolean repeatersOnly) {
		this.repeatersOnly = repeatersOnly;
	}

	public String getReplacedByCode() {
		return replacedByCode;
	}

	public void setReplacedByCode(String replacedByCode) {
		this.replacedByCode = replacedByCode;
	}

	public String getResearchDegreeFlag() {
		return researchDegreeFlag;
	}

	public void setResearchDegreeFlag(String researchDegreeFlag) {
		this.researchDegreeFlag = researchDegreeFlag;
	}

	public String getSaqaId() {
		return saqaId;
	}

	public void setSaqaId(String saqaId) {
		this.saqaId = saqaId;
	}

	public Integer getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(Integer schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getSecondQualifier() {
		return secondQualifier;
	}

	public void setSecondQualifier(String secondQualifier) {
		this.secondQualifier = secondQualifier;
	}

	public String getSeriesCode() {
		return seriesCode;
	}

	public void setSeriesCode(String seriesCode) {
		this.seriesCode = seriesCode;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public boolean isSpecialAdmission() {
		return specialAdmission;
	}

	public void setSpecialAdmission(boolean specialAdmission) {
		this.specialAdmission = specialAdmission;
	}

	public boolean isSpecialForeignLevy() {
		return specialForeignLevy;
	}

	public void setSpecialForeignLevy(boolean specialForeignLevy) {
		this.specialForeignLevy = specialForeignLevy;
	}

	public boolean isSpecialMasters() {
		return specialMasters;
	}

	public void setSpecialMasters(boolean specialMasters) {
		this.specialMasters = specialMasters;
	}

	public boolean isSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(boolean specialisation) {
		this.specialisation = specialisation;
	}

	public boolean isStudyMaterial() {
		return studyMaterial;
	}

	public void setStudyMaterial(boolean studyMaterial) {
		this.studyMaterial = studyMaterial;
	}

	public Integer getSubDeptCode() {
		return subDeptCode;
	}

	public void setSubDeptCode(Integer subDeptCode) {
		this.subDeptCode = subDeptCode;
	}

	public boolean isSubsidyType() {
		return subsidyType;
	}

	public void setSubsidyType(boolean subsidyType) {
		this.subsidyType = subsidyType;
	}

	public boolean isTeachingForeign() {
		return teachingForeign;
	}

	public void setTeachingForeign(boolean teachingForeign) {
		this.teachingForeign = teachingForeign;
	}

	public boolean isTefsaCoAllowed() {
		return tefsaCoAllowed;
	}

	public void setTefsaCoAllowed(boolean tefsaCoAllowed) {
		this.tefsaCoAllowed = tefsaCoAllowed;
	}

	public boolean isTransfersOnly() {
		return transfersOnly;
	}

	public void setTransfersOnly(boolean transfersOnly) {
		this.transfersOnly = transfersOnly;
	}

	public String getUnderPostCategory() {
		return underPostCategory;
	}

	public void setUnderPostCategory(String underPostCategory) {
		this.underPostCategory = underPostCategory;
	}

	public boolean isWebCalendar() {
		return webCalendar;
	}

	public void setWebCalendar(boolean webCalendar) {
		this.webCalendar = webCalendar;
	}

	public boolean isWebRegistration() {
		return webRegistration;
	}

	public void setWebRegistration(boolean webRegistration) {
		this.webRegistration = webRegistration;
	}
}