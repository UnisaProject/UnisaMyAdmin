package za.ac.unisa.myadmin.payment.services.dto;

import java.io.Serializable;

/**
 * Created by dev on 2018-06-04.
 */
public class QualificationInfo implements Serializable {

	private static final long serialVersionUID = -290275679347221408L;
	private String qualCode;
	private String qualDesc;
	private String qualType;

	private String specialityCode;
	private String specialityDesc;

	public QualificationInfo() {
	}

	public String getQualCode() {
		return qualCode;
	}

	public void setQualCode(String qualCode) {
		this.qualCode = qualCode;
	}

	public String getQualDesc() {
		return qualDesc;
	}

	public void setQualDesc(String qualDesc) {
		this.qualDesc = qualDesc;
	}

	public String getQualType() {
		return qualType;
	}

	public void setQualType(String qualType) {
		this.qualType = qualType;
	}

	public String getSpecialityCode() {
		return specialityCode;
	}

	public void setSpecialityCode(String specialityCode) {
		this.specialityCode = specialityCode;
	}

	public String getSpecialityDesc() {
		return specialityDesc;
	}

	public void setSpecialityDesc(String specialityDesc) {
		this.specialityDesc = specialityDesc;
	}
}
