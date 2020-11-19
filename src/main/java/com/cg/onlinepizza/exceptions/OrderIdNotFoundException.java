package com.cg.onlinepizza.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason ="Order does not exist. Please enter valid order details! ")
public class OrderIdNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderIdNotFoundException() {

	}

	public OrderIdNotFoundException(String message) {
		super(message);
	}
}
