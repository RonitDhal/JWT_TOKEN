package com.jwt3.jwt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt3.jwt.entity.User;
import com.jwt3.jwt.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	//HANDLER FOR GETTING ALL THE USERS
	
	//http:localhost:8080/home/users
	@GetMapping("/users")
	public List<User> getUser()
	{
		System.out.println("getting users");
		return this.userService.getUsers();
	}
	
	//HANDLER FOR GETTING ONLY THE LOGIN USERS
	@GetMapping("/currentuser")
	public String getLoggedInUser(Principal principal)
	{
		return principal.getName();
	}

}
