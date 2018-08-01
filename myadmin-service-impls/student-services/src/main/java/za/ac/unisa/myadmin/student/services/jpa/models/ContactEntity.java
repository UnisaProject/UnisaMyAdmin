package za.ac.unisa.myadmin.student.services.jpa.models;

import za.ac.unisa.myadmin.services.utilities.YesOrNoBooleanConverter;

import za.ac.unisa.myadmin.contact.services.dto.ContactInfo;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The persistent class for the ADRPH database table.
 * Which is just phone and email.
 */
@Entity
@Table(name = "ADRPH")
public class ContactEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ContactEntityId contactEntityId;

	@Column(name = "REFERENCE_NO", insertable = false, updatable = false)
	private Integer referenceNumber;

	@Column(name = "FK_ADRCATCODE", insertable = false, updatable = false)
	private Integer addressCategoryCode;

	@Column(name = "CELL_NUMBER")
	private String cellNumber;

	@Column(name = "CELL_PHONE_VERIFIE")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean cellPhoneVerified;

	@Column(name = "COURIER_CONTACT_NO")
	private String courierContactNo;

	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name = "EMAIL_VERIFIED")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean emailVerified;

	@Column(name = "FAX_NUMBER")
	private String faxNumber;

	@Column(name = "HOME_NUMBER")
	private String homeNumber;

	@Column(name = "WORK_NUMBER")
	private String workNumber;

	public ContactEntity() {
	}

	public ContactInfo toDto() {
		ContactInfo info = new ContactInfo();
		info.setReferenceNumber(this.referenceNumber);
		info.setAddressCategoryCode(this.addressCategoryCode);
		info.setCellNumber(this.cellNumber);
		info.setCellPhoneVerified(this.cellPhoneVerified);
		info.setCourierContactNo(this.courierContactNo);
		info.setEmailAddress(this.emailAddress);
		info.setEmailVerified(this.emailVerified);
		info.setFaxNumber(this.faxNumber);
		info.setWorkNumber(this.workNumber);
		return info;
	}

	public ContactEntityId getContactEntityId() {
		return contactEntityId;
	}

	public void setContactEntityId(ContactEntityId contactEntityId) {
		this.contactEntityId = contactEntityId;
	}

	public Integer getAddressCategoryCode() {
		return addressCategoryCode;
	}

	public void setAddressCategoryCode(Integer addressCategoryCode) {
		this.addressCategoryCode = addressCategoryCode;
	}

	public Integer getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(Integer referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	public boolean isCellPhoneVerified() {
		return cellPhoneVerified;
	}

	public void setCellPhoneVerified(boolean cellPhoneVerified) {
		this.cellPhoneVerified = cellPhoneVerified;
	}

	public String getCourierContactNo() {
		return courierContactNo;
	}

	public void setCourierContactNo(String courierContactNo) {
		this.courierContactNo = courierContactNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getWorkNumber() {
		return workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}
}