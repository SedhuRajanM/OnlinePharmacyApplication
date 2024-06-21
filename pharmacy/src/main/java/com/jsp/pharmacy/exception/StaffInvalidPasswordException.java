package com.jsp.pharmacy.exception;

import org.springframework.stereotype.Component;


public class StaffInvalidPasswordException extends RuntimeException {

	private String message;

	public StaffInvalidPasswordException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
