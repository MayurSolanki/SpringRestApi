package com.appsdeveloperblog.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.entity.CoursesEntity;
import com.appsdeveloperblog.app.ws.io.repositories.CourseRepository;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.service.CourseService;
import com.appsdeveloperblog.app.ws.shared.dto.CourseDto;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	CourseRepository courseRepository;
	
	
	@Autowired
	UserRepository userRepository;


	@Override
	public List<CourseDto> getAllCourses() {
		
		List<CoursesEntity> listCourseEntity = new ArrayList<CoursesEntity>();
		List<CourseDto> listCourseDto = new ArrayList<CourseDto>();
		ModelMapper modelMapper = new ModelMapper();

		
		listCourseEntity = (List<CoursesEntity>) courseRepository.findAll();
		
		for (CoursesEntity coursesEntity : listCourseEntity) {
			
			
			
		  CourseDto courseDto =	modelMapper.map(coursesEntity, CourseDto.class);
		  
		  listCourseDto.add(courseDto);
		}
		
		
		return listCourseDto;
	}
	
	
	
}
