package com.appsdeveloperblog.app.ws.service;

import com.appsdeveloperblog.app.ws.shared.dto.BookDto;

import java.util.List;

import com.appsdeveloperblog.app.ws.shared.dto.BookDto;

public interface BookService {

	public BookDto addBookPublishers(BookDto bookDto) ;  // ==>check book existence first,  Adding completely new Book with their publisher,   

	public BookDto addBook(BookDto bookDto);

	public BookDto updateBook(String bookId); // ==> Check publisher and update, Updating book, and publisher against
												// book, like later adding new publisher for same book and changing the
												// name of book

	public void DeleteBook(String bookId); // delete specfic book, only book , not publisher
	
	public List<BookDto> findBooksOfPublisher(String publisherId);  // Find all books of specific publisher

}
