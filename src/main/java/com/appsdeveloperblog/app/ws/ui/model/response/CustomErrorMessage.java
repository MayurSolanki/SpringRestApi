package com.appsdeveloperblog.app.ws.ui.model.response;

import java.util.Date;

public class CustomErrorMessage {
	
	private Date timeStamp;
	private String message;
	private int statusCode;
	
	
	public CustomErrorMessage() {
		
	}
	
	
	
	
	
	public CustomErrorMessage(Date timeStamp, String message) {
		this.timeStamp = timeStamp;
		this.message = message;
	}





	public CustomErrorMessage(Date timeStamp, String message,int statusCode) {
		this.timeStamp = timeStamp;
		this.message = message;
		this.statusCode = statusCode;
	}



	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	public int getStatusCode() {
		return statusCode;
	}



	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	
	
	

}
