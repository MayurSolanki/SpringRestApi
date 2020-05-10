package com.appsdeveloperblog.app.ws.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.exception.CustomExceptionHandler;
import com.appsdeveloperblog.app.ws.exception.CustomExceptionStatusCodeHandler;
import com.appsdeveloperblog.app.ws.io.entity.BookEntity;
import com.appsdeveloperblog.app.ws.io.entity.BookPublisherEntity;
import com.appsdeveloperblog.app.ws.io.entity.CoursesEntity;
import com.appsdeveloperblog.app.ws.io.entity.PublisherEntity;
import com.appsdeveloperblog.app.ws.io.repositories.BookRepository;
import com.appsdeveloperblog.app.ws.io.repositories.PublisherRepository;
import com.appsdeveloperblog.app.ws.service.BookService;
import com.appsdeveloperblog.app.ws.shared.dto.BookDto;
import com.appsdeveloperblog.app.ws.shared.dto.BookPublisherDto;
import com.appsdeveloperblog.app.ws.shared.dto.CourseDto;
import com.appsdeveloperblog.app.ws.shared.dto.PublisherDto;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.shared.dto.Utils;
import com.appsdeveloperblog.app.ws.ui.controller.UserController;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;

import javassist.expr.NewArray;

@Service
public class BookServiceImpl implements BookService{
	
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@Autowired
	Utils utils;

	@Override
	public BookDto addBookPublishers(BookDto bookDto)  {
		
		 // add New Book, So check If exist  or not
		
	    BookEntity bookEntityFromDb =	  bookRepository.findByBookName(bookDto.getBookName());
		
		if(bookEntityFromDb != null) throw new CustomExceptionStatusCodeHandler(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage(), HttpStatus.CONFLICT); 

		
		 // Prepare Book Entity
		 BookEntity bookEntity = new BookEntity();
		 bookEntity.setBookId(utils.generateAddressId(30));
		 bookEntity.setBookName(bookDto.getBookName());

		 
		 for(PublisherDto publisherDto : bookDto.getPublisher()) {
			 
	    	
			 
			 PublisherEntity publisherEntityFromDb =  publisherRepository.findByPublisherName(publisherDto.getPublisherName());
			 PublisherEntity   publisherEntity = null;
			 if(publisherEntityFromDb == null) {
				 publisherEntity = new PublisherEntity();
				 publisherEntity.setPublisherId(utils.generateAddressId(30));
				 publisherEntity.setPublisherName(publisherDto.getPublisherName());
		    					 
			 }else {
				 publisherEntity = new PublisherEntity();
				 publisherEntity.setId(publisherEntityFromDb.getId());
				 publisherEntity.setPublisherId(publisherEntityFromDb.getPublisherId());
				 publisherEntity.setPublisherName(publisherEntityFromDb.getPublisherName());
		    	
			  }
			 
			
		
	    	 // Prepare Book-Publisher Entity
		         BookPublisherEntity bookPublisherEntity = new BookPublisherEntity();
			     bookPublisherEntity.setBook(bookEntity);
			     bookPublisherEntity.setPublisher(publisherEntity);
			     bookPublisherEntity.setPublishedDate(new Date());
			    

			     
			// Add Book-Publisher Entity to Book
				bookEntity.getBookPublishers().add(bookPublisherEntity);
				
			    publisherRepository.save(publisherEntity);
		   }
		 	

		    BookEntity bookEntityReturn = bookRepository.save(bookEntity);
		    
		    
		 for (BookPublisherEntity bookPublisherEntity : bookEntityReturn.getBookPublishers()) {
			 
                ModelMapper modelMapper = new ModelMapper();
                BookPublisherDto bookPublisherDto =      modelMapper.map(bookPublisherEntity, BookPublisherDto.class);
                
                logger.debug(bookPublisherDto.toString());         		
                
	      }   
		 


		ModelMapper modelMapper2 = new ModelMapper();
		BookDto bookDto2  =  modelMapper2.map(bookEntityReturn, BookDto.class);
		
		 
		
		return bookDto2;
	}

	@Override
	public BookDto updateBook(String BookId) {
		
		
		
		return null;
	}

	@Override
	public void DeleteBook(String bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BookDto addBook(BookDto bookDto) {
		
		
		BookDto bookDtoToMap = new BookDto();
		bookDtoToMap.setBookId(utils.generateAddressId(30));
		bookDtoToMap.setBookName(bookDto.getBookName());
		
		
		
		
		
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(bookDto,BookEntity.class);
		              
		
		return null;
	}

	@Override
	public List<BookDto> findBooksOfPublisher(String publisherId) {
		
		List<BookDto> bookDtos = new ArrayList<BookDto>();
		List<Object[]>  bookListOfPublisher =    bookRepository.findBooksOfPublisher(publisherId);
		       
				
				
		    	for(int i =0; i < bookListOfPublisher.size() ;i++) {
		    		BookDto bookDto = new  BookDto();
		    		Object[] obj = bookListOfPublisher.get(i);
		    		bookDto.setBookId((String.valueOf(obj[0])));
		    		bookDto.setBookName(String.valueOf(obj[1]));
			   
			    	bookDtos.add(bookDto);	
		    	}
		       
		return bookDtos;
	}



}
