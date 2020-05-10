package com.appsdeveloperblog.app.ws.ui.model.response;


import java.util.List;
import com.appsdeveloperblog.app.ws.ui.model.request.CourseRequestModel;
import com.fasterxml.jackson.annotation.JsonFilter;


@JsonFilter("SomeBeanFilter")
public class UserDetailsResponseModel {

	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	
//	@JsonProperty("departmentInformation")    // Set this if you want to accept json object with custom name otherwise name will be according to dto poperty
	private DepartmentResponseModel department;
	
	
	private List<AddressResponseModel> addresses;
	
    private List<CourseRequestModel> courses;
//	private Set<CoursesEntity> courses = new HashSet<>();


	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	
	
	
	public DepartmentResponseModel getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentResponseModel department) {
		this.department = department;
	}
	public List<AddressResponseModel> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressResponseModel> addresses) {
		this.addresses = addresses;
	}
	public List<CourseRequestModel> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseRequestModel> courses) {
		this.courses = courses;
	}
//	public Set<CoursesEntity> getCourses() {
//		return courses;
//	}
//	public void setCourses(Set<CoursesEntity> courses) {
//		this.courses = courses;
//	}
	
	
	
	
	
}
