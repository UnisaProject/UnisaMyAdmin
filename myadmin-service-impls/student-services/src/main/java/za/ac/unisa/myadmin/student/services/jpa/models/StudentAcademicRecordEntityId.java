package za.ac.unisa.myadmin.student.services.jpa.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class StudentAcademicRecordEntityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "MK_STUDENT_NR")
	private Integer studentNr;

	@Column(name = "MK_QUALIFICATION_C")
	private String qualificationCode;

	public StudentAcademicRecordEntityId(Integer studentNr, String qualificationCode) {
		this.studentNr = studentNr;
		this.qualificationCode = qualificationCode;
	}

	public Integer getStudentNr() {
		return studentNr;
	}

	public void setStudentNr(Integer studentNr) {
		this.studentNr = studentNr;
	}

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}
}