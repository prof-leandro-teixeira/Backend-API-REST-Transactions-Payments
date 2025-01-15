package com.backend.utils;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	//para validação de campos em DTOs
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
	    String errorMessage = ex.getBindingResult()
	                            .getFieldErrors()
	                            .stream()
	                            .map(error -> error.getField() + ": " + error.getDefaultMessage())
	                            .findFirst()
	                            .orElse("Erro de validação");
	                            
	    logger.error("Erro de validação: {}", errorMessage);

	    ErrorResponse errorResponse = new ErrorResponse(
	        HttpStatus.BAD_REQUEST.value(),
	        "Erro de validação",
	        errorMessage,
	        LocalDateTime.now()
	    );

	    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	//HttpMessageNotReadableException (para erros de parsing de JSON).
	
	//ConstraintViolationException (para validações diretas do Bean Validation).
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		logger.error("Erro interno: {}", ex.getMessage(), ex);

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Erro interno no servidor", ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		logger.error("Recurso não encontrado: {}", ex.getMessage(), ex);

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Recurso não encontrado",
				ex.getMessage(), LocalDateTime.now());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	public static class ErrorResponse {
		private int status;
		private String error;
		private String message;
		private LocalDateTime timestamp;

		public ErrorResponse(int status, String error, String message, LocalDateTime timestamp) {
			this.status = status;
			this.error = error;
			this.message = message;
			this.timestamp = timestamp;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
	}
}
