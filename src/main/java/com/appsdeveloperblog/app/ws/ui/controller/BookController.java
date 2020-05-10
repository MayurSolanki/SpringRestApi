package com.appsdeveloperblog.app.ws.ui.controller;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.appsdeveloperblog.app.ws.service.BookService;
import com.appsdeveloperblog.app.ws.service.PublisherService;
import com.appsdeveloperblog.app.ws.shared.dto.BookDto;
import com.appsdeveloperblog.app.ws.shared.dto.BookPublisherDto;
import com.appsdeveloperblog.app.ws.shared.dto.PublisherDto;
import com.appsdeveloperblog.app.ws.ui.model.request.BookRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.request.PublisherRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.BookResponseModel;

@RestController
@RequestMapping("books")

public class BookController {
	//https://hellokoding.com/jpa-many-to-many-extra-columns-relationship-mapping-example-with-spring-boot-hsql/
	//https://hellokoding.com/jpa-many-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql/

	@Autowired
	BookService bookService;

	
	@Autowired
	PublisherService publisherService;
	

	@RequestMapping(value = "/publishers", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public BookResponseModel addBookPublishers(@RequestBody BookRequestModel bookRequestModel) {
		
	        ModelMapper modelMapper = new ModelMapper();

	     	BookDto  bookDto = new BookDto();
	 	    bookDto.setBookName(bookRequestModel.getBookName());

		   for (PublisherRequestModel publisherRequestModel : bookRequestModel.getPublisher()) {
			     PublisherDto publisherDto =  modelMapper.map(publisherRequestModel, PublisherDto.class);
				 bookDto.getPublisher().add(publisherDto);
		   }
		
		
	    BookDto bookDto2  = bookService.addBookPublishers(bookDto);
	    
	    BookDto bookDtoReturn = new BookDto();
	    	
	    for (BookPublisherDto bookPublisherDto : bookDto2.getBookPublishers()) {	    	
	    	bookDtoReturn.setBookId(bookPublisherDto.getBook().getBookId());
	    	bookDtoReturn.setBookName(bookPublisherDto.getBook().getBookName());
	    	bookDtoReturn.getPublisher().add(bookPublisherDto.getPublisher());	    
		}
	 
	    BookResponseModel bookResponseModel =  modelMapper.map(bookDtoReturn, BookResponseModel.class);

	    

		return bookResponseModel;

	}
	
	
	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public BookResponseModel addBook(@RequestBody BookRequestModel bookRequestModel) {
		
	        ModelMapper modelMapper = new ModelMapper();

	     	BookDto  bookDto = new BookDto();
	 	    bookDto.setBookName(bookRequestModel.getBookName());
		
		
	    BookDto bookDto2  = bookService.addBook(bookDto);
	    
	    BookDto bookDtoReturn = new BookDto();
	    	
	    for (BookPublisherDto bookPublisherDto : bookDto2.getBookPublishers()) {	    	
	    	bookDtoReturn.setBookId(bookPublisherDto.getBook().getBookId());
	    	bookDtoReturn.setBookName(bookPublisherDto.getBook().getBookName());
	    	bookDtoReturn.getPublisher().add(bookPublisherDto.getPublisher());	    
		}
	 
	    BookResponseModel bookResponseModel =  modelMapper.map(bookDtoReturn, BookResponseModel.class);

	    

		return bookResponseModel;

	}
	
	// All Books Of Specific Publisher
	
	@RequestMapping(value = "/publisher/{publisherId}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public List<BookResponseModel> GetAllbooksOfPublisher(@PathVariable String publisherId) {
		
	      List<BookResponseModel>  revertResponseList = new ArrayList<BookResponseModel>();
		
	    List<BookDto> bookDtoList  = bookService.findBooksOfPublisher(publisherId);
	    
	    	
	    for (BookDto bookDto : bookDtoList) {	    	
	    	BookResponseModel bookResponseModel = new BookResponseModel();
	    	bookResponseModel.setBookId(bookDto.getBookId()); 
	    	bookResponseModel.setBookName(bookDto.getBookName());
	    	
	    	revertResponseList.add(bookResponseModel);
	    	
		}
	 

	    

		return revertResponseList;

	}
	
	
	// Check Book Exist Or not
	
	
	
	
	// Update Book

}
