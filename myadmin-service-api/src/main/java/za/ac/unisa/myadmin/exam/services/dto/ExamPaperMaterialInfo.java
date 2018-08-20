package za.ac.unisa.myadmin.exam.services.dto;

import za.ac.unisa.myadmin.studymaterial.services.dto.AbstractMaterialDetailInfo;

import java.io.Serializable;

/**
 * An object that represents the material of a previously presented exam paper
 */
public class ExamPaperMaterialInfo extends AbstractMaterialDetailInfo implements Serializable {

	private String periodDesc;

	/**
	 * The language in which this paper is.
	 */
	private String language;


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
}
