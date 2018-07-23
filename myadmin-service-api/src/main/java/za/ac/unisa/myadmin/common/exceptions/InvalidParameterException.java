package za.ac.unisa.myadmin.common.exceptions;

public class InvalidParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidParameterException() {
		super();
	}

	public InvalidParameterException(String message) {
		super(message);
	}

	public InvalidParameterException(String message, Throwable cause) {
		super(message, cause);

	}
}
