package com.jsp.pharmacy.util;

public class ResponseStructure<T> {

	
	private String message;
	private int httpstatus;
	private Object data;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public int getHttpstatus() {
		return httpstatus;
	}
	public void setHttpstatus(int httpstatus) {
		this.httpstatus = httpstatus;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
