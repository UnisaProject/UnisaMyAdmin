package za.ac.unisa.myadmin.studyquotation.quotation;

/**
 * The type Study unit.
 */
public class StudyUnitInfo {

	private String studyUnitcode;
	private String description;
	private double fee;

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
	public String getStudyUnitcode() {
		return studyUnitcode;
	}

	/**
	 * Sets study unitcode.
	 *
	 * @param studyUnitcode The studyUnitcode to set.
	 */
	public void setStudyUnitcode(String studyUnitcode) {
		this.studyUnitcode = studyUnitcode;
	}



}
