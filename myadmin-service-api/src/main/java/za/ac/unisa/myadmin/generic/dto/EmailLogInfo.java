package za.ac.unisa.myadmin.generic.dto;

import java.io.Serializable;
import java.util.Date;

public class EmailLogInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String recipient;

	private String emailAddress;

	private Date dateSent;

	private String body;

	private String emailType;

	private String fileName;

	private String fromAddress;

	private String program;

	private String recipientType;

	private String reqProgram;

	private String reqSystem;

	private String subject;

	public EmailLogInfo() {
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getReqProgram() {
		return reqProgram;
	}

	public void setReqProgram(String reqProgram) {
		this.reqProgram = reqProgram;
	}

	public String getReqSystem() {
		return reqSystem;
	}

	public void setReqSystem(String reqSystem) {
		this.reqSystem = reqSystem;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}