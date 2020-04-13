package com.appsdeveloperblog.app.ws.ui.model.request;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.appsdeveloperblog.app.ws.io.entity.CoursesEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetailsRequestModel {

	private String firstName;
	private String lastName;
	private String email;
	private String password;

//	@JsonProperty("departmentInformation")    // Set this if you want to accept json object with custom name otherwise name will be according to dto poperty
	private DepartmentRequestModel department;

	private List<AddressRequestModel> addresses;
	
//	private List<CourseRequestModel> courses;
	private Set<CoursesEntity> courses = new HashSet<>();


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DepartmentRequestModel getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentRequestModel department) {
		this.department = department;
	}

	public List<AddressRequestModel> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressRequestModel> addresses) {
		this.addresses = addresses;
	}

	public Set<CoursesEntity> getCourses() {
		return courses;
	}

	public void setCourses(Set<CoursesEntity> courses) {
		this.courses = courses;
	}

//	public List<CourseRequestModel> getCourses() {
//		return courses;
//	}
//
//	public void setCourses(List<CourseRequestModel> courses) {
//		this.courses = courses;
//	}

	
	
}
