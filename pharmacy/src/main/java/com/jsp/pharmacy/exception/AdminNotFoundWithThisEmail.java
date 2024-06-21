package com.jsp.pharmacy.exception;

public class AdminNotFoundWithThisEmail extends RuntimeException {

	private String message;

	public AdminNotFoundWithThisEmail(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
