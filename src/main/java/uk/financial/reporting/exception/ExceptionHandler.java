package uk.financial.reporting.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

	@org.springframework.web.bind.annotation.ExceptionHandler
	public final ResponseEntity<ExceptionResponse> handleException(LoginException ex, WebRequest request) {
		LOGGER.error("HATA:", ex);

		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public final ResponseEntity<ExceptionResponse> handleException(SystemException ex, WebRequest request) {
		LOGGER.error("HATA:", ex);

		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public final ResponseEntity<ExceptionResponse> handleException(RemoteSystemException ex, WebRequest request) {
		LOGGER.error("HATA:", ex.getCause());

		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
