package za.ac.unisa.myadmin.server.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import za.ac.unisa.myadmin.common.dto.ErrorInfo;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Exception handler that will handle any rest service exceptions and instead return a
 * <code>ErrorInfo</code> object.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private ApplicationContext appContext;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + appContext.getMessage(error, Locale.ENGLISH));
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + appContext.getMessage(error, Locale.ENGLISH));
		}

		ErrorInfo errorInfo =
			new ErrorInfo(ex.getLocalizedMessage(), errors, 1400);
		return handleExceptionInternal(ex, errorInfo, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = {OperationFailedException.class})
	public ResponseEntity<Object> handleOperationFailedException(Exception ex, WebRequest request) {
		ErrorInfo error = new ErrorInfo(ex.getMessage(), 1500);
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(value = {MissingParameterException.class, InvalidParameterException.class})
	public ResponseEntity<Object> handleMissingParameterException(Exception ex, WebRequest request) {
		ErrorInfo error = new ErrorInfo(ex.getMessage(), 1400);
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = {DoesNotExistException.class})
	public ResponseEntity<Object> handleDoesNotExistException(Exception ex, WebRequest request) {
		ErrorInfo error = new ErrorInfo(ex.getMessage(), 1404);
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		ErrorInfo error = new ErrorInfo(ex.getMessage(), 1500);
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}
