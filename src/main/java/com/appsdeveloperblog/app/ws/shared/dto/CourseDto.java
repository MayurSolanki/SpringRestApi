package com.appsdeveloperblog.app.ws.shared.dto;

import java.util.List;
import java.util.Set;

import com.appsdeveloperblog.app.ws.io.entity.CoursesEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;

public class CourseDto {
	
	
	private long id;
	private String courseId;
	private String courseName;
	
//	 private Set<UserDto> users;
	 private List<UserEntity> users;
	

	
	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

//	public Set<UserDto> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<UserDto> users) {
//		this.users = users;
//	}

	


	
	
}
