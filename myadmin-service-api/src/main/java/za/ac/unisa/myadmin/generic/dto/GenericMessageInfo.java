package za.ac.unisa.myadmin.generic.dto;

import java.io.Serializable;

/**
 * Created by Adrian on 2018-07-27.
 */
public class GenericMessageInfo implements Serializable{

	private String messageCode;

	private String program;

	private String devNotes;

	private Integer functionNumber;

	private String message;

	private String messageType;

	private String resolution;

	private boolean resolutionUsed;

	private String system;

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
		return devNotes;
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
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getResolution() {
		return resolution;
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
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}
}
