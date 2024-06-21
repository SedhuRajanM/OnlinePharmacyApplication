package com.jsp.pharmacy.exception;

public class BookingsIdNotFoundException extends  RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public BookingsIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
}
