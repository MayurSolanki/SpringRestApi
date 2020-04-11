package com.appsdeveloperblog.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.aspectj.weaver.tools.Trace;

@Entity
@Table(name="user_profile_image")
public class UserProfileImageEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2064915487550183991L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=30,nullable= false)
	private String userProfileImageId;
	
	@Column(length=1000,nullable= false, updatable = true)
	private String userProfileImageUrl;
	
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id", nullable = false)
    private UserEntity userDetails;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUserProfileImageId() {
		return userProfileImageId;
	}


	public void setUserProfileImageId(String userProfileImageId) {
		this.userProfileImageId = userProfileImageId;
	}


	public String getUserProfileImageUrl() {
		return userProfileImageUrl;
	}


	public void setUserProfileImageUrl(String userProfileImageUrl) {
		this.userProfileImageUrl = userProfileImageUrl;
	}


	public UserEntity getUserDetails() {
		return userDetails;
	}


	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}


    	


		
	

}
