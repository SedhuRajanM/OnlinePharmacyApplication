package com.jsp.pharmacy.exception;

public class CustomerEmailNotFoundException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public CustomerEmailNotFoundException(String message) {
		super();
		this.message = message;
	}
	
}
