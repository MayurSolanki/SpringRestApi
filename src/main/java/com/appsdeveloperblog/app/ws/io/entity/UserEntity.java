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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.IndexColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;

//@Data
//@EqualsAndHashCode(exclude = "publishers")

@Entity
@Table(name = "users")
public class UserEntity implements Serializable{

	private static final long serialVersionUID =4865903039190150223L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String userId;
	
	@Column(nullable=false,length =50)
	private String firstName;
	
	@Column(nullable=false,length =50)
	private String lastName;
	
	@Column(nullable=false,length =120, unique = true)
	private String email;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	
	private String emailVerificationToken;
	
	@Column(nullable=false,columnDefinition="boolean default false")
	private Boolean emailVerificationStatus =false;
	
	
	
	@OneToMany(mappedBy="userDetails", cascade=CascadeType.ALL,fetch = FetchType.EAGER)  // mapped by -> check name in UserEntity class, i.e "userEntity"
	@Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<AddressEntity> addresses;  // Table to which relation, child table relation
	
	
	@OneToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL,
            mappedBy = "userDetails")
    private UserProfileImageEntity userProfileImageEntity;
	
	
	@ManyToOne      //  TODO:  REMEMBER : No cascade type in many to one relationship in child
    @JoinColumn(name="department_id")
	DepartmentEntity department;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinTable(name = "users_courses",
    joinColumns = {
            @JoinColumn(name = "users_id", referencedColumnName = "id",
                   nullable = false, updatable = false)},
    inverseJoinColumns = {
            @JoinColumn(name = "course_id", referencedColumnName = "id",
                    nullable = false, updatable = false)})
	
//    private Set<CoursesEntity> courses = new HashSet<>();
	private List<CoursesEntity> courses;
	
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinTable(name = "users_fav_courses", 
    joinColumns = {
            @JoinColumn(name = "users_id", referencedColumnName = "id",
                   nullable = false, updatable = false)},
    inverseJoinColumns = {
            @JoinColumn(name = "course_id", referencedColumnName = "id",
                    nullable = false, updatable = false)})
//    private Set<CoursesEntity> courses = new HashSet<>();
	private List<CoursesEntity> favCourses;
	


	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	public UserProfileImageEntity getUserProfileImageEntity() {
		return userProfileImageEntity;
	}

	public void setUserProfileImageEntity(UserProfileImageEntity userProfileImageEntity) {
		this.userProfileImageEntity = userProfileImageEntity;
	}

	

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}


	public List<CoursesEntity> getCourses() {
		return courses;
	}

	public void setCourses(List<CoursesEntity> courses) {
		this.courses = courses;
	}

	public List<CoursesEntity> getFavCourses() {
		return favCourses;
	}

	public void setFavCourses(List<CoursesEntity> favCourses) {
		this.favCourses = favCourses;
	}


	

	
	
	
	
	
	
}
