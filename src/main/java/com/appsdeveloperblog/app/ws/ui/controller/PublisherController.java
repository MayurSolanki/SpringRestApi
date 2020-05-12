package com.appsdeveloperblog.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.exception.CustomExceptionStatusCodeHandler;
import com.appsdeveloperblog.app.ws.service.PublisherService;
import com.appsdeveloperblog.app.ws.shared.dto.PublisherDto;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;
import com.appsdeveloperblog.app.ws.ui.model.response.PublisherResponseModel;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("publishers")

public class PublisherController {

	@Autowired
	PublisherService publisherService;

	// All Publisher Of Book, List of Publisher Who published Book	

	@RequestMapping(value = "/book/{bookName}", produces = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public MappingJacksonValue getAllPublisherOfBook(@PathVariable("bookName") String bookName) {

		List<PublisherResponseModel> responseList = new ArrayList<PublisherResponseModel>();

		List<PublisherDto> publisherList = publisherService.getAllPublisherOfBook(bookName);

		if(publisherList.isEmpty()) throw new CustomExceptionStatusCodeHandler(ErrorMessages.NO_RECORD_FOUND.getErrorMessage(), HttpStatus.OK);
		
		MappingJacksonValue mappingJacksonValue = null;

		for (PublisherDto publisherDto : publisherList) {
			PublisherResponseModel publisherResponseModel = new PublisherResponseModel();

			publisherResponseModel.setPublisherId(publisherDto.getPublisherId());
			publisherResponseModel.setPublisherName(publisherDto.getPublisherName());

			FilterProvider filters = new SimpleFilterProvider().addFilter("PublisherResponseFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept("publisherId", "publisherName"));
			mappingJacksonValue = new MappingJacksonValue(responseList);
			responseList.add(publisherResponseModel);
			mappingJacksonValue.setFilters(filters);

		}

		return mappingJacksonValue;

	}
	
	//  FORM_URLENCODED_VALUE

//	@RequestMapping(value = "/patientdetails", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//	public @ResponseBody List<PatientProfileDto> getPatientDetails(
//	        PatientProfileDto name) {
//
//
//	    List<PatientProfileDto> list = new ArrayList<PatientProfileDto>();
//	    list = service.getPatient(name);
//	    return list;
//	}
	
	//  Form Data
	
//	public void createNewObjectWithImage(@RequestParam("model") String model, @RequestParam(value = "file", required = false) MultipartFile file)
	
//	@RequestMapping(value = Array(“/{id}”), method = Array(RequestMethod.POST))
//	def upload(
//	           @PathVariable(“id”) id: String,
//	           @RequestHeader(“Authorization”) authHeader: String,
//	           @RequestParam(“file”) multipartFile: MultipartFile,
//	           @RequestParam(“owner_id”) ownerId: String,
//	           @RequestParam(“name”) name: String
//	          ) = {
//	  
//	  /* Some neat uploading goes on here */
//	}
}
