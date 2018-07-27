package za.ac.unisa.myadmin.generic.dto;

import java.io.Serializable;

/**
 * Created by Adrian on 2018-07-27.
 */
public class GenericCodeInfo implements Serializable {

	private String code;

	private Integer genericCategoryCode;

	private String englishDescription;

	private String afrikaansDescription;

	private boolean inUse;

	private String info;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getGenericCategoryCode() {
		return genericCategoryCode;
	}

	public void setGenericCategoryCode(Integer genericCategoryCode) {
		this.genericCategoryCode = genericCategoryCode;
	}

	public String getEnglishDescription() {
		return englishDescription;
	}

	public void setEnglishDescription(String englishDescription) {
		this.englishDescription = englishDescription;
	}

	public String getAfrikaansDescription() {
		return afrikaansDescription;
	}

	public void setAfrikaansDescription(String afrikaansDescription) {
		this.afrikaansDescription = afrikaansDescription;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
