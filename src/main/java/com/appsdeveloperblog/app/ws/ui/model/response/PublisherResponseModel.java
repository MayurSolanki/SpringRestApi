package com.appsdeveloperblog.app.ws.ui.model.response;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("PublisherResponseFilter")
public class PublisherResponseModel {
	
	 String publisherId;
	 String publisherName;
	 
	public String getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	 
	 

}
