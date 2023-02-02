package com.tweetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.exception.InvalidTokenException;
import com.tweetapp.exception.InvalidInputException;
import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.exception.UserAlreadyExistException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.LoginCredentials;
import com.tweetapp.model.LoginResponse;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.service.AuthenticationService;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
public class TweetAppController {

	@Autowired
	private UserService userService;
	@Autowired
	private TweetService tweetService;

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/api/v1.0/tweets/register")
	public ResponseEntity<User> registerUser(@RequestBody @Valid User user)
			throws UserAlreadyExistException, InvalidInputException {
		log.info("START - registerUser() of TweetAppController");
		try {
			return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/api/v1.0/tweets/login")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginCredentials loginCredentials)
			throws UserNotFoundException, InvalidInputException, InvalidTokenException {
		log.info("START - loginUser() of TweetAppController");
		log.info("Username  : ", loginCredentials.getEmail());
		try {
			return new ResponseEntity<>(userService.loginUser(loginCredentials), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/api/v1.0/tweets/{email}/forgot")
	public ResponseEntity<User> forgotPassword(@RequestHeader("Authorization") String token, @PathVariable String email,
			@RequestBody String newPassword) throws UserNotFoundException {
		log.info("START - forgotPassword() of TweetAppController");
		try {
			if (authenticationService.validateToken(token)) {
				return new ResponseEntity<>(userService.resetPassword(email, newPassword), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/api/v1.0/tweets/all")
	public ResponseEntity<List<Tweet>> getAllTweets(@RequestHeader("Authorization") String token)
			throws TweetNotFoundException {
		log.info("START - getAllTweets() of TweetAppController");
		try {
			if (authenticationService.validateToken(token)) {
				return new ResponseEntity<>(tweetService.returnAllTweets(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/api/v1.0/tweets/users/all")
	public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String token)
			throws UserNotFoundException {
		log.info("START - getAllUsers() of TweetAppController");
		try {
			if (authenticationService.validateToken(token)) {
				return new ResponseEntity<>(userService.returnAllUsers(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/api/v1.0/tweets/user/search/{email}")
	public ResponseEntity<User> getUserByUsername(@RequestHeader("Authorization") String token,
			@PathVariable String email) throws UserNotFoundException {
		log.info("START - getUserByUsername() of TweetAppController");
		try {
			if (authenticationService.validateToken(token)) {
				return new ResponseEntity<>(userService.returnUserByUsername(email), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/api/v1.0/tweets/{email}")
	public ResponseEntity<List<Tweet>> getAllTweetsOfUser(@RequestHeader("Authorization") String token,
			@PathVariable String email) throws TweetNotFoundException {
		log.info("START - getAllTweetsOfUser() of TweetAppController");
		try {
			if (authenticationService.validateToken(token)) {
				return new ResponseEntity<>(tweetService.getTweetsByUsername(email), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/api/v1.0/tweets/{email}/add")
	public ResponseEntity<Tweet> postNewTweet(@RequestHeader("Authorization") String token, @PathVariable String email,
			@RequestBody String tweetContent) throws UserNotFoundException {
		log.info("START - postNewTweet() of TweetAppController");
		try {
			if (authenticationService.validateToken(token)) {
				return new ResponseEntity<>(tweetService.addNewTweet(email, tweetContent), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/api/v1.0/tweets/{email}/update/{id}")
	public ResponseEntity<Tweet> updateTweet(@RequestHeader("Authorization") String token, @PathVariable String email,
			@PathVariable int id, @RequestBody String tweetContent) throws TweetNotFoundException {
		log.info("START - updateTweet() of TweetAppController");
		try {
			if (authenticationService.validateToken(token)) {
				return new ResponseEntity<>(tweetService.updateTweetById(email, id, tweetContent), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/api/v1.0/tweets/{email}/delete/{id}")
	public ResponseEntity<HttpStatus> deleteTweet(@RequestHeader("Authorization") String token,
			@PathVariable String email, @PathVariable int id) throws TweetNotFoundException {
		log.info("START - deleteTweet() of TweetAppController");
		try {
			if (authenticationService.validateToken(token)) {
				tweetService.deleteTweetById(email, id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("api/v1.0/tweets/{email}/like/{id}")
	public ResponseEntity<Tweet> likeTweet(@RequestHeader("Authorization") String token, @PathVariable String email,
			@PathVariable int id) throws TweetNotFoundException {
		log.info("START - likeTweet() of TweetAppController");
		try {
			if (authenticationService.validateToken(token)) {
				return new ResponseEntity<>(tweetService.likeTweet(email, id), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/api/v1.0/tweets/{email}/reply/{id}")
	public ResponseEntity<Tweet> replyToTweet(@RequestHeader("Authorization") String token, @PathVariable String email,
			@PathVariable int id, @RequestBody String tweetReply) throws TweetNotFoundException {
		log.info("START - replyToTweet() of TweetAppController");
		try {
			if (authenticationService.validateToken(token)) {
				return new ResponseEntity<>(tweetService.replyToTweet(email, id, tweetReply), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/api/v1.0/tweets/{email}/logout")
	public ResponseEntity<User> logoutUser(@RequestHeader("Authorization") String token, @PathVariable String email
			) throws UserNotFoundException {
		log.info("START - logoutUser() of TweetAppController");
		try {
			if (authenticationService.validateToken(token)) {
				return new ResponseEntity<>(userService.logoutUser(email), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
