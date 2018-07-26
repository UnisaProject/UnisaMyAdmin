package za.ac.unisa.myadmin.student.services.jpa.models;

import za.ac.unisa.myadmin.services.utilities.YesOrNoBooleanConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "GENMSG")
public class GenericMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GenericMessageEntityId id;

	@Column(name = "MSG_CODE", insertable = false, updatable = false)
	private String messageCode;

	@Column(name = "PROGRAM", insertable = false, updatable = false)
	private String program;

	@Column(name = "DEV_NOTES")
	private String devNotes;

	@Column(name = "FUNCTION_NR")
	private Integer functionNumber;

	@Column(name = "MESSAGE")
	private String message;

	@Column(name = "MESSAGE_TYPE_GC287")
	private String messageType;

	@Column(name = "RESOLUTION")
	private String resolution;

	@Column(name = "RESOLUTION_USED")
	@Convert(converter = YesOrNoBooleanConverter.class)
	private boolean resolutionUsed;

	@Column(name = "SYSTEM_GC285")
	private String system;

	public GenericMessageEntity() {
	}

	public GenericMessageEntityId getId() {
		return this.id;
	}

	public void setId(GenericMessageEntityId id) {
		this.id = id;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getDevNotes() {
		return this.devNotes;
	}

	public void setDevNotes(String devNotes) {
		this.devNotes = devNotes;
	}

	public Integer getFunctionNumber() {
		return functionNumber;
	}

	public void setFunctionNumber(Integer functionNumber) {
		this.functionNumber = functionNumber;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getResolution() {
		return this.resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public boolean isResolutionUsed() {
		return resolutionUsed;
	}

	public void setResolutionUsed(boolean resolutionUsed) {
		this.resolutionUsed = resolutionUsed;
	}

	public String getSystem() {
		return this.system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

}