package com.jsp.pharmacy.exception;

public class StaffInvalidPhoneNumber extends RuntimeException {

	private String message;

	public StaffInvalidPhoneNumber(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
