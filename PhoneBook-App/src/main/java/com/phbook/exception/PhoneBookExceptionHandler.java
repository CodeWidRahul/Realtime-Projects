package com.phbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PhoneBookExceptionHandler {

	@ExceptionHandler(value = NoDataFoundException.class)
	public ResponseEntity<String> handleNoDataFoundException(NoDataFoundException ndfe) {
		String errMsg = ndfe.getMessage();
		return new ResponseEntity<>(errMsg, HttpStatus.BAD_REQUEST);
	}
}
