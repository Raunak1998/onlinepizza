package com.cg.onlinepizza.exceptions;

public class CouponNotFoundException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public CouponNotFoundException() {
	// TODO Auto-generated constructor stub
	}
	public CouponNotFoundException(String message) {	
		super(message);
	}
	

}
