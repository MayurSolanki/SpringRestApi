package com.appsdeveloperblog.app.ws.shared.dto;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.appsdeveloperblog.app.ws.FileStorageProperties;
import com.appsdeveloperblog.app.ws.exception.CustomExceptionHandler;
import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessages;

@Component
public class Utils {
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final int ITERATIONS = 10000;
	private final int KEY_LENGTH = 256;
	
	
	public final String generateUserId(int length) {
		
		 return  generateRandomString(length);
		
	}
	
	
    public final String generateAddressId(int length) {
		
	    return  generateRandomString(length);
		
	}

    private String generateRandomString(int length) {
	  
        StringBuilder returnValue = new StringBuilder(length);
		
		for(int i=0;i<length;i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		
		return new String(returnValue);
   }
    
    

    
    
}
