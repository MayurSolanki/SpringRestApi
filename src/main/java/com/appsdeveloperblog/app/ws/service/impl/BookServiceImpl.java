package com.appsdeveloperblog.app.ws.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import com.appsdeveloperblog.app.ws.shared.dto.Utils;
import com.appsdeveloperblog.app.ws.ui.controller.UserController;

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
	public BookDto addBook(BookDto bookDto) {
		
		      // Prepare Book Entity
		 BookEntity bookEntity = new BookEntity();
		 bookEntity.setBookId(utils.generateAddressId(30));
		 bookEntity.setBookName(bookDto.getBookName());

		 
		 for(PublisherDto publisherDto : bookDto.getPublisher()) {
			 
			 
			 PublisherEntity   publisherEntity = new PublisherEntity();
			 publisherEntity.setPublisherId(utils.generateAddressId(30));
			 publisherEntity.setPublisherName(publisherDto.getPublisherName());
	    	
		
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

}
