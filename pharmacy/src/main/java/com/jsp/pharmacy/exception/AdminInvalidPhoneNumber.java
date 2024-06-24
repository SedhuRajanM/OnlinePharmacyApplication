package com.jsp.pharmacy.exception;

public class AdminInvalidPhoneNumber extends RuntimeException {

	private String message;

	public AdminInvalidPhoneNumber(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
