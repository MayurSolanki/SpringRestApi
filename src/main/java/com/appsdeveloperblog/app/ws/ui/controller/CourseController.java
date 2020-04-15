package com.appsdeveloperblog.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.service.CourseService;
import com.appsdeveloperblog.app.ws.service.DepartmentService;
import com.appsdeveloperblog.app.ws.shared.dto.CourseDto;
import com.appsdeveloperblog.app.ws.shared.dto.DepartmentDto;
import com.appsdeveloperblog.app.ws.ui.model.request.DepartmentRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.CourseResponseModel;
import com.appsdeveloperblog.app.ws.ui.model.response.DepartmentResponseModel;

@RestController
@RequestMapping("courses")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CourseResponseModel> getCourses() {
		
		 List<CourseDto> coursesDtoList = new ArrayList<CourseDto>();
		 List<CourseResponseModel> coursesResponseModelList = new ArrayList<CourseResponseModel>();
		 ModelMapper modelMapper = new ModelMapper();

		
		 coursesDtoList  = 	courseService.getAllCourses();
		 
		 for (CourseDto courseDto : coursesDtoList) {
			 
			 CourseResponseModel  courseResponseModel = modelMapper.map(courseDto, CourseResponseModel.class);
			 coursesResponseModelList.add(courseResponseModel);	 
		 }
		
		return coursesResponseModelList;
		
	}
	
	
	
	
	
	// Get user by course, list of user who opt that course,
	
	
	
	
	
	
	// Add courses to the users, suppose user Wants to Opt for particular course later

}
