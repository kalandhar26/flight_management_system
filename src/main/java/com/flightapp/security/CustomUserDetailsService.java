package com.flightapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flightapp.entities.Users;
import com.flightapp.repos.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsersRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Users user= userRepository.findByUserName(userName);
		if(user==null)
		{
			throw new  UsernameNotFoundException("user not found");
		}
		CustomUserDetails customUserDetails= new CustomUserDetails(user);
		return customUserDetails;
		
		//===============Manual Entry=============================
		
//		if(username.equals("Baba"))
//		{
//			return new User("Baba","Password123", new ArrayList<>());
//		}else {
//			throw new UsernameNotFoundException("User not found.!!");
//		}
		
		//==================Manual Entry=====================
	}

}
