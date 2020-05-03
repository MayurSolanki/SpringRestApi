package com.appsdeveloperblog.app.ws.shared.dto;

import java.util.HashSet;
import java.util.Set;


import com.appsdeveloperblog.app.ws.io.entity.BookPublisherEntity;

public class PublisherDto {

	    private int id;
		private String publisherId;
	    private String publisherName;
	    private Set<BookDto> books = new HashSet<BookDto>();
	    private Set<BookPublisherDto> bookPublishers = new HashSet<BookPublisherDto>();

	    

		public String getPublisherId() {
			return publisherId;
		}

		public void setPublisherId(String publisherId) {
			this.publisherId = publisherId;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	   

		public String getPublisherName() {
			return publisherName;
		}

		public void setPublisherName(String publisherName) {
			this.publisherName = publisherName;
		}

		
		
		
		public Set<BookDto> getBooks() {
			return books;
		}

		public void setBooks(Set<BookDto> books) {
			this.books = books;
		}

		public Set<BookPublisherDto> getBookPublishers() {
			return bookPublishers;
		}

		public void setBookPublishers(Set<BookPublisherDto> bookPublishers) {
			this.bookPublishers = bookPublishers;
		}

		
		
	    
	    
	    
}
