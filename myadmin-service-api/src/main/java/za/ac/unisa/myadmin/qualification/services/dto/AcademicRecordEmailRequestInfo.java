package za.ac.unisa.myadmin.qualification.services.dto;

import java.io.Serializable;

/**
 * Created by Adrian on 2018-08-16.
 */
public class AcademicRecordEmailRequestInfo  implements Serializable {

	private Integer studentNumber;
	private String academicQualificationCode;
	private boolean isAttachMarks;

	public AcademicRecordEmailRequestInfo() {
	}

	public Integer getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getAcademicQualificationCode() {
		return academicQualificationCode;
	}

	public void setAcademicQualificationCode(String academicQualificationCode) {
		this.academicQualificationCode = academicQualificationCode;
	}

	public boolean isAttachMarks() {
		return isAttachMarks;
	}

	public void setAttachMarks(boolean attachMarks) {
		isAttachMarks = attachMarks;
	}
}
