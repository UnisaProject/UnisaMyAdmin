package za.ac.unisa.myadmin.exam.services.dto;

import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;

import java.io.Serializable;

/**
 * An object that represents the material of a previously presented exam paper
 */
public class ExamPaperMaterialInfo extends StudyMaterialDetailInfo implements Serializable {

	private String periodDesc;

	private String fileSizeInBytes;

	private boolean fileExist;


	/**
	 * Getter for property 'periodDesc'.
	 *
	 * @return Value for property 'periodDesc'.
	 */
	public String getPeriodDesc() {
		return periodDesc;
	}

	/**
	 * Setter for property 'periodDesc'.
	 *
	 * @param periodDesc Value to set for property 'periodDesc'.
	 */
	public void setPeriodDesc(String periodDesc) {
		this.periodDesc = periodDesc;
	}

	/**
	 * Getter for property 'fileSizeInBytes'.
	 *
	 * @return Value for property 'fileSizeInBytes'.
	 */
	public String getFileSizeInBytes() {
		return fileSizeInBytes;
	}

	/**
	 * Setter for property 'fileSizeInBytes'.
	 *
	 * @param fileSizeInBytes Value to set for property 'fileSizeInBytes'.
	 */
	public void setFileSizeInBytes(String fileSizeInBytes) {
		this.fileSizeInBytes = fileSizeInBytes;
	}

	/**
	 * Getter for property 'fileExist'.
	 *
	 * @return Value for property 'fileExist'.
	 */
	public boolean isFileExist() {
		return fileExist;
	}

	/**
	 * Setter for property 'fileExist'.
	 *
	 * @param fileExist Value to set for property 'fileExist'.
	 */
	public void setFileExist(boolean fileExist) {
		this.fileExist = fileExist;
	}
}
