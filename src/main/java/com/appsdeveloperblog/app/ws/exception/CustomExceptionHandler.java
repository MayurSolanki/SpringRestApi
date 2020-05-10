package com.appsdeveloperblog.app.ws.exception;

public class CustomExceptionHandler extends RuntimeException{
	// set specific error message while exception
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3893671374415951164L;

	public CustomExceptionHandler(String message) {
		super(message);
	}

}
