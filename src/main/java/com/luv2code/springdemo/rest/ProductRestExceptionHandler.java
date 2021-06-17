package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductRestExceptionHandler {
	//Exception Handler
			@ExceptionHandler
			public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException ex){
				ProductErrorResponse error = new ProductErrorResponse();
				error.setStatus(HttpStatus.NOT_FOUND.value());
				error.setMessage(ex.getMessage());
				error.setTimeStamp(System.currentTimeMillis());
				return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
			}
			
			@ExceptionHandler
			public ResponseEntity<ProductErrorResponse> handleException(Exception ex){
				ProductErrorResponse error = new ProductErrorResponse();
				error.setStatus(HttpStatus.BAD_REQUEST.value());
				error.setMessage("Invalid Product Id");
				error.setTimeStamp(System.currentTimeMillis());
				return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
			}
}
