package za.ac.unisa.myadmin.common.dto;

import java.util.List;

/**
 * A class representing an Data Transfer Object to indicate that an error happened
 * on the server. This object should be sent in which ever format the client
 * expects to consume a response - it could be json, xml or html, etc.
 *
 * @author Charl Thiem
 */
public class ErrorInfo {

	/**
	 * A message that can be displayed to the user.
	 */
	private String message;

	/**
	 * A code for this error.
	 * These codes should be fixed values and the consumer of the service
	 * should have these codes known.
	 * <p>
	 * Default http error code can be mapped to the 1000 range.
	 * For example http 404, would result in errorCode 1404.
	 */
	private int errorCode;

	private List<String> errors;

	/**
	 * Instantiates a new application error.
	 */
	public ErrorInfo() {
	}


	/**
	 * Instantiates a new application error.
	 *
	 * @param message   the message
	 * @param errorCode the error code
	 */
	public ErrorInfo(String message, int errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public ErrorInfo(String message, List<String> errors, int errorCode) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.errors = errors;
	}

	/**
	 * Gets message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets message.
	 *
	 * @param message the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets error code.
	 *
	 * @return the error code
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets error code.
	 *
	 * @param errorCode the error code
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
