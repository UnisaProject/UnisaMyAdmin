package za.ac.unisa.myadmin.student.services.dto;

import java.io.Serializable;

/**
 * Created by Adrian on 2018-06-26.
 */
public class StudentInfo implements Serializable {

	private Integer studentNumber;

	private String studentName;

	private String title;

	private String initials;

	private String emailAddress;

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

	@Override
	public String toString() {
		return "CardStudentInfo{" +
			"studentNumber='" + studentNumber + '\'' +
			", studentName='" + studentName + '\'' +
			", title='" + title + '\'' +
			", initials='" + initials + '\'' +
			", emailAddress='" + emailAddress + '\'' +
			'}';
	}

}
