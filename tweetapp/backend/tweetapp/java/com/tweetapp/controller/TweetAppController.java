package com.tweetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.exception.InvalidUserCredentialsException;
import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.exception.UserAlreadyExistException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.LoginCredentials;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TweetAppController {

	@Autowired
	private UserService userService;
	@Autowired
	private TweetService tweetService;

	@PostMapping("/api/v1.0/tweets/register")
	public String registerUser(@RequestBody @Valid User user) throws UserAlreadyExistException {
		log.info("START - registerUser() of TweetAppController");
		return userService.registerUser(user);
	}

	@GetMapping("/api/v1.0/tweets/login")
	public String loginUser(@RequestBody LoginCredentials loginCredentials)
			throws UserNotFoundException, InvalidUserCredentialsException {
		log.info("START - loginUser() of TweetAppController");
		return userService.loginUser(loginCredentials);
	}

	@GetMapping("/api/v1.0/tweets/{username}/forgot")
	public String forgotPassword(@PathVariable String username, @RequestBody String newPassword)
			throws UserNotFoundException {
		log.info("START - forgotPassword() of TweetAppController");
		return userService.resetPassword(username, newPassword);
	}

	@GetMapping("/api/v1.0/tweets/all")
	public List<Tweet> getAllTweets() throws TweetNotFoundException {
		log.info("START - getAllTweets() of TweetAppController");
		return tweetService.returnAllTweets();
	}

	@GetMapping("/api/v1.0/tweets/users/all")
	public List<User> getAllUsers() throws UserNotFoundException {
		log.info("START - getAllUsers() of TweetAppController");
		return userService.returnAllUsers();
	}

	@GetMapping("/api/v/1.0/tweets/user/search/{username}")
	public User getUserByUsername(@PathVariable String username) throws UserNotFoundException {
		log.info("START - getUserByUsername() of TweetAppController");
		return userService.returnUserByUsername(username);
	}

	@GetMapping("/api/v1.0/tweets/{username}")
	public List<Tweet> getAllTweetsOfUser(@PathVariable String username) throws TweetNotFoundException {
		log.info("START - getAllTweetsOfUser() of TweetAppController");
		return tweetService.getTweetsByUsername(username);
	}

	@PostMapping("/api/v1.0/tweets/{username}/add")
	public String postNewTweet(@PathVariable String username, @RequestBody Tweet tweet) throws UserNotFoundException {
		log.info("START - postNewTweet() of TweetAppController");
		return tweetService.addNewTweet(username, tweet);
	}

	@PutMapping("/api/v1.0/tweets/{username}/update/{id}")
	public String updateTweet(@PathVariable String username, @PathVariable int id, @RequestBody String tweetContent)
			throws TweetNotFoundException {
		log.info("START - updateTweet() of TweetAppController");
		return tweetService.updateTweetById(username, id, tweetContent);
	}

	@DeleteMapping("/api/v1.0/tweets/{username}/delete/{id}")
	public String deleteTweet(@PathVariable String username, @PathVariable int id) throws TweetNotFoundException {
		log.info("START - deleteTweet() of TweetAppController");
		return tweetService.deleteTweetById(username, id);
	}

	@PutMapping("api/v1.0/tweets/{username}/like/{id}")
	public String likeTweet(@PathVariable String username, @PathVariable int id) throws TweetNotFoundException {
		log.info("START - likeTweet() of TweetAppController");
		return tweetService.likeTweet(username, id);
	}

	@PostMapping("/api/v1.0/tweets/{username}/reply/{id}")
	public String replyToTweet(@PathVariable String username, @PathVariable int id, @RequestBody String tweetReply)
			throws TweetNotFoundException {
		log.info("START - replyToTweet() of TweetAppController");
		return tweetService.replyToTweet(username, id, tweetReply);
	}

}
