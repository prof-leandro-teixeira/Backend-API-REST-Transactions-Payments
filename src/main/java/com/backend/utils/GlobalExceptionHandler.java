package com.backend.utils;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// Manipulador genérico para todas as exceções
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		// Logando o erro
		logger.error("Erro interno: {}", ex.getMessage(), ex);

		// Criando uma resposta de erro personalizada
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Erro interno no servidor", ex.getMessage(), LocalDateTime.now());

		// Retorna uma resposta HTTP com status 500 (INTERNAL_SERVER_ERROR) e o corpo da
		// mensagem
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Manipulador específico para ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		// Logando o erro de recurso não encontrado
		logger.error("Recurso não encontrado: {}", ex.getMessage(), ex);

		// Criando a resposta para o erro
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Recurso não encontrado",
				ex.getMessage(), LocalDateTime.now());

		// Retorna uma resposta HTTP com status 404 (NOT_FOUND) e o corpo da mensagem
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// Classe para encapsular a resposta de erro
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

		// Getters e setters
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
