package com.appsdeveloperblog.app.ws.shared.dto;

public class UserProfileImageDto {

	
	private long id;
	private String userProfileImageId;
	private String userProfileImageUrl;
    private UserDto userDetails;
   
	
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
	
	public UserDto getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDto userDetails) {
		this.userDetails = userDetails;
	}
	
	
	
    
}
