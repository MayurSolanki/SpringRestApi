package com.appsdeveloperblog.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.appsdeveloperblog.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto userDto);
	
	UserDto getUser(String email);
	
	UserDto updateUser(String userId,UserDto userDto);
	
	void deleteUser(String userId);
	
	UserDto getUserByUserId(String id);

	
	
	
	
}
