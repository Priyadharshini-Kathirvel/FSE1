package com.tweetapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.LoginCredentials;
import com.tweetapp.model.LoginResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationService {

	JwtUtilService jwtUtilService = new JwtUtilService();

	@Autowired
	UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	public LoginResponse generateToken(LoginCredentials loginCredentials) throws UserNotFoundException{
		log.info("START - generateToken() AuthenticationService");
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCredentials.getEmail(), loginCredentials.getPassword()));
		var token = new LoginResponse(jwtUtilService.generateToken(loginCredentials.getEmail()));
		log.info("END - generateToken() AuthenticationService");
		return token;
	}

	public boolean validateToken(String token) {
		log.info("START - authorization() AuthenticationService");
		token = token.substring(7);
		var user = userDetailsService.loadUserByUsername(jwtUtilService.extractUsername(token));
		if (Boolean.TRUE.equals(jwtUtilService.validateToken(token, user))) {
			log.info("END - authorization() AuthenticationService");
			return true;
		} else {
			log.info("END - authorization() AuthenticationService");
			return false;
		}
	}

}
