package com.appsdeveloperblog.app.ws.ui.model.response;

import java.util.List;

import com.appsdeveloperblog.app.ws.ui.model.request.CourseRequestModel;

public class UpdateCourseResponseModel {

	private String userId;
	private List<CourseResponseModel> courses;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<CourseResponseModel> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseResponseModel> courses) {
		this.courses = courses;
	}
	
	

}
