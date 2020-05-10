package com.appsdeveloperblog.app.ws.shared.dto;

import java.util.Date;


import com.appsdeveloperblog.app.ws.io.entity.BookEntity;
import com.appsdeveloperblog.app.ws.io.entity.PublisherEntity;

public class BookPublisherDto {
	
	    private BookDto book;
		private PublisherDto publisher;
	    private Date publishedDate;
	    
		public BookDto getBook() {
			return book;
		}
		public void setBook(BookDto book) {
			this.book = book;
		}
		public PublisherDto getPublisher() {
			return publisher;
		}
		public void setPublisher(PublisherDto publisher) {
			this.publisher = publisher;
		}
		public Date getPublishedDate() {
			return publishedDate;
		}
		public void setPublishedDate(Date publishedDate) {
			this.publishedDate = publishedDate;
		}
	    
	    
	    
		
}
