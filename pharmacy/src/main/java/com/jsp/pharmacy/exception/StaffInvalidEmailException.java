package com.jsp.pharmacy.exception;

import org.springframework.stereotype.Component;


public class StaffInvalidEmailException extends RuntimeException {

	private String message;

	public StaffInvalidEmailException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	
	
}
