package com.cg.onlinepizza.exceptions;

public class CustomerAlreadyExistsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerAlreadyExistsException(String string)
	{
		super(string);
	}
}
