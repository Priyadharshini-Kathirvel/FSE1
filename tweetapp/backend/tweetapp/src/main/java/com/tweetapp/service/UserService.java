package com.tweetapp.service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.exception.InvalidTokenException;
import com.tweetapp.exception.InvalidInputException;
import com.tweetapp.exception.UserAlreadyExistException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.LoginCredentials;
import com.tweetapp.model.LoginResponse;
import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepository;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private KafkaProducerService kafkaProducerService;

	@Autowired
	private AuthenticationService authService;

//	private KafkaMessage kafkaMessage = new KafkaMessage();

	public User registerUser(User user) throws UserAlreadyExistException, InvalidInputException {

		log.info("START - registerUser() of UserService");

		if (userRepository.findByEmail(user.getEmail()) != null) {
//			kafkaMessage.setMessage("User Already Exist");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - registerUser() of UserService");
			log.info("END - registerUser() of TweetAppController");
			throw new UserAlreadyExistException("User Already Exist.");
		} else {
			if (!user.getPassword().equals(user.getConfirmPassword())) {
//				kafkaMessage.setMessage("Password and Confirm Password should be same.");
//				kafkaProducerService.sendMessage(kafkaMessage);
				log.info("END - registerUser() of UserService");
				log.info("END - registerUser() of TweetAppController");
				throw new InvalidInputException("Password and Confirm Password should be same.");
			} else {
				userRepository.save(user);
//				kafkaMessage.setMessage("User Registered Succssfully");
//				kafkaProducerService.sendMessage(kafkaMessage);
				log.info("END - registerUser() of UserService");
				log.info("END - registerUser() of TweetAppController");
				return user;
			}
		}

	}

	public LoginResponse loginUser(LoginCredentials loginCredentials)
			throws UserNotFoundException, InvalidInputException, InvalidTokenException {
		log.info("START - loginUser() of UserService");
		var user = userRepository.findByEmail(loginCredentials.getEmail());
		if (user == null) {
//			kafkaMessage.setMessage("User Not Found.");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - loginUser() of UserService");
			log.info("END - loginUser() of TweetAppController");
			throw new UserNotFoundException("User Not Found.");

		} else {
			if (user.getEmail().equals(loginCredentials.getEmail()) && user.getPassword().equals(loginCredentials.getPassword())) {
				user.setLoggedIn(true);
				userRepository.save(user);
				return authService.generateToken(loginCredentials);
			} else {
//					kafkaMessage.setMessage("Invalid user credentials received.");
//					kafkaProducerService.sendMessage(kafkaMessage);
					log.info("END - loginUser() of UserService");
					log.info("END - loginUser() of TweetAppController");
					throw new InvalidInputException("Invalid user credentials received.");
				}
		}
	}

	public User resetPassword(String email, String newPassword) throws UserNotFoundException, InvalidInputException {
		log.info("START - resetPassword() of UserService");
		var user = userRepository.findByEmail(email);
		if (user == null) {
//			kafkaMessage.setMessage("User Not Found.");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - resetPassword() of UserService");
			log.info("END - forgotPassword() of TweetAppController");
			throw new UserNotFoundException("User Not Found.");

		} else {
			user.setPassword(newPassword);
			user.setConfirmPassword(newPassword);
			userRepository.save(user);
//			kafkaMessage.setMessage("Password Reset Successfully.");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - resetPassword() of UserService");
			log.info("END - forgotPassword() of TweetAppController");
			return user;
		}

	}

	public List<User> returnAllUsers() throws UserNotFoundException {
		log.info("START - returnAllUsers() of UserService");
		List<User> usersList = userRepository.findAll();
		if (usersList.isEmpty()) {
//			kafkaMessage.setMessage("No User Found.");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - returnAllUsers() of UserService");
			log.info("END - getAllUsers() of TweetAppController");
			throw new UserNotFoundException("No User Found.");
		} else {
//			kafkaMessage.setMessage("Returned Users List Successfully.");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - returnAllUsers() of UserService");
			log.info("END - getAllUsers() of TweetAppController");
			return usersList;
		}
	}

	public User returnUserByUsername(String email) throws UserNotFoundException {
		log.info("START - returnUserByUsername() of UserService");
		var user = userRepository.findByEmail(email);
		if (user == null) {
//			kafkaMessage.setMessage("User not found with Email - " + email);
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - returnUserByUsername() of UserService");
			log.info("END - getUserByUsername() of TweetAppController");
			throw new UserNotFoundException("User not found with Email - " + email);
		} else {
//			kafkaMessage.setMessage("Returned User Successfully.");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - returnUserByUsername() of UserService");
			log.info("END - getUserByUsername() of TweetAppController");
			return user;
		}
	}

	public User logoutUser(String email) throws UserNotFoundException {

		log.info("START - logoutUser() of UserService");
		var user = userRepository.findByEmail(email);
		if (user == null) {
//			kafkaMessage.setMessage("User not found with Email - " + email);
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - logoutUser() of UserService");
			log.info("END - logoutUser() of TweetAppController");
			throw new UserNotFoundException("User not found with Email - " + email);
		} else {
			user.setLoggedIn(false);
			userRepository.save(user);
//			kafkaMessage.setMessage("User Logged Out Successfully.");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - logoutUser() of UserService");
			log.info("END - logoutUser() of TweetAppController");
			return user;
		}

	}

}
