package com.appsdeveloperblog.app.ws.ui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.aspectj.weaver.tools.Trace;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appsdeveloperblog.app.ws.exception.CustomExceptionStatusCodeHandler;
import com.appsdeveloperblog.app.ws.exception.UserServiceException;
import com.appsdeveloperblog.app.ws.service.AddressService;
import com.appsdeveloperblog.app.ws.service.AddressesService;
import com.appsdeveloperblog.app.ws.service.FileStorageService;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDto;
import com.appsdeveloperblog.app.ws.shared.dto.DepartmentDto;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.shared.dto.UserProfileImageDto;
import com.appsdeveloperblog.app.ws.ui.model.request.DepartmentRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.request.PasswordResetRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.request.RequestOperationEnum;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.AddressResponseModel;
import com.appsdeveloperblog.app.ws.ui.model.response.CustomErrorMessage;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;
import com.appsdeveloperblog.app.ws.ui.model.response.OperationStatusModel;
import com.appsdeveloperblog.app.ws.ui.model.response.ResponseOperationEnum;
import com.appsdeveloperblog.app.ws.ui.model.response.UserProfileImageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.appsdeveloperblog.app.ws.ui.model.response.UserDetailsResponseModel;


@RestController
@RequestMapping("users") // http://localhost:8080/users,
//@CrossOrigin(origins = "*")  // allow all origin

//After context path    http://localhost:8080/mobile-app-ws/users, 
//server.servlet.context-path=/mobile-app-ws 
// in application.properties

public class UserController {
	
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


	@Autowired
	UserService userService;

	@Autowired
	AddressesService addressesService;

	@Autowired
	AddressService addressService;
	
	@Autowired
    FileStorageService fileStorageService;
    
    @PostMapping("/uploadFile/{userId}")
    public UserProfileImageResponse uploadFile(@RequestParam("file") MultipartFile file ,@PathVariable String userId) {
    	
    	UserProfileImageDto userProfileImageDto  = fileStorageService.storeFile(file,userId);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(userProfileImageDto.getUserProfileImageUrl())
                .toUriString();
        
        
        

        return new UserProfileImageResponse(userProfileImageDto.getUserDetails().getUserId(),userProfileImageDto.getUserProfileImageUrl(), fileDownloadUri, file.getContentType(), file.getSize());
    }
    
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,  HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

	

//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public MappingJacksonValue getUser(@PathVariable String id) {

		UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();

		UserDto userDto = userService.getUserByUserId(id);

		BeanUtils.copyProperties(userDto, userDetailsResponseModel);
		
	    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName","lastName","email");
	    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

	    MappingJacksonValue mapping = new MappingJacksonValue(userDetailsResponseModel);
	    mapping.setFilters(filters);


		return mapping;

	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public MappingJacksonValue createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel)
			throws Exception {

		UserDetailsResponseModel userDetailResponseModel = new UserDetailsResponseModel();

//  	if(userDetailsRequestModel.getFirstName().isEmpty()) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage()); // change the message in error json object
// 		if(userDetailsRequestModel.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage()); // this will trigger 1st method in AppExceptionHandler class, for different gson representation
		if (userDetailsRequestModel.getFirstName().isEmpty())
			throw new NullPointerException("This object is null"); // this will trigger 2 nd method in
																	// AppExceptionHandler class

//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(userDetailsRequestModel, userDto);

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetailsRequestModel, UserDto.class);

		UserDto createdUserDto = userService.createUser(userDto);

//		BeanUtils.copyProperties(createdUserDto, userDetailResponseModel);
		userDetailResponseModel = modelMapper.map(createdUserDto, UserDetailsResponseModel.class);
		
		// Jackson mapping
		    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userId,firstName","lastName","email","department","addresses");
		    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		    MappingJacksonValue mapping = new MappingJacksonValue(userDetailResponseModel);
		    mapping.setFilters(filters);

		return mapping;
	}
	

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserDetailsResponseModel updateUser(@PathVariable String id,
			@RequestBody UserDetailsRequestModel userDetailsRequestModel) {

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetailsRequestModel, userDto);

		UserDto updatedUserDto = userService.updateUser(id, userDto);

		UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();
		BeanUtils.copyProperties(updatedUserDto, userDetailsResponseModel);

		return userDetailsResponseModel;
	}
	

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	// path param vs path variable,
	// https://medium.com/1developer/spring-requestparam-vs-queryparam-vs-pathvariable-vs-pathparam-7c5655e541ad
	public OperationStatusModel deleteUser(@PathVariable String id) {

		userService.deleteUser(id);

		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationEnum.DELETE.name());
		operationStatusModel.setOperationResult(ResponseOperationEnum.SUCCESS.name());

		return operationStatusModel;
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//	@CrossOrigin(origins = "*")  // allow all origin
//	@CrossOrigin(origins = "http://localhost:8083")  // allow from specific origin with port
//	@CrossOrigin(origins = {"http://localhost:8083","http://myblog.com:8084"})  // allow from list of origin

	public MappingJacksonValue getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {

		List<UserDetailsResponseModel> returnList = new ArrayList<UserDetailsResponseModel>();
		List<UserDto> usersList = userService.getUsers(page, limit);
		MappingJacksonValue mappingJacksonValue = null;

		for (UserDto userDto : usersList) {
			UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();

			BeanUtils.copyProperties(userDto, userDetailsResponseModel);

			 FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",
			 SimpleBeanPropertyFilter.filterOutAllExcept("userId","firstName","lastName","email"));
			    
				 mappingJacksonValue = new MappingJacksonValue(returnList);
	 
			   returnList.add(userDetailsResponseModel);	
			   
			   mappingJacksonValue.setFilters(filters);
			   

		}

		return mappingJacksonValue;

	}

	// https://localhost/mobile-app-ws/users/{userId}/addresses
	@GetMapping(path = "/{userId}/addresses", produces = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE })
	public List<AddressResponseModel> getUserAddresses(@PathVariable String userId,HttpServletResponse response) {
		
		List<AddressResponseModel> returnList = new ArrayList<AddressResponseModel>();

		List<AddressDto> addressesDto = addressesService.getUserAddresses(userId);

		if (addressesDto != null && !addressesDto.isEmpty()) {
			
			for (AddressDto addressDto : addressesDto) {
				ModelMapper modelMapper = new ModelMapper();
			   AddressResponseModel addressResponseModel =	modelMapper.map(addressDto, AddressResponseModel.class);
			   
			    returnList.add(addressResponseModel);
			}
//			java.lang.reflect.Type listType = new TypeToken<List<AddressResponseModel>>() {}.getType();
//			returnList = new ModelMapper().map(addressesDto, listType);
		}
		
			response.setHeader("My custom header", "ABCDXYZ");


		return returnList;

	}

	// https://localhost/mobile-app-ws/users/{userId}/addresses
	@GetMapping(path = "/{userId}/addresses/{addressId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	
	public AddressResponseModel getUserAddress(@PathVariable String addressId) {

		AddressDto addressDto = addressService.getAddressDetail(addressId);

		ModelMapper modelMapper = new ModelMapper();
		AddressResponseModel addressResponseModel = modelMapper.map(addressDto, AddressResponseModel.class);

		return addressResponseModel;
	}
	
	@GetMapping(path="/addresses", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public List<AddressResponseModel> getAddresses(){
		
		List<AddressResponseModel> returnAddressesList = new ArrayList<AddressResponseModel>();
		
		ModelMapper modelMapper = new ModelMapper();
		List<AddressDto> list    = addressesService.getAddresses();
		
		for (AddressDto addressDto : list) {
		    AddressResponseModel addressResponseModel = 	modelMapper.map(addressDto, AddressResponseModel.class);
		    
		    
		   
		    returnAddressesList.add(addressResponseModel);
		}
		
		
		
		return returnAddressesList;
		
	}
	
//	@RequestMapping(value = "/users-by-first-name}", method = RequestMethod.GET)
	@GetMapping(path = "/users-by-first-name",produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public MappingJacksonValue getUsersByFirstName(@RequestParam String firstName){
		
		List<UserDetailsResponseModel> returnList = new ArrayList<UserDetailsResponseModel>();
		ObjectMapper mapper = new ObjectMapper();
		List<UserDto> userList =    userService.findUsersByFirstName(firstName);
		ModelMapper modelMapper = new ModelMapper();
		MappingJacksonValue mappingJacksonValue = null;
		
		for (UserDto userDto : userList) {
		   
			UserDetailsResponseModel userDetailsResponseModel =	modelMapper.map(userDto, UserDetailsResponseModel.class);
		   
		   FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",
		   SimpleBeanPropertyFilter.filterOutAllExcept("firstName","lastName","email"));
		    
			
		   mappingJacksonValue = new MappingJacksonValue(returnList);
 
		    returnList.add(userDetailsResponseModel);	
		   
			mappingJacksonValue.setFilters(filters);
		} 
		
		
		return mappingJacksonValue;
		
	}
	
	

	
	
	
	@GetMapping(path="/password-reset-request", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel requestPasswordReset(@RequestBody PasswordResetRequestModel passwordResetRequestModel) {
		
		OperationStatusModel returnOperationStatusModel = new OperationStatusModel();
		
		boolean operationResult =  userService.requestPasswordReset(passwordResetRequestModel.getEmail());
		
		returnOperationStatusModel.setOperationName(RequestOperationEnum.REQUEST_PASSWORD_RESET.name());
		returnOperationStatusModel.setOperationResult(ResponseOperationEnum.ERROR.name());
		
		
		if(operationResult) {
			returnOperationStatusModel.setOperationResult(ResponseOperationEnum.SUCCESS.name());
		}
		
		return returnOperationStatusModel;
		
	}
	
	

	// Get User By department , so  users/department/{departmentid}
	@RequestMapping(value = "/department", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public MappingJacksonValue usersByDepartment(@RequestParam String departmentName) throws Exception{
		
    	ModelMapper modelMapper = new ModelMapper();
		MappingJacksonValue mappingJacksonValue = null;

		List<UserDetailsResponseModel>  usersByDepartmentList = new ArrayList<UserDetailsResponseModel>();
			                                
		   List<UserDto>  userList = userService.findUserByDepartmentName(departmentName);
		   
	
		   if(userList.isEmpty()) {
			   
//			   throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage()); 
			   
			   throw new CustomExceptionStatusCodeHandler(ErrorMessages.NO_RECORD_FOUND.getErrorMessage(), HttpStatus.NOT_FOUND);
			   
//			   CustomErrorMessage customErrorMessage = new CustomErrorMessage();
//			   customErrorMessage.setMessage("no data");
//			   customErrorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
//			   
			   
//			   mappingJacksonValue = new MappingJacksonValue(customErrorMessage);

			   
			   
			   
		   }else {
			   for (UserDto userDto : userList) {
				   UserDetailsResponseModel userDetailsResponseModel =   modelMapper.map(userDto,UserDetailsResponseModel.class);
				   
				   FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",
				   SimpleBeanPropertyFilter.filterOutAllExcept("userId","firstName","lastName","email"));
							    
				   mappingJacksonValue = new MappingJacksonValue(usersByDepartmentList);
					 
				   usersByDepartmentList.add(userDetailsResponseModel);
							   
				  mappingJacksonValue.setFilters(filters);
					
			   }
			 	
		   }
	   
	  
		
		
		return mappingJacksonValue;
	}
	

}
