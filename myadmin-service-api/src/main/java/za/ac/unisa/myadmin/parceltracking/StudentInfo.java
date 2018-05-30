package za.ac.unisa.myadmin.parceltracking;

import java.io.Serializable;

public class StudentInfo implements Serializable {

	private static final long serialVersionUID = 9084536063667476815L;

	private String studentNumber;

	private String studentName;

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
