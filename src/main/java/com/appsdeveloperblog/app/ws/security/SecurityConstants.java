package com.appsdeveloperblog.app.ws.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.appsdeveloperblog.app.ws.SpringApplicationContext;

public class SecurityConstants {
	
//	@Autowired
//	private static ApplicationContext _applicationContext;

	public static final long EXPIRATION_TIME = 864000000;  //10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING= "Authorization";
	public static final String SIGN_UP_URL = "/users";
    public static final String CUSTOM_AUTHENTICATION_URL = "/users/login";

	
//	public static final String TOKEN_SECRET = "jf9i4jgu83nfl0";
	
	public static String getTokenSecret() {
		

		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
		
		
	}
}
