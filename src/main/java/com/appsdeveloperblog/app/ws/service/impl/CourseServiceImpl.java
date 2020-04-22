package com.appsdeveloperblog.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.exception.CustomExceptionHandler;
import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.entity.CoursesEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repositories.CourseRepository;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.service.CourseService;
import com.appsdeveloperblog.app.ws.shared.dto.CourseDto;
import com.appsdeveloperblog.app.ws.shared.dto.Utils;
import com.appsdeveloperblog.app.ws.ui.model.response.CustomErrorMessage;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	CourseRepository courseRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;


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


	@Override
	public List<CourseDto> updateUserCourses(String userId, List<CourseDto> coursesList) {
		
	List<CourseDto> courseListToIterate = new ArrayList<CourseDto>();	
	List<CourseDto> courseListToReturn = new ArrayList<CourseDto>();	

	List<CoursesEntity> coursesEntityList = new ArrayList<CoursesEntity>();
	UserEntity userEntity	  =   userRepository.findByUserId(userId);
	
	if(userEntity == null) throw new CustomExceptionHandler(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage()); 
	
	courseListToIterate.addAll(coursesList);
	
	
	// Here we are checking If User Adding course to his profile, If course not exist then it will be automatically added to course table
	// If Exist then .....
	for (CourseDto courseDto : courseListToIterate) {
		
	     CoursesEntity coursesEntity =  	courseRepository.findByCourseName(courseDto.getCourseName());
	     
	     if(coursesEntity == null) {
	    	 CoursesEntity coursesEntityNew = new CoursesEntity();
	    	 
	    	 coursesEntityNew.setCourseId(utils.generateAddressId(30));
	    	 coursesEntityNew.setCourseName(courseDto.getCourseName());
	    	 
		     CoursesEntity coursesEntity1 =  	courseRepository.save(coursesEntityNew);
		     coursesEntityList.add(coursesEntity1);


	     }else {
		     coursesEntityList.add(coursesEntity);

	     }
	     
	     
	}
	
	userEntity.setCourses(coursesEntityList);
	
       UserEntity userEntityFromDb =	   userRepository.save(userEntity);
	
       for(int i= 0; i < userEntityFromDb.getCourses().size() ; i++) {
    	    
    	   CoursesEntity coursesEntity = userEntityFromDb.getCourses().get(i);
    	   
    	   
    	      ModelMapper modelMapper = new ModelMapper();    
    	     CourseDto courseDto =    modelMapper.map(coursesEntity, CourseDto.class);
    	                        
    	      
    	      
    	      courseListToReturn.add(courseDto);
       }
       
   
		return courseListToReturn;
	}
	
	
	
}
