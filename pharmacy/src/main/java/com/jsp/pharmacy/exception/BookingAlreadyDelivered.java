package com.jsp.pharmacy.exception;

public class BookingAlreadyDelivered extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public BookingAlreadyDelivered(String message) {
		super();
		this.message = message;
	}
	
	
}
