package com.appsdeveloperblog.app.ws.shared.dto;

import java.util.HashSet;
import java.util.Set;


public class BookDto {
	
	    private int id;
		private String bookId;
	    private String bookName;
	    private Set<PublisherDto> publisher = new HashSet<PublisherDto>();
	    private Set<BookPublisherDto> bookPublishers = new HashSet<BookPublisherDto>();


	    
	   
	    
		public String getBookId() {
			return bookId;
		}

		public void setBookId(String bookId) {
			this.bookId = bookId;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getBookName() {
			return bookName;
		}

		public void setBookName(String bookName) {
			this.bookName = bookName;
		}

		

		public Set<PublisherDto> getPublisher() {
			return publisher;
		}

		public void setPublisher(Set<PublisherDto> publisher) {
			this.publisher = publisher;
		}

		public Set<BookPublisherDto> getBookPublishers() {
			return bookPublishers;
		}

		public void setBookPublishers(Set<BookPublisherDto> bookPublishers) {
			this.bookPublishers = bookPublishers;
		}

		
	    
	    
}
