package za.ac.unisa.myadmin.student.services.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adrian on 2018-06-26.
 */
public class StudentInfo implements Serializable {

	private static final long serialVersionUID = 7114849708840316512L;
	private Integer studentNumber;

	private String studentName;

	private String surname;

	private String firstNames;

	private Date dateOfBirth;

	private String title;

	private String initials;

	private String emailAddress;

	private String identityNumber;

	private String passportNumber;

	private boolean examFeesDebt;

	private boolean financialBlock;

	private Integer libraryBlackList;

	private Integer disciplinaryIncident;

	public StudentInfo() {
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
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

	public Integer getLibraryBlackList() {
		return libraryBlackList;
	}

	public void setLibraryBlackList(Integer libraryBlackList) {
		this.libraryBlackList = libraryBlackList;
	}

	public Integer getDisciplinaryIncident() {
		return disciplinaryIncident;
	}

	public void setDisciplinaryIncident(Integer disciplinaryIncident) {
		this.disciplinaryIncident = disciplinaryIncident;
	}

	@Override
	public String toString() {
		return "StudentInfo{" +
			"studentNumber='" + studentNumber + '\'' +
			", studentName='" + studentName + '\'' +
			", title='" + title + '\'' +
			", initials='" + initials + '\'' +
			", emailAddress='" + emailAddress + '\'' +
			'}';
	}

}
