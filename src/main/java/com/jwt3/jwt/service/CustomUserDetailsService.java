package com.jwt3.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt3.jwt.Repository.UserRepository;
import com.jwt3.jwt.entity.User;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//LOAD USER FROM DATABSE
		
		User user = userRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("User not found!!"));
	     
		return user;
	}

}
