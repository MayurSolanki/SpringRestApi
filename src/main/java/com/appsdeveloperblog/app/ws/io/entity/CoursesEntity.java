package com.appsdeveloperblog.app.ws.io.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class CoursesEntity implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String courseId;

	@Column(unique =true, nullable =false)
	private String courseName;

	@ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
//    private Set<UserEntity> users = new HashSet<>();
     private List<UserEntity> users;


	@ManyToMany(mappedBy = "favCourses",fetch = FetchType.LAZY)
    private List<UserEntity> usersFavCourses;
	

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

//	public Set<UserEntity> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<UserEntity> users) {
//		this.users = users;
//	}

	
//	public Set<UserEntity> getUsers() {
//		return users;
//	}
//
//	public void addUsers(UserEntity userEntity) {
//		users.add(userEntity);
//		userEntity.getCourses().add(this);	
//		}
	

//	public List<UserEntity> getCourses() {
//		return users;
//	}
//
//	public void setCourses(List<UserEntity> courses) {
//		this.users = courses;
//	}
//	

	public List<UserEntity> getUsersFavCourses() {
		return usersFavCourses;
	}

	public void setUsersFavCourses(List<UserEntity> usersFavCourses) {
		this.usersFavCourses = usersFavCourses;
	}

	
	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
	
	
	

	
	

	
	
	



}
