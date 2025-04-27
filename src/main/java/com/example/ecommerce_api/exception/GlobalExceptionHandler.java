package com.example.ecommerce_api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle error validate
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		Map<String, Object> response = new HashMap<>();
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("errors", errors);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// Handle common exception (ex: RuntimeException)
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>> handleRuntimeExceptions(RuntimeException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("error", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
