package za.ac.unisa.myadmin.studyquotation;

import java.io.Serializable;

public class StudyUnit implements Serializable {

	private static final long serialVersionUID = 1L;

	private String studyUnitcode;
	private String description;
	private Double fee;

	public String getStudyUnitcode() {
		return studyUnitcode;
	}

	public void setStudyUnitcode(String studyUnitcode) {
		this.studyUnitcode = studyUnitcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}
}
