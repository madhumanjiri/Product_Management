package com.jbk.productException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String, Object> handlemethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("Time", new Date());

		/*
		 * ex.getBindingResult().getFieldErrors().forEach(error -> {
		 * map.put(error.getField(), error.getDefaultMessage()); });
		 */

		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return map;

	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productNotFoundException(ProductNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.OK);
		
	}

}
