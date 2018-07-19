package za.ac.unisa.myadmin.exam.services.jpa.models;

import static za.ac.unisa.myadmin.common.dto.DescriptionInfo.LOCALE_AFRIKAANS;
import static za.ac.unisa.myadmin.common.dto.DescriptionInfo.LOCALE_ENGLISH;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import za.ac.unisa.myadmin.common.dto.DescriptionInfo;
import za.ac.unisa.myadmin.exam.services.dto.ExamPeriodInfo;

@Entity
@Table(name = "XAMPRD")
public class ExamPeriodEntity {

	@Id
	@Column(name = "CODE")
	private Integer code;

	@Column(name = "ENG_SHORT_DESCRIPT")
	private String engShortDescription;

	@Column(name = "AFR_SHORT_DESCRIPT")
	private String afrShortDescription;

	@Column(name = "ENG_DESCRIPTION")
	private String engDescription;

	@Column(name = "AFR_DESCRIPTION")
	private String afrDescription;

	public ExamPeriodEntity() {
	}

	public ExamPeriodEntity(ExamPeriodInfo dto) {
		this.fromDto(dto);
	}

	public void fromDto(ExamPeriodInfo dto) {
		this.setCode(dto.getCode());
		if (dto.getDescriptionInfo() != null) {
			dto.getDescriptionInfo().forEach(descriptionInfo -> {
				if(LOCALE_ENGLISH.equals(descriptionInfo.getLocale())){
					this.setEngDescription(descriptionInfo.getDescription());
					this.setEngShortDescription(descriptionInfo.getShortDescription());
				}
				else if(LOCALE_AFRIKAANS.equals(descriptionInfo.getLocale())){
					this.setAfrDescription(descriptionInfo.getDescription());
					this.setAfrShortDescription(descriptionInfo.getShortDescription());
				}
			});
		}
	}

	public ExamPeriodInfo toDto() {
		ExamPeriodInfo info = new ExamPeriodInfo();
		info.setCode(this.getCode());
		info.addDescriptionInfo(new DescriptionInfo(LOCALE_AFRIKAANS, this.getAfrShortDescription(), this.getAfrDescription()));
		info.addDescriptionInfo(new DescriptionInfo(LOCALE_ENGLISH, this.getEngShortDescription(), this.getEngDescription()));
		return info;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getEngShortDescription() {
		return engShortDescription;
	}

	public void setEngShortDescription(String engShortDescription) {
		this.engShortDescription = engShortDescription;
	}

	public String getAfrShortDescription() {
		return afrShortDescription;
	}

	public void setAfrShortDescription(String afrShortDescription) {
		this.afrShortDescription = afrShortDescription;
	}

	public String getEngDescription() {
		return engDescription;
	}

	public void setEngDescription(String engDescription) {
		this.engDescription = engDescription;
	}

	public String getAfrDescription() {
		return afrDescription;
	}

	public void setAfrDescription(String afrDescription) {
		this.afrDescription = afrDescription;
	}

}
