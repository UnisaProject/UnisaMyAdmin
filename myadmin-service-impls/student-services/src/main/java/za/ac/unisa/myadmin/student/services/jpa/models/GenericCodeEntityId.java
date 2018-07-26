package za.ac.unisa.myadmin.student.services.jpa.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GenericCodeEntityId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "CODE")
	private String code;

	@Column(name = "FK_GENCATCODE")
	private Integer genericCategoryCode;

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
}