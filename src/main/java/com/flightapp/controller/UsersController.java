package com.flightapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.MessageResponse;
import com.flightapp.entities.Users;
import com.flightapp.repos.UsersRepository;
import com.flightapp.service.UserService;

@RestController
@RequestMapping("/api")
public class UsersController {
	
	@Autowired
	UserService service;
	
	@Autowired 
	UsersRepository repo;
	
	
	@GetMapping(value="/users")
	Iterable<Users> getAllUser()
	{
		return service.getAllUsers();
	}
	@GetMapping(value="/users/{id}")
	Optional<Users> getUserById(@PathVariable("id") int userId)
	{
		return service.getUserById(userId);
	}
	
	@PostMapping(value="/users/add")
	ResponseEntity<?> addNewUser(@RequestBody Users newUser)
	{	
		if (repo.existsByUserName(newUser.getUserName())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (repo.existsByUserEmail(newUser.getUserEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		newUser.setRole("ROLE_USER");
		service.addNewUser(newUser);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));	}

}
