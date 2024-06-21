package com.jsp.pharmacy.exception;

public class MedicineNameNotFoundException extends RuntimeException {

	private String message;

	public MedicineNameNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
