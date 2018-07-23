package za.ac.unisa.myadmin.common.exceptions;

public class DoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public DoesNotExistException() {
	}

	public DoesNotExistException(String message) {
		super(message);
	}

	public DoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
