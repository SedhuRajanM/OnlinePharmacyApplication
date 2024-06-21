package com.jsp.pharmacy.exception;

import org.springframework.stereotype.Component;


public class StaffIdNotFoundException extends RuntimeException {

	private String message;

	public StaffIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
