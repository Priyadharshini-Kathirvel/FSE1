package com.tweetapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweetapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("START - loadUserByUsername() UserDetailsServiceImpl");
		try {
			log.info("Inside try block");
			var user = userRepository.findByEmail(email);
			log.info("User Details: " + user.getEmail() );
			log.info("END - loadUserByUsername() UserDetailsServiceImpl");
			return new org.springframework.security.core.userdetails.User(user.getEmail(),new BCryptPasswordEncoder().encode(user.getPassword()), new ArrayList<>());
		} catch (Exception e) {
			log.info("User Not Found Exception Occurred");
			log.info("END - loadUserByUsername() UserDetailsServiceImpl");
			throw new UsernameNotFoundException("No User found with username - "+email);
		}
	}

}
