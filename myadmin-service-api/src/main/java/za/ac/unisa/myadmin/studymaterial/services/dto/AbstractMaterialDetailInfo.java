package za.ac.unisa.myadmin.studymaterial.services.dto;

public abstract class AbstractMaterialDetailInfo {

	private String courseCode;

	private String academicYear;

	private String path;

	private String filesize;

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
	 * Getter for property 'academicYear'.
	 *
	 * @return Value for property 'academicYear'.
	 */
	public String getAcademicYear() {
		return academicYear;
	}

	/**
	 * Setter for property 'academicYear'.
	 *
	 * @param academicYear Value to set for property 'academicYear'.
	 */
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	/**
	 * Getter for property 'path'.
	 *
	 * @return Value for property 'path'.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Setter for property 'path'.
	 *
	 * @param path Value to set for property 'path'.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Getter for property 'filesize'.
	 *
	 * @return Value for property 'filesize'.
	 */
	public String getFilesize() {
		return filesize;
	}

	/**
	 * Setter for property 'filesize'.
	 *
	 * @param filesize Value to set for property 'filesize'.
	 */
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
}
