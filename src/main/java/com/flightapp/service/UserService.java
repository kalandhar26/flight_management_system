package com.flightapp.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;

import com.flightapp.entities.Users;

public interface UserService {
	
	 String login(String username, String password);
	    Optional<User> findByToken(String token);
	    Users findById(Long id);

}
