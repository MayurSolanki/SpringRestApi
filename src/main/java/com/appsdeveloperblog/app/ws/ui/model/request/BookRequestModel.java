package com.appsdeveloperblog.app.ws.ui.model.request;

import java.util.HashSet;
import java.util.Set;

import com.appsdeveloperblog.app.ws.io.entity.BookPublisherEntity;

public class BookRequestModel {

	     private String bookName;
	     
	     private Set<PublisherRequestModel> publisher = new HashSet<PublisherRequestModel>();
	     

		public String getBookName() {
			return bookName;
		}

		public void setBookName(String bookName) {
			this.bookName = bookName;
		}

		public Set<PublisherRequestModel> getPublisher() {
			return publisher;
		}

		public void setPublisher(Set<PublisherRequestModel> publisher) {
			this.publisher = publisher;
		}

		
	    
	    
}
