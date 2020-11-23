package com.cg.onlinepizza.exceptions;

public class CustomersNotPresentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomersNotPresentException(String string)
	{
		super(string);
	}

}
