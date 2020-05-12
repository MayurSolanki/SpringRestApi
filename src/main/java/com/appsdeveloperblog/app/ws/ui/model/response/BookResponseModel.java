package com.appsdeveloperblog.app.ws.ui.model.response;

import java.util.HashSet;
import java.util.Set;

import com.appsdeveloperblog.app.ws.shared.dto.BookPublisherDto;
import com.appsdeveloperblog.app.ws.shared.dto.PublisherDto;
import com.appsdeveloperblog.app.ws.ui.model.request.PublisherRequestModel;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("BookResponseFilter")
public class BookResponseModel {

	private String bookId;
	private String bookName;

	private Set<PublisherResponseModel> publisher = new HashSet<PublisherResponseModel>();

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Set<PublisherResponseModel> getPublisher() {
		return publisher;
	}

	public void setPublisher(Set<PublisherResponseModel> publisher) {
		this.publisher = publisher;
	}

}
