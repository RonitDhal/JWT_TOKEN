package com.jwt3.jwt.service;




import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt3.jwt.Repository.UserRepository;
import com.jwt3.jwt.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
// COMMENTING THESE LINES AS WE ARE NOW CONFIGURING WITH THE DATABASE IN THE LATER STAGE
	
//	private List<User> store= new ArrayList<>();
	
//	public UserService() {
//		store.add(new User(UUID.randomUUID().toString(),"Durgesh","durgesh@gmail.com"));
//		store.add(new User(UUID.randomUUID().toString(),"Ankit","ankit@gmail.com"));
//		store.add(new User(UUID.randomUUID().toString(),"Harsh","harsh@gmail.com"));
//	}
	
	//method declared for getting all users in the form of a list
	
	public List<User> getUsers()
	{
		return userRepository.findAll();
	}
	
	//method for creating a user
	public User createUser(User user) {
		
		user.setUserId(UUID.randomUUID().toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}
}
