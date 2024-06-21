package com.jsp.pharmacy.exception;

public class AdminInvalidtPasswordException extends RuntimeException {

	private String message;

	public AdminInvalidtPasswordException(String message) {
		super();
		this.message = message;
	}
	
	
}
