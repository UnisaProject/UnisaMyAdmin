package za.ac.unisa.myadmin.student.services.dto;

/**
 * The type Study unit.
 */
public class StudyUnitInfo {

	private String code;
	private String description;
	private double fee;

	public StudyUnitInfo() {

	}

	public StudyUnitInfo(StudyUnitInfo info) {
		this.code = info.getCode();
		this.description = info.getDescription();
		this.fee = info.getFee();
	}

	/**
	 * Gets description.
	 *
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets description.
	 *
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets fee.
	 *
	 * @return Returns the fee.
	 */
	public double getFee() {
		return fee;
	}

	/**
	 * Sets fee.
	 *
	 * @param d The fee to set.
	 */
	public void setFee(double d) {
		this.fee = d;
	}

	/**
	 * Gets study unitcode.
	 *
	 * @return Returns the studyUnitcode.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets study unitcode.
	 *
	 * @param studyUnitcode The studyUnitcode to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}



}
