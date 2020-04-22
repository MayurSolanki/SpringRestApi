package com.appsdeveloperblog.app.ws.service;

import java.util.List;

import com.appsdeveloperblog.app.ws.shared.dto.CourseDto;

public interface CourseService {
	
	List<CourseDto> getAllCourses();
	
	List<CourseDto> updateUserCourses(String userId, List<CourseDto> coursesList);

}
