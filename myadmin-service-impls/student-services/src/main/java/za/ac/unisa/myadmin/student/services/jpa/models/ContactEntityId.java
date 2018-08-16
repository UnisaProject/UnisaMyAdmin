package za.ac.unisa.myadmin.student.services.jpa.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContactEntityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "REFERENCE_NO")
	private Integer referenceNumber;

	@Column(name = "FK_ADRCATCODE")
	private Integer addressCategoryCode;

	public ContactEntityId(Integer referenceNumber, Integer addressTypeCode) {
		this.referenceNumber = referenceNumber;
		this.addressCategoryCode = addressTypeCode;
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
}
