package com.cg.onlinepizza.exceptions;

public class CustomersNotPresentException extends Exception {
	private static final long serialVersionUID = 1L;

	public CustomersNotPresentException(String string)
	{
		super(string);
	}

}
