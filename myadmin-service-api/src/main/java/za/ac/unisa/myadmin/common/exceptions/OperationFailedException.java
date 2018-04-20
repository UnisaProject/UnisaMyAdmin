package za.ac.unisa.myadmin.common.exceptions;

public class OperationFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	public OperationFailedException() {
	}

	public OperationFailedException(String message) {
		super(message);
	}

	public OperationFailedException(String message, Throwable t) {
		super(message, t);
	}

	public OperationFailedException(Throwable t) {
		super(t);
	}

}
