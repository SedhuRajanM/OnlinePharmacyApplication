package com.jsp.pharmacy.exception;

public class CustomerInvalidPasswordException extends RuntimeException {

	private String mesaage;

	public String getMesaage() {
		return mesaage;
	}

	public CustomerInvalidPasswordException(String mesaage) {
		super();
		this.mesaage = mesaage;
	}
	
	
}
