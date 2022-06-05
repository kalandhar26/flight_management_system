package com.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.JwtRequest;
import com.flightapp.dto.JwtResponse;
import com.flightapp.repos.UsersRepository;
import com.flightapp.security.CustomUserDetailsService;
import com.flightapp.security.JwtUtil;



@RestController
@RequestMapping
@CrossOrigin
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService  customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UsersRepository userRepository;
	
	@PostMapping("/token")
	public ResponseEntity<?>  generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		System.out.println(jwtRequest);
		
		try {
		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));	
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentails");
		}
		
		// fine area..
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
		
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT"+token);
		
		// {"token":"value"}
		// return ResponseEntity.ok(new JwtResponse(token));
		System.out.println(userRepository.findByUserName(jwtRequest.getUserName()).getId());
		return ResponseEntity.ok(new JwtResponse(token,
				userRepository.findByUserName(jwtRequest.getUserName()).getRole(),
				userRepository.findByUserName(jwtRequest.getUserName()).getId()
				));	
	}

}
