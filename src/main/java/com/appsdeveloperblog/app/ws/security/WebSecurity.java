package com.appsdeveloperblog.app.ws.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.appsdeveloperblog.app.ws.service.UserService;

// we disable api security for one methos i.e for create, now all endpoint need password
// 403 forbidden for unauthorised req. Access Denied

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserService mUserDetailsService;
	private final BCryptPasswordEncoder mBCryptPasswordEncoder;
	
	
	public WebSecurity(UserService userService,BCryptPasswordEncoder bCryptpasswordEncoder) {
		this.mUserDetailsService = userService;
		this.mBCryptPasswordEncoder= bCryptpasswordEncoder; 
	}
	
	// allow only signup url, rest will be secured
	// only signup method does not required authentication and authorization, Rest of all methods required validation
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		 .cors()  // CORS - cross origin request resource
		 .and() 
		.csrf().disable()  // disable 
		.authorizeRequests()   // authorization for SIgn up method , else required authentication
      	.antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL) //passsword security , HttpMethod.POST,
      	.permitAll()  // all permission to post i.e sign up
      	.antMatchers(SecurityConstants.H2_CONSOLE)
      	.permitAll()  
      	.antMatchers(HttpMethod.GET,"/localhost/**")    
      	.permitAll() 
      	.antMatchers(HttpMethod.GET,"/mobile-app-ws/**")    
      	.permitAll() 
    	.anyRequest().authenticated() // any other request should be authenticated
      	.and()
      	.addFilter(getAuthenticationFilter())
      	.addFilter(new AuthorizationFilter(authenticationManager()))
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // make REST api stateless
		
		http.headers().frameOptions().disable();  // disable to load our URL in browser frame, For H2 
	}
	
	// encrypt/encode password
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(mUserDetailsService).passwordEncoder(mBCryptPasswordEncoder);
	}
	
	//set custom authentication url i.e http://localhost:8080/users/users/login  instead of http://localhost:8080/login
	public AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
		authenticationFilter.setFilterProcessesUrl(SecurityConstants.CUSTOM_AUTHENTICATION_URL);
		authenticationFilter.setPostOnly(true);
		return authenticationFilter;
	}
	
	
	// Spring security configuration for CORS
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		final CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("*"));
		
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);   // allow all URL , not like users/login/...
		
		return  source;
	}
	
	
	
	
}
