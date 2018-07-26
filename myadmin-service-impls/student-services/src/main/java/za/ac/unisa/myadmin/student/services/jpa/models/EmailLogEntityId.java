package za.ac.unisa.myadmin.student.services.jpa.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class EmailLogEntityId implements Serializable {
	private static final long serialVersionUID = 1L;
	//Might be a key
	@Column(name = "RECIPIENT")
	private String recipient;

	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name = "DATE_SENT")
	private Timestamp dateSent;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Timestamp getDateSent() {
		return dateSent;
	}

	public void setDateSent(Timestamp dateSent) {
		this.dateSent = dateSent;
	}
}