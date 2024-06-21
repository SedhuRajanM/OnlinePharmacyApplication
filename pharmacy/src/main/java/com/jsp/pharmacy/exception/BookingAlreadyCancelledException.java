package com.jsp.pharmacy.exception;

public class BookingAlreadyCancelledException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public BookingAlreadyCancelledException(String message) {
		super();
		this.message = message;
	}
	
	
}
