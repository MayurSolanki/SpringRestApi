package com.appsdeveloperblog.app.ws.exception;

import org.springframework.http.HttpStatus;

public class CustomExceptionStatusCodeHandler extends RuntimeException{
	// set specific error message while exception
	
	String message;
	HttpStatus httpStatus;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3893671374415951164L;

	
	
	public CustomExceptionStatusCodeHandler(String message, HttpStatus httpStatus ) {
		this.message = message;
		this.httpStatus = httpStatus;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public HttpStatus getHttpStatus() {
		return httpStatus;
	}



	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}




	
	
}
