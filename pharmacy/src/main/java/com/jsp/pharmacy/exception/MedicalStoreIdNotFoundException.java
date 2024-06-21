package com.jsp.pharmacy.exception;

public class MedicalStoreIdNotFoundException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public MedicalStoreIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
}
