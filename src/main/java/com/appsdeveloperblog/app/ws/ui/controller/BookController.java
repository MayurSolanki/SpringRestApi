package com.appsdeveloperblog.app.ws.ui.controller;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	
	@Autowired
	BookService bookService;

	
	@Autowired
	PublisherService publisherService;
	

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public BookResponseModel addBooks(@RequestBody BookRequestModel bookRequestModel) {
		
	        ModelMapper modelMapper = new ModelMapper();

	     	BookDto  bookDto = new BookDto();
	 	    bookDto.setBookName(bookRequestModel.getBookName());

		   for (PublisherRequestModel publisherRequestModel : bookRequestModel.getPublisher()) {
			     PublisherDto publisherDto =  modelMapper.map(publisherRequestModel, PublisherDto.class);
				 bookDto.getPublisher().add(publisherDto);

		   }
		
		
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

}
