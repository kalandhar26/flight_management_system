package com.flightapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.entities.Users;
import com.flightapp.repos.UsersRepository;

@Service
public class UserService {

	@Autowired
	UsersRepository userRepository;
	
	public Iterable<Users> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	public Optional<Users> getUserById(int id)
	{
		return userRepository.findById(id);
	}
	
	public void addNewUser(Users newUser)
	{
		userRepository.save(newUser);
	}

	public String getRolebyEmail(String userName) {

		return userRepository.findByUserEmail(userName).getRole();
		
		
	}
}
