package za.ac.unisa.myadmin.student.services.jpa.models;

import za.ac.unisa.myadmin.generic.dto.CodeInfo;
import za.ac.unisa.myadmin.services.utilities.YesOrNoBooleanConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The persistent class for the GENCOD database table.
 */
@Entity
@Table(name = "GENCOD")
public class GenericCodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenericCodeEntityId genericCodeEntityId;

	@Column(name = "CODE", insertable = false, updatable = false)
	private String code;

	@Column(name = "FK_GENCATCODE", insertable = false, updatable = false)
	private Integer genericCategoryCode;

	@Column(name = "ENG_DESCRIPTION")
	private String englishDescription;

	@Column(name = "AFR_DESCRIPTION")
	private String afrikaansDescription;

	@Column(name = "IN_USE_FLAG")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean inUse;

	@Column(name = "INFO")
	private String info;

	public GenericCodeEntity() {
	}

	public CodeInfo toDto() {
		CodeInfo info = new CodeInfo();
		info.setCode(this.code);
		info.setCategoryCode(this.genericCategoryCode);
		info.setEnglishDescription(this.englishDescription);
		info.setAfrikaansDescription(this.afrikaansDescription);
		info.setInUse(this.inUse);
		info.setInfo(this.info);
		return info;
	}

	public GenericCodeEntityId getGenericCodeEntityId() {
		return genericCodeEntityId;
	}

	public void setGenericCodeEntityId(GenericCodeEntityId genericCodeEntityId) {
		this.genericCodeEntityId = genericCodeEntityId;
	}

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