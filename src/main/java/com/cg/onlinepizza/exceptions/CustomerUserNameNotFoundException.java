package com.cg.onlinepizza.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason ="Customer username or password is incorrect!")
public class CustomerUserNameNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public CustomerUserNameNotFoundException(String string)
	{
		super(string);
	}

}
