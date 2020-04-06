package com.appsdeveloperblog.app.ws.ui.controller;


import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.exception.UserServiceException;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.ui.model.request.RequestOperationEnum;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;
import com.appsdeveloperblog.app.ws.ui.model.response.OperationStatusModel;
import com.appsdeveloperblog.app.ws.ui.model.response.ResponseOperationEnum;
import com.appsdeveloperblog.app.ws.ui.model.response.UserDetailsResponseModel;



@RestController
@RequestMapping("users") // http://localhost:8080/users,
//After context path    http://localhost:8080/mobile-app-ws/users, 
//server.servlet.context-path=/mobile-app-ws 
// in application.properties
public class UserController {

	@Autowired
	UserService userService;

	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @GetMapping(path = "/{id}", produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}  )
	public UserDetailsResponseModel getUser(@PathVariable String id) {
		
		UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();
		
	    UserDto userDto  = 	userService.getUserByUserId(id);
		
		BeanUtils.copyProperties(userDto, userDetailsResponseModel);
		
		
		return userDetailsResponseModel;
		
		
	}

	@PostMapping(
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) throws Exception {

		UserDetailsResponseModel userDetailResponseModel = new UserDetailsResponseModel();
		
		
//  	if(userDetailsRequestModel.getFirstName().isEmpty()) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage()); // change the message in error json object

// 		if(userDetailsRequestModel.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage()); // this will trigger 1st method in AppExceptionHandler class, for different gson representation

		if(userDetailsRequestModel.getFirstName().isEmpty()) throw new NullPointerException("This object is null");  // this will trigger 2 nd method in AppExceptionHandler class

		
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userDetailsRequestModel, userDto);

		UserDto createdUserDto = userService.createUser(userDto);

		BeanUtils.copyProperties(createdUserDto, userDetailResponseModel);

		return userDetailResponseModel;
	}

	@PutMapping(
			path ="/{id}",
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
	public UserDetailsResponseModel updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetailsRequestModel) {
		
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetailsRequestModel, userDto);
		
		
	   
		UserDto updatedUserDto = userService.updateUser(id,userDto);
		
		
		UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel(); 
		BeanUtils.copyProperties(updatedUserDto, userDetailsResponseModel);

		
		
		return userDetailsResponseModel;
	}

	@DeleteMapping(
			path ="/{id}",
			produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	// path param vs path variable, https://medium.com/1developer/spring-requestparam-vs-queryparam-vs-pathvariable-vs-pathparam-7c5655e541ad
	public OperationStatusModel deleteUser(@PathVariable String id) {
		
		userService.deleteUser(id);
		
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationEnum.DELETE.name());
		operationStatusModel.setOperationResult(ResponseOperationEnum.SUCCESS.name());
		
		return operationStatusModel;
	}
	
	
	@GetMapping(produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserDetailsResponseModel> getUsers(@RequestParam(value="page",defaultValue="1") int page, @RequestParam(value="limit",defaultValue="25") int limit){
		
		List<UserDetailsResponseModel> returnList = new ArrayList<UserDetailsResponseModel>();
		
		List<UserDto> usersList = userService.getUsers(page,limit);
		
		for (UserDto userDto : usersList) {
			UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();
			
			BeanUtils.copyProperties(userDto, userDetailsResponseModel);
			
			returnList.add(userDetailsResponseModel);
			
		}
		
		
		return returnList;
		
	}

}
