package za.ac.unisa.myadmin.student.services.jpa.models;

import za.ac.unisa.myadmin.generic.dto.EmailLogInfo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the EMLLOG database table.
 */
@Entity
@Table(name = "EMLLOG")
public class EmailLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmailLogEntityId emailLogEntityId;

	@Column(name = "RECIPIENT", insertable = false, updatable = false)
	private String recipient;

	@Column(name = "EMAIL_ADDRESS", insertable = false, updatable = false)
	private String emailAddress;

	@Column(name = "DATE_SENT", insertable = false, updatable = false)
	private Date dateSent;

	@Column(name = "BODY0")
	private String body;

	@Column(name = "EMAIL_TYPE_GC138")
	private String emailType;

	@Column(name = "FILENAME")
	private String fileName;

	@Column(name = "FROM_ADDRESS")
	private String fromAddress;

	@Column(name = "PROGRAM")
	private String program;

	@Column(name = "RECIP_TYPE_GC137")
	private String recipientType;

	@Column(name = "REQ_PROGRAM")
	private String reqProgram;

	@Column(name = "REQ_SYSTEM_GC285")
	private String reqSystem;

	@Column(name = "SUBJECT")
	private String subject;

	public EmailLogEntity() {
	}

	public EmailLogInfo toDto() {
		EmailLogInfo info = new EmailLogInfo();
		info.setEmailAddress(this.emailAddress);
		info.setRecipient(this.recipient);
		info.setProgram(this.program);
		info.setEmailType(this.emailType);
		info.setReqSystem(this.reqSystem);
		info.setReqProgram(this.reqProgram);
		info.setBody(this.body);
		if(this.dateSent !=null) {
			info.setDateSent(new Date(this.dateSent.getTime()));
		}
		return info;
	}

	public EmailLogEntityId getEmailLogEntityId() {
		return emailLogEntityId;
	}

	public void setEmailLogEntityId(EmailLogEntityId emailLogEntityId) {
		this.emailLogEntityId = emailLogEntityId;
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