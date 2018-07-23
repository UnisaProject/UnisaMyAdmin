package za.ac.unisa.myadmin.student.services.jpa.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import za.ac.unisa.myadmin.services.utilities.YesOrNoBooleanConverter;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

/**
 * The persistent class for the STU database table.
 * Created by Adrian on 2018-06-06.
 */
@Entity
@Table(name = "STU")
public class StudentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NR")
	private Integer studentNumber;

	@Column(name = "SPECIAL_CHARACTER")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean specialCharacter;

	@Column(name = "MK_TITLE")
	private String title;

	@Column(name = "MK_CORRESPONDENCE")
	private String correspondence;

	@Column(name = "MK_COUNTRY_CODE")
	private String countryCode;

	@Column(name = "MK_HOME_LANGUAGE")
	private String homeLanguage;

	@Column(name = "MK_LAST_ACADEMIC_Y")
	private Integer lastAcademicYear;

	@Column(name = "MK_LAST_ACADEMIC_P")
	private Integer lastAcademicPeriod;

	@Column(name = "MK_NATIONALITY")
	private String nationality;

	//table ETN FK
	@Column(name = "FK_ETNCODE")
	private String ethnicCode;

	//Table LDD FK
	@Column(name = "FK_LDDCODE")
	private Integer learningDistrictCode; //suburbCode??

	//Table RACE FK
	@Column(name = "FK_RACECODE")
	private Integer raceCode;

	@Column(name = "SURNAME")
	private String surname;

	@Column(name = "FIRST_NAMES")
	private String firstNames;

	@Column(name = "INITIALS")
	private String initials;

	@Column(name = "IDENTITY_NR")
	private String identityNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "ACCESS_RESTRICTED")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean accessRestricted;

	@Column(name = "ADMINISTRATION_ORD")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean administrationOrd;

	@Column(name = "APPLICATION_STATUS")
	private String applicationStatusCode;

	@Column(name = "CAO_ADMISSION_CHEC")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean caoAdmission;

	@Column(name = "CORRESPND_HIST")
	private String correspondenceHistory;

	@Column(name = "DECEASED_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean deceased;

	@Column(name = "DEPTPACK_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean departmentPack;

	@Column(name = "DISCIPLINARY_INCID")
	private Integer disciplinaryIncidentId;

	@Column(name = "EXAM_FEES_DEBT_FLA")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean examFeesDebt;

	@Column(name = "FINANCIAL_BLOCK_FL")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean financialBlock;

	@Column(name = "HANDED_OVER_FLAG")
	private String handedOver;

	@Column(name = "ID_BLOCK")
	private String idBlock;

	@Column(name = "LAST_TEMP_PERIOD")
	private Integer lastTempPeriod;

	@Column(name = "LAST_TEMP_YEAR")
	private Integer lastTempYear;

	@Column(name = "LIBRARY_BLACK_LIST")
	private Integer libraryBlackList;

	//Table FK MST
	@Column(name = "MST_CODE")
	private Integer meritCertificateCode;

	@Column(name = "MST_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean mstFlag;

	@Column(name = "MTR_SCHOOL")
	private Integer matricSchool;

	@Column(name = "NSFAS_CONTRACT_BLOCK")
	private Integer nsfasContractBlock;

	@Column(name = "NUMBER_RESTRICTED")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean numberRestricted;

	@Column(name = "OTHER_DOCTRATE_YEA")
	private Integer otherDoctrateYear;

	@Column(name = "OTHER_MASTERS_YEAR")
	private Integer otherMastersYear;

	@Column(name = "PASSPORT_NO")
	private String passportNumber;

	@Column(name = "PHASED_OUT_STATUS")
	private Integer phasedOutStatus;

	@Column(name = "POST_GRADUATE_STUD")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean postGraduateStud;

	@Column(name = "PREVIOUS_SURNAME")
	private String previousSurname;

	@Column(name = "PREVIOUSLY_POST_GR")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean previouslyPostGrad;

	@Column(name = "PREVIOUSLY_UNISA_P")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean previouslyUnisaPostGrad;

	@Column(name = "PUBLIC_WEB_BLOCK")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean publicWebBlock;

	@Column(name = "PURQ_CONSENT_DATE")
	private Timestamp purqConsentDate;

	@Column(name = "PURQ_CONSENT_FLAG")
	private String purqConsentFlag;

	@Column(name = "RD_CHEQUE_CODE")
	private Integer rdChequeCode;

	@Column(name = "RESULT_BLOCK_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean resultBlock;

	@Column(name = "SFP_FLAG")
	private String sfpFlag;

	@Column(name = "SITS_LOADED_DATE")
	private Date sitsLoadedDate;

	@Column(name = "SITS_STU_NR")
	private Integer sitsStudentNumber;

	@Column(name = "SMART_CARD_ISSUED")
	private String smartCardIssued;

	@Column(name = "SQUASH_CODE")
	private String squashCode;

	@Column(name = "STATUTORY_OUTSTANDING_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean statutoryOutstandingFlag;

	@Column(name = "TITLE_HIST")
	private String titleHistory;

	@Column(name = "TWIN_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean twin;

	@Column(name = "UNISA_DOCTRATE_YEA")
	private Integer unisaDoctrateYear;

	@Column(name = "UNISA_HONOURS_YEAR")
	private Integer unisaHonoursYear;

	@Column(name = "UNISA_MASTERS_YEAR")
	private Integer unisaMastersYear;

	@Column(name = "UNISA_UNDERGRAD_YE")
	private Integer unisaUndergradYear;

	public StudentEntity() {
	}

	public StudentInfo toDto() {
		StudentInfo info = new StudentInfo();
		info.setStudentNumber(this.studentNumber);
		String fullName = (this.surname.isEmpty() ? "" : this.surname) + ", " + (this.firstNames.isEmpty() ? "" : this.firstNames);
		info.setStudentName(fullName);
		info.setSurname(this.surname);
		info.setFirstNames(this.firstNames);
		info.setInitials(this.initials);
		info.setTitle(this.title);
		if (this.birthDate != null) {
			info.setDateOfBirth(new Date(this.birthDate.getTime()));
		}
		info.setIdentityNumber(this.identityNumber);
		info.setPassportNumber(this.passportNumber);
		//No email address in table
		//info.setEmailAddress(this.email);
		return info;
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}

	public boolean isSpecialCharacter() {
		return specialCharacter;
	}

	public void setSpecialCharacter(boolean specialCharacter) {
		this.specialCharacter = specialCharacter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstNames() {
		return firstNames;
	}

	public void setFirstNames(String firstNames) {
		this.firstNames = firstNames;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isAccessRestricted() {
		return accessRestricted;
	}

	public void setAccessRestricted(boolean accessRestricted) {
		this.accessRestricted = accessRestricted;
	}

	public boolean isAdministrationOrd() {
		return administrationOrd;
	}

	public void setAdministrationOrd(boolean administrationOrd) {
		this.administrationOrd = administrationOrd;
	}

	public String getApplicationStatusCode() {
		return applicationStatusCode;
	}

	public void setApplicationStatusCode(String applicationStatusCode) {
		this.applicationStatusCode = applicationStatusCode;
	}

	public boolean isCaoAdmission() {
		return caoAdmission;
	}

	public void setCaoAdmission(boolean caoAdmission) {
		this.caoAdmission = caoAdmission;
	}

	public String getCorrespondenceHistory() {
		return correspondenceHistory;
	}

	public void setCorrespondenceHistory(String correspondenceHistory) {
		this.correspondenceHistory = correspondenceHistory;
	}

	public boolean isDeceased() {
		return deceased;
	}

	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}

	public Integer getDisciplinaryIncidentId() {
		return disciplinaryIncidentId;
	}

	public void setDisciplinaryIncidentId(Integer disciplinaryIncidentId) {
		this.disciplinaryIncidentId = disciplinaryIncidentId;
	}

	public boolean isExamFeesDebt() {
		return examFeesDebt;
	}

	public void setExamFeesDebt(boolean examFeesDebt) {
		this.examFeesDebt = examFeesDebt;
	}

	public boolean isFinancialBlock() {
		return financialBlock;
	}

	public void setFinancialBlock(boolean financialBlock) {
		this.financialBlock = financialBlock;
	}

	public String getEthnicCode() {
		return ethnicCode;
	}

	public void setEthnicCode(String ethnicCode) {
		this.ethnicCode = ethnicCode;
	}

	public Integer getLearningDistrictCode() {
		return learningDistrictCode;
	}

	public void setLearningDistrictCode(Integer learningDistrictCode) {
		this.learningDistrictCode = learningDistrictCode;
	}

	public Integer getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(Integer raceCode) {
		this.raceCode = raceCode;
	}

	public String getHandedOver() {
		return handedOver;
	}

	public void setHandedOver(String handedOver) {
		this.handedOver = handedOver;
	}

	public String getIdBlock() {
		return idBlock;
	}

	public void setIdBlock(String idBlock) {
		this.idBlock = idBlock;
	}

	public Integer getLastTempPeriod() {
		return lastTempPeriod;
	}

	public void setLastTempPeriod(Integer lastTempPeriod) {
		this.lastTempPeriod = lastTempPeriod;
	}

	public Integer getLastTempYear() {
		return lastTempYear;
	}

	public void setLastTempYear(Integer lastTempYear) {
		this.lastTempYear = lastTempYear;
	}

	public Integer getLibraryBlackList() {
		return libraryBlackList;
	}

	public void setLibraryBlackList(Integer libraryBlackList) {
		this.libraryBlackList = libraryBlackList;
	}

	public String getCorrespondence() {
		return correspondence;
	}

	public void setCorrespondence(String correspondence) {
		this.correspondence = correspondence;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getHomeLanguage() {
		return homeLanguage;
	}

	public void setHomeLanguage(String homeLanguage) {
		this.homeLanguage = homeLanguage;
	}

	public Integer getLastAcademicPeriod() {
		return lastAcademicPeriod;
	}

	public void setLastAcademicPeriod(Integer lastAcademicPeriod) {
		this.lastAcademicPeriod = lastAcademicPeriod;
	}

	public Integer getLastAcademicYear() {
		return lastAcademicYear;
	}

	public void setLastAcademicYear(Integer lastAcademicYear) {
		this.lastAcademicYear = lastAcademicYear;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Integer getMeritCertificateCode() {
		return meritCertificateCode;
	}

	public void setMeritCertificateCode(Integer meritCertificateCode) {
		this.meritCertificateCode = meritCertificateCode;
	}

	public boolean isMstFlag() {
		return mstFlag;
	}

	public void setMstFlag(boolean mstFlag) {
		this.mstFlag = mstFlag;
	}

	public Integer getMatricSchool() {
		return matricSchool;
	}

	public void setMatricSchool(Integer matricSchool) {
		this.matricSchool = matricSchool;
	}

	public Integer getNsfasContractBlock() {
		return nsfasContractBlock;
	}

	public void setNsfasContractBlock(Integer nsfasContractBlock) {
		this.nsfasContractBlock = nsfasContractBlock;
	}

	public boolean isNumberRestricted() {
		return numberRestricted;
	}

	public void setNumberRestricted(boolean numberRestricted) {
		this.numberRestricted = numberRestricted;
	}

	public Integer getOtherDoctrateYear() {
		return otherDoctrateYear;
	}

	public void setOtherDoctrateYear(Integer otherDoctrateYear) {
		this.otherDoctrateYear = otherDoctrateYear;
	}

	public Integer getOtherMastersYear() {
		return otherMastersYear;
	}

	public void setOtherMastersYear(Integer otherMastersYear) {
		this.otherMastersYear = otherMastersYear;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Integer getPhasedOutStatus() {
		return phasedOutStatus;
	}

	public void setPhasedOutStatus(Integer phasedOutStatus) {
		this.phasedOutStatus = phasedOutStatus;
	}

	public boolean isPostGraduateStud() {
		return postGraduateStud;
	}

	public void setPostGraduateStud(boolean postGraduateStud) {
		this.postGraduateStud = postGraduateStud;
	}

	public String getPreviousSurname() {
		return previousSurname;
	}

	public void setPreviousSurname(String previousSurname) {
		this.previousSurname = previousSurname;
	}

	public boolean isPreviouslyPostGrad() {
		return previouslyPostGrad;
	}

	public void setPreviouslyPostGrad(boolean previouslyPostGrad) {
		this.previouslyPostGrad = previouslyPostGrad;
	}

	public boolean isDepartmentPack() {
		return departmentPack;
	}

	public void setDepartmentPack(boolean departmentPack) {
		this.departmentPack = departmentPack;
	}

	public boolean isPreviouslyUnisaPostGrad() {
		return previouslyUnisaPostGrad;
	}

	public void setPreviouslyUnisaPostGrad(boolean previouslyUnisaPostGrad) {
		this.previouslyUnisaPostGrad = previouslyUnisaPostGrad;
	}

	public boolean isTwin() {
		return twin;
	}

	public void setTwin(boolean twin) {
		this.twin = twin;
	}

	public boolean isPublicWebBlock() {
		return publicWebBlock;
	}

	public void setPublicWebBlock(boolean publicWebBlock) {
		this.publicWebBlock = publicWebBlock;
	}

	public Timestamp getPurqConsentDate() {
		return purqConsentDate;
	}

	public void setPurqConsentDate(Timestamp purqConsentDate) {
		this.purqConsentDate = purqConsentDate;
	}

	public String getPurqConsentFlag() {
		return purqConsentFlag;
	}

	public void setPurqConsentFlag(String purqConsentFlag) {
		this.purqConsentFlag = purqConsentFlag;
	}

	public Integer getRdChequeCode() {
		return rdChequeCode;
	}

	public void setRdChequeCode(Integer rdChequeCode) {
		this.rdChequeCode = rdChequeCode;
	}

	public boolean isResultBlock() {
		return resultBlock;
	}

	public void setResultBlock(boolean resultBlock) {
		this.resultBlock = resultBlock;
	}

	public String getSfpFlag() {
		return sfpFlag;
	}

	public void setSfpFlag(String sfpFlag) {
		this.sfpFlag = sfpFlag;
	}

	public Date getSitsLoadedDate() {
		return sitsLoadedDate;
	}

	public void setSitsLoadedDate(Date sitsLoadedDate) {
		this.sitsLoadedDate = sitsLoadedDate;
	}

	public Integer getSitsStudentNumber() {
		return sitsStudentNumber;
	}

	public void setSitsStudentNumber(Integer sitsStudentNumber) {
		this.sitsStudentNumber = sitsStudentNumber;
	}

	public String getSmartCardIssued() {
		return smartCardIssued;
	}

	public void setSmartCardIssued(String smartCardIssued) {
		this.smartCardIssued = smartCardIssued;
	}

	public String getSquashCode() {
		return squashCode;
	}

	public void setSquashCode(String squashCode) {
		this.squashCode = squashCode;
	}

	public boolean isStatutoryOutstandingFlag() {
		return statutoryOutstandingFlag;
	}

	public void setStatutoryOutstandingFlag(boolean statutoryOutstandingFlag) {
		this.statutoryOutstandingFlag = statutoryOutstandingFlag;
	}

	public String getTitleHistory() {
		return titleHistory;
	}

	public void setTitleHistory(String titleHistory) {
		this.titleHistory = titleHistory;
	}


	public Integer getUnisaDoctrateYear() {
		return unisaDoctrateYear;
	}

	public void setUnisaDoctrateYear(Integer unisaDoctrateYear) {
		this.unisaDoctrateYear = unisaDoctrateYear;
	}

	public Integer getUnisaHonoursYear() {
		return unisaHonoursYear;
	}

	public void setUnisaHonoursYear(Integer unisaHonoursYear) {
		this.unisaHonoursYear = unisaHonoursYear;
	}

	public Integer getUnisaMastersYear() {
		return unisaMastersYear;
	}

	public void setUnisaMastersYear(Integer unisaMastersYear) {
		this.unisaMastersYear = unisaMastersYear;
	}

	public Integer getUnisaUndergradYear() {
		return unisaUndergradYear;
	}

	public void setUnisaUndergradYear(Integer unisaUndergradYear) {
		this.unisaUndergradYear = unisaUndergradYear;
	}
}