package com.flightapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	
	public Users findByUserEmail(String userEmail);
	public Users findByUserName(String userName);
	Boolean existsByUserName(String userName);
	Boolean existsByUserEmail(String Email);

}
