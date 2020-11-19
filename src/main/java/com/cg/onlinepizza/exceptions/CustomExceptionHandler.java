package com.cg.onlinepizza.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(OrderIdNotFoundException.class)
	public ResponseEntity<Object> handleOrderIdNotFoundException(OrderIdNotFoundException exception, WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(404);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<Object> (exceptionResponse, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<Object> handleDatabaseException(DatabaseException exception, WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(404);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<Object> (exceptionResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(PizzaIdNotFoundException.class)
	public ResponseEntity<Object> handlePizzaIdNotFoundException(PizzaIdNotFoundException exception, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(404);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<Object> (exceptionResponse, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(PizzaAlreadyExistsException.class)
	public ResponseEntity<Object> handlePizzaAlreadyExistsException(PizzaAlreadyExistsException exception, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(404);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<Object> (exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CouponAlreadyExistsException.class)
	public ResponseEntity<Object> handleCouponAlreadyExistsException(CouponAlreadyExistsException exception, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(404);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<Object> (exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CouponNotFoundException.class)
	public ResponseEntity<Object> handleCouponNotFoundException(CouponNotFoundException exception, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(404);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<Object> (exceptionResponse, HttpStatus.NOT_FOUND);
	}
}
