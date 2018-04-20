package za.ac.unisa.myadmin.common.dto;

import java.io.Serializable;

public class DescriptionInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String english;
	private String afrikaans;

	public DescriptionInfo() {
	}

	public DescriptionInfo(String english, String afrikaans) {
		this.english = english;
		this.afrikaans = afrikaans;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getAfrikaans() {
		return afrikaans;
	}

	public void setAfrikaans(String afrikaans) {
		this.afrikaans = afrikaans;
	}

}
