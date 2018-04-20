package za.ac.unisa.myadmin.service.exam.paper.details.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import za.ac.unisa.myadmin.exam.paper.details.ExamPaperDetailsInfo;

@Embeddable
public class ExamPaperDetailsIdentity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "EXAM_YEAR")
	private Integer year;

	@Column(name = "MK_STUDY_UNIT_CODE")
	private String courseCode;

	public ExamPaperDetailsIdentity() {

	}

	public ExamPaperDetailsIdentity(ExamPaperDetailsInfo dto) {
		this.year = dto.getYear();
		this.courseCode = dto.getCourseCode();
	}

	public ExamPaperDetailsIdentity(Integer year, String courseCode) {
		this.year = year;
		this.courseCode = courseCode;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

}
