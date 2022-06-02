package com.flightapp.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.flightapp.entities.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
	
	 @Query(value = "SELECT u FROM Users u where u.userName = ?1 and u.password = ?2 ")
	    Optional<Users> login(String username,String password);
	    Optional<Users> findByToken(String token);

}
