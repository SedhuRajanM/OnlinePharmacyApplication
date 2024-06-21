package com.jsp.pharmacy.exception;

public class AdminIdNotFoundException extends RuntimeException {

	private String message;
	

	public AdminIdNotFoundException(String message) {
		super();
		this.message = message;
	}


	public String getMessage() {
		return message;
	}
	
}
