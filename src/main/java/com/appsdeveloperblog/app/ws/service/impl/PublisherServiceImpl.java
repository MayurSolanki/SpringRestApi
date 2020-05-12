package com.appsdeveloperblog.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.io.repositories.PublisherRepository;
import com.appsdeveloperblog.app.ws.service.PublisherService;
import com.appsdeveloperblog.app.ws.shared.dto.PublisherDto;

@Service
public class PublisherServiceImpl implements PublisherService{
	
	@Autowired
	PublisherRepository publisherRepository;

	@Override
	public List<PublisherDto> getAllPublisherOfBook(String bookname) {
		
		List<PublisherDto> outputList = new ArrayList<PublisherDto>();
		
		
	   List<Object[]> publisherList =  	     publisherRepository.findAllPublisherOfBook(bookname);
	 
	   
	   for(int i = 0; i < publisherList.size(); i++) {
		   
   		    Object[] object = publisherList.get(i);
   		    
	    	PublisherDto publisherDto = new PublisherDto();
	    	
	        publisherDto.setPublisherId(String.valueOf(object[0]));
	        publisherDto.setPublisherName(String.valueOf(object[1]));
	    	
//	    	publisherDto.setPublisherName(String.valueOf(publisherList.get(i)));

			
	    	outputList.add(publisherDto);
		
	   }
	   
	  
		
		

		return outputList;
	}

}
