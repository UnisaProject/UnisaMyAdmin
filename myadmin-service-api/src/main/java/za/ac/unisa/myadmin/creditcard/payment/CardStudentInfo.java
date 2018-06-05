package za.ac.unisa.myadmin.creditcard.payment;

import za.ac.unisa.myadmin.parceltracking.StudentInfo;

import java.io.Serializable;

public class CardStudentInfo extends StudentInfo {

	private static final long serialVersionUID = 9156385677947304227L;
	private String title;
	private String initials;
	private String emailAddress;

	public CardStudentInfo() {
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
}
