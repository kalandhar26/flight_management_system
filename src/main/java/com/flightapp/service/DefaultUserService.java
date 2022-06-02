package com.flightapp.service;

import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.flightapp.entities.Users;
import com.flightapp.repos.UserRepository;

@Service
public class DefaultUserService implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public String login(String username, String password) {
		   Optional<Users> users1 = userRepository.login(username,password);
	        if(users1.isPresent()){
	            String token = UUID.randomUUID().toString();
	            Users users2= users1.get();
	            users2.setToken(token);
	            userRepository.save(users2);
	            return token;
	        }

	        return StringUtils.EMPTY;
	}

	@Override
	public Optional<User> findByToken(String token) {
		 Optional<Users> users1= userRepository.findByToken(token);
	        if(users1.isPresent()){
	            Users users2 = users1.get();
	            User user= new User(users2.getUserName(), users2.getPassword(), true, true, true, true,
	                    AuthorityUtils.createAuthorityList("USER"));
	            return Optional.of(user);
	        }
	        return  Optional.empty();
	}

	@Override
	public Users findById(Long id) {
		 Optional<Users> users1= userRepository.findById(id);
	        return users1.orElse(null);
	}

}
