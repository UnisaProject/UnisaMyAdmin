package za.ac.unisa.myadmin.server.configurations;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import za.ac.unisa.myadmin.common.dto.FrameworkError;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;

/**
 * Exception handler that will handle any rest service exceptions and instead return a
 * <code>FrameworkError</code> object.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { OperationFailedException.class})
	public ResponseEntity<Object> handleOperationFailedException(Exception ex, WebRequest request) {
		FrameworkError error = new FrameworkError(ex.getMessage(), 1500);
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(value = { MissingParameterException.class, InvalidParameterException.class})
	public ResponseEntity<Object> handleMissingParameterException(Exception ex, WebRequest request) {
		FrameworkError error = new FrameworkError(ex.getMessage(), 1400);
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { DoesNotExistException.class})
	public ResponseEntity<Object> handleDoesNotExistException(Exception ex, WebRequest request) {
		FrameworkError error = new FrameworkError(ex.getMessage(), 1404);
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(value = { Exception.class})
	public ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		FrameworkError error = new FrameworkError(ex.getMessage(), 1500);
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}
