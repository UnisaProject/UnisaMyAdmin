package za.ac.unisa.myadmin.exam.services.dto;

import za.ac.unisa.myadmin.studymaterial.services.dto.AbstractMaterialDetailInfo;

import java.io.Serializable;

/**
 * An object that represents the material of a previously presented exam paper
 */
public class ExamPaperMaterialInfo extends AbstractMaterialDetailInfo implements Serializable {

	private String periodDesc;



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

}
