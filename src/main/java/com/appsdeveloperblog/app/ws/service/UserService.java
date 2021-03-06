package com.appsdeveloperblog.app.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.appsdeveloperblog.app.ws.shared.dto.DepartmentDto;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;


public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto userDto);
	
	UserDto getUser(String email);
	
	UserDto updateUser(String userId,UserDto userDto);
	
	void deleteUser(String userId);
	
	UserDto getUserByUserId(String id);
	
	List<UserDto> getUsers(int page, int limit);
	
	boolean requestPasswordReset(String email);

	List<UserDto> findUsersByFirstName(String firstName);
	
	UserDto addUserDepartment(String userId,DepartmentDto departmentDto);
	
	   List<UserDto> findUserByDepartmentName(String departmentName);

	
	
}
