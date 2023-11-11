package com.jwt3.jwt.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt3.jwt.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	//creating a custom finder method that would fetch user from database based on email
	
	public Optional<User> findByEmail(String email);

}
