package za.ac.unisa.myadmin.contact.services.dto;

import java.io.Serializable;

/**
 * Created by Adrian on 2018-08-01.
 */
public class ContactInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer referenceNumber;

	private Integer addressCategoryCode;

	private String cellNumber;

	private boolean cellPhoneVerified;

	private String courierContactNo;

	private String emailAddress;

	private boolean emailVerified;

	private String faxNumber;

	private String homeNumber;

	private String workNumber;

	public ContactInfo() {
	}

	public Integer getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(Integer referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Integer getAddressCategoryCode() {
		return addressCategoryCode;
	}

	public void setAddressCategoryCode(Integer addressCategoryCode) {
		this.addressCategoryCode = addressCategoryCode;
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
