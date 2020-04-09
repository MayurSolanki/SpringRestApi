package com.appsdeveloperblog.app.ws;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// Global CORS configuration
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	
		registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE","OPTIONS").allowedOrigins("*");
		// to allow all method "*"
	}
	 

}
