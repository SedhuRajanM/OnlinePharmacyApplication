package com.jsp.pharmacy.exception;

public class BookingCantCancel extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public BookingCantCancel(String message) {
		super();
		this.message = message;
	}
	
}
