package za.ac.unisa.myadmin.studymaterial.services.dto;

import java.io.Serializable;
import java.util.Date;

public class StudyMaterialDetailInfo extends AbstractMaterialDetailInfo implements Serializable {

	private static final long serialVersionUID = -4211012322752781937L;

	private String semester;

	private String description;

	private Date implementationDate;

	private String webId;

	private boolean blockedStatus = false;

	private String shortDescription;

	public StudyMaterialDetailInfo() {
	}


	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getImplementationDate() {
		return implementationDate;
	}

	public void setImplementationDate(Date implementationDate) {
		this.implementationDate = implementationDate;
	}

	public String getWebId() {
		return webId;
	}

	public void setWebId(String webId) {
		this.webId = webId;
	}

	public boolean isBlockedStatus() {
		return blockedStatus;
	}

	public void setBlockedStatus(boolean blockedStatus) {
		this.blockedStatus = blockedStatus;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
}
