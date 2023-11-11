package com.jwt3.jwt.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt3.jwt.entity.User;
import com.jwt3.jwt.security.JwtHelper;
import com.jwt3.jwt.service.UserService;
import com.jwt3.jwt.service.model.JwtRequest;
import com.jwt3.jwt.service.model.JwtResponse;

import org.slf4j.*;

@RestController
@RequestMapping("/auth") 
public class AuthController {

	//for fething the user details based on the username
	@Autowired
	private UserDetailsService userDetailsService;
	
	//for authenticating the user
	@Autowired
	private AuthenticationManager manager;
	
	
	//for creating the jwttoken
	@Autowired
	private JwtHelper helper;
	
	//for creating user we require to inject the userservice
	@Autowired
	private UserService userService;
	
	
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	//HANDLER FOR LOGIN OF USER
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request)
	{
		this.doAuthenticate(request.getEmail(),request.getPassword());
		
		UserDetails userDetails= userDetailsService.loadUserByUsername(request.getEmail());
		
		String token=this.helper.generateToken(userDetails);
		
		/////////////////////////////////////////////
		JwtResponse response = JwtResponse.builder()
		        .jwtToken(token)
		        .username(userDetails.getUsername())
		        .build();
		return new ResponseEntity<>(response, HttpStatus.OK);

		
	}

	private void doAuthenticate(String email, String password) {
		// TODO Auto-generated method stub
		  UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	        try {
	            manager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }

	      
		
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		
		return "Credentials invalid!!!";
	}
	
	//HANDLER FOR CREATING USER
	@PostMapping("/createuser")
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
	}
	
}
