package za.ac.unisa.myadmin.exam.integration.services.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exampaper")
public class ExamPaperDto {

	@XmlElement(name = "coursecode")
	private String courseCode;

	@XmlElement(name = "examyear")
	private String examYear;

	@XmlElement(name = "period")
	private int period;

	@XmlElement(name = "language")
	private String language;

	@XmlElement(name = "papernumber")
	private int paperNumber;

	@XmlElement(name = "filesize")
	private long fileSize;

	@XmlElement(name = "link")
	private String link;

	/**
	 * Getter for property 'courseCode'.
	 *
	 * @return Value for property 'courseCode'.
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Setter for property 'courseCode'.
	 *
	 * @param courseCode Value to set for property 'courseCode'.
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Getter for property 'examYear'.
	 *
	 * @return Value for property 'examYear'.
	 */
	public String getExamYear() {
		return examYear;
	}

	/**
	 * Setter for property 'examYear'.
	 *
	 * @param examYear Value to set for property 'examYear'.
	 */
	public void setExamYear(String examYear) {
		this.examYear = examYear;
	}

	/**
	 * Getter for property 'period'.
	 *
	 * @return Value for property 'period'.
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * Setter for property 'period'.
	 *
	 * @param period Value to set for property 'period'.
	 */
	public void setPeriod(int period) {
		this.period = period;
	}

	/**
	 * Getter for property 'language'.
	 *
	 * @return Value for property 'language'.
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Setter for property 'language'.
	 *
	 * @param language Value to set for property 'language'.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Getter for property 'paperNumber'.
	 *
	 * @return Value for property 'paperNumber'.
	 */
	public int getPaperNumber() {
		return paperNumber;
	}

	/**
	 * Setter for property 'paperNumber'.
	 *
	 * @param paperNumber Value to set for property 'paperNumber'.
	 */
	public void setPaperNumber(int paperNumber) {
		this.paperNumber = paperNumber;
	}

	/**
	 * Getter for property 'fileSize'.
	 *
	 * @return Value for property 'fileSize'.
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * Setter for property 'fileSize'.
	 *
	 * @param fileSize Value to set for property 'fileSize'.
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Getter for property 'link'.
	 *
	 * @return Value for property 'link'.
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Setter for property 'link'.
	 *
	 * @param link Value to set for property 'link'.
	 */
	public void setLink(String link) {
		this.link = link;
	}
}
