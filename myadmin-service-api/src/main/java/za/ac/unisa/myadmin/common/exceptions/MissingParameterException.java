package za.ac.unisa.myadmin.common.exceptions;

public class MissingParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	public MissingParameterException() {
	}

	public MissingParameterException(String message) {
		super(message);
	}

}
