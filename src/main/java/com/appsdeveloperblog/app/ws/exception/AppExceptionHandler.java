package com.appsdeveloperblog.app.ws.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.appsdeveloperblog.app.ws.ui.model.response.CustomErrorMessage;

// This uses  UserServiceException class which is already created by us
@ControllerAdvice
public class AppExceptionHandler {

	// This method will handle only user specific exceptions
	@ExceptionHandler(value = { UserServiceException.class })
	public ResponseEntity<Object> handleUserServiceException(UserServiceException userServiceException,
			WebRequest webRequest) {

		// userServiceException.getMessage() , change this value to json object of you
		// wants object instead of String

		// if only string then response like be => Missing required field. Please check
		// documentation for required fields

//		return new ResponseEntity<>(userServiceException.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

		CustomErrorMessage customErrorMessage = new CustomErrorMessage();
		customErrorMessage.setTimeStamp(new Date());
		customErrorMessage.setMessage(userServiceException.getMessage());
		customErrorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

//     if custom Object then response like be => Missing required field. Please check documentation for required fields 

		return new ResponseEntity<>(customErrorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	// This method will handle Common null pointer exception
	@ExceptionHandler(value = { Exception.class })	
	// This method will handle Common null pointer exception
	@ExceptionHandler(value = {Exception.class })
	public ResponseEntity<Object> handleUserServiceException(Exception exception, WebRequest webRequest) {

		CustomErrorMessage customErrorMessage = new CustomErrorMessage(new Date(), exception.getMessage());

		return new ResponseEntity<>(customErrorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	

	@ExceptionHandler(value = { CustomExceptionHandler.class }) // For not found
	public ResponseEntity<Object> handleCustomException(CustomExceptionHandler customExceptionHandler,
			WebRequest webRequest) {

		CustomErrorMessage customErrorMessage = new CustomErrorMessage();
		customErrorMessage.setTimeStamp(new Date());
		customErrorMessage.setMessage(customExceptionHandler.getMessage());
		customErrorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());

//	     if custom Object then response like be => Missing required field. Please check documentation for required fields 

		return new ResponseEntity<>(customErrorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);

	}

	

	@ExceptionHandler(value = { CustomExceptionStatusCodeHandler.class }) // Resource Already Exist
	public ResponseEntity<Object> handleCustomException(CustomExceptionStatusCodeHandler customExceptionStatusCodeHandler,
			WebRequest webRequest) {

		CustomErrorMessage customErrorMessage = new CustomErrorMessage();
		customErrorMessage.setTimeStamp(new Date());
		customErrorMessage.setMessage(customExceptionStatusCodeHandler.getMessage());
		customErrorMessage.setStatusCode(customExceptionStatusCodeHandler.getHttpStatus().value());

//	     if custom Object then response like be => Missing required field. Please check documentation for required fields 

		return new ResponseEntity<>(customErrorMessage, new HttpHeaders(), customExceptionStatusCodeHandler.getHttpStatus());

	}
	
	

}
