package za.ac.unisa.myadmin.common.dto;

import java.io.Serializable;

public class DescriptionInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String LOCALE_ENGLISH = "en_ZA";
	public static final String LOCALE_AFRIKAANS = "af_ZA";

	private String locale;
	private String shortDescription;
	private String description;

	public DescriptionInfo() {
	}

	public DescriptionInfo(String locale, String shortDescription, String description) {
		this.locale = locale;
		this.shortDescription = shortDescription;
		this.description = description;
	}

	/**
	 * Getter for property 'locale'.
	 *
	 * @return Value for property 'locale'.
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * Setter for property 'locale'.
	 *
	 * @param locale Value to set for property 'locale'.
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * Getter for property 'shortDescription'.
	 *
	 * @return Value for property 'shortDescription'.
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * Setter for property 'shortDescription'.
	 *
	 * @param shortDescription Value to set for property 'shortDescription'.
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * Getter for property 'description'.
	 *
	 * @return Value for property 'description'.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter for property 'description'.
	 *
	 * @param description Value to set for property 'description'.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
