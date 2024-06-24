package com.jsp.pharmacy.exception;

public class CustomerInvalidPhoneNumber extends RuntimeException {

	private String message;

	public CustomerInvalidPhoneNumber(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
