package za.ac.unisa.myadmin.service.creditcard.payment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by dev on 2018-06-06.
 */
@Entity
@Table(name = "STU")
public class StudentEntity {

	@Id
	@Column(name = "NR", insertable = false, updatable = false)
	private Integer studentNumber;

	@Column(name = "SMART_CARD_ISSUED")
	private String smartCardIssued;

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getSmartCardIssued() {
		return smartCardIssued;
	}

	public void setSmartCardIssued(String smartCardIssued) {
		this.smartCardIssued = smartCardIssued;
	}
}
