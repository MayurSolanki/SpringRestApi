package com.appsdeveloperblog.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.service.DepartmentService;
import com.appsdeveloperblog.app.ws.shared.dto.DepartmentDto;
import com.appsdeveloperblog.app.ws.ui.model.request.DepartmentRequestModel;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.service.DepartmentService;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.DepartmentDto;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.ui.model.request.DepartmentRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.DepartmentResponseModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserDetailsResponseModel;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("department")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	

	@Autowired
	UserService userService;
	
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public DepartmentResponseModel saveDepartment(@RequestBody DepartmentRequestModel departmentRequestModel) {
		
		DepartmentResponseModel departmentResponseModel = new DepartmentResponseModel();
		
		DepartmentDto departmentDto = new DepartmentDto();
		BeanUtils.copyProperties(departmentRequestModel, departmentDto);
		
		
		DepartmentDto  departmentDto2 =   departmentService.addDepartment(departmentDto);
		
		BeanUtils.copyProperties(departmentDto2, departmentResponseModel);

		
		return departmentResponseModel;
		
	}
	
	

	@RequestMapping(value = "user/{userId}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE} , method = RequestMethod.PUT)
//	@PutMapping(path = "",)
	public MappingJacksonValue updateUserDepartment(@PathVariable String userId, @RequestBody DepartmentRequestModel departmentRequestModel) {
		
		DepartmentDto departmentDto = new DepartmentDto();
		
		BeanUtils.copyProperties(departmentRequestModel, departmentDto);
		
		
		UserDto userDto =   userService.addUserDepartment(userId, departmentDto);
		
				
		 ModelMapper modelMapper = new ModelMapper();
	 	UserDetailsResponseModel userDetailsResponseModel =  modelMapper.map(userDto, UserDetailsResponseModel.class);
	 	
	 	 
	 	    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userId","department");
		    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		    MappingJacksonValue mapping = new MappingJacksonValue(userDetailsResponseModel);
		    mapping.setFilters(filters);

		
		
		 return mapping;
	}
	
	
	
	//  Update department, Like make department from temp to permanent,  department/{id}
		@RequestMapping(value = "/{departmentId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	    public DepartmentResponseModel updateDepartment(@RequestBody DepartmentRequestModel departmentRequestModel) {
			
			DepartmentResponseModel departmentResponseModel = new DepartmentResponseModel();
			
			
			
			return departmentResponseModel;
		}
		
		


		

	
	
}
