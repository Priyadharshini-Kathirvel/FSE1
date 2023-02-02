package com.tweetapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.exception.InvalidInputException;
import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.Reply;
import com.tweetapp.model.Tweet;
import com.tweetapp.repository.ReplyRepositoy;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class TweetService {

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReplyRepositoy replyRepositoy;

//	@Autowired
//	private KafkaProducerService kafkaProducerService;

//	private KafkaMessage kafkaMessage = new KafkaMessage();

	public Tweet addNewTweet(String email, String tweetContent) throws UserNotFoundException {
		log.info("START - addNewTweet() of TweetService");
		var user = userRepository.findByEmail(email);
		if (user == null) {
//			kafkaMessage.setMessage("No User found with Email - " + email);
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - addNewTweet() of TweetService");
			log.info("END - postNewTweet() of TweetAppController");
			throw new UserNotFoundException("No User found with Email - " + email);
		} else {
			var tweet = new Tweet();
			tweet.setTweetContent(tweetContent);
			tweet.setUser(userRepository.findByEmail(email));
			tweetRepository.save(tweet);
			List<Tweet> tweetsList = new ArrayList<>();
			tweetsList.add(tweet);
			user.setTweets(tweetsList);
			userRepository.save(user);
//			kafkaMessage.setMessage("Tweet Posted Successfully.");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - addNewTweet() of TweetService");
			log.info("END - postNewTweet() of TweetAppController");
			return tweet;
		}

	}

	public List<Tweet> getTweetsByUsername(String email) throws TweetNotFoundException {
		log.info("START - getTweetsByUsername() of TweetService");
		List<Tweet> tweetsList = tweetRepository.findByUser_Email(email);
		if (tweetsList.isEmpty()) {
//			kafkaMessage.setMessage("No Tweet Found for User " + email);
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - getTweetsByUsername() of TweetService");
			log.info("END - getAllTweetsOfUser() of TweetAppController");
			throw new TweetNotFoundException("No Tweet Found for User " + email);
		} else {
//			kafkaMessage.setMessage("Returned Tweets of provided User Successfully.");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - getTweetsByUsername() of TweetService");
			log.info("END - getAllTweetsOfUser() of TweetAppController");
			return tweetsList;
		}
	}

	public List<Tweet> returnAllTweets() throws TweetNotFoundException {
		log.info("START - returnAllTweets() of TweetService");
		List<Tweet> tweetsList = tweetRepository.findAll();
		if (tweetsList.isEmpty()) {
//			kafkaMessage.setMessage("No Tweet Found.");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - returnAllTweets() of TweetService");
			log.info("END - getAllTweets() of TweetAppController");
			throw new TweetNotFoundException("No Tweet Found.");
		} else {
//			kafkaMessage.setMessage("Returned all Tweets Successfully.");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - returnAllTweets() of TweetService");
			log.info("END - getAllTweets() of TweetAppController");
			return tweetsList;
		}
	}

	public Tweet updateTweetById(String email, int id, String tweetContent)
			throws TweetNotFoundException, InvalidInputException{
		log.info("START - updateTweetById() of TweetService");
		var tweet = tweetRepository.findById(id);
		if (tweet == null) {
//			kafkaMessage.setMessage("No Tweet found with id - " + id);
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - updateTweetById() of TweetService");
			log.info("END - updateTweet() of TweetAppController");
			throw new TweetNotFoundException("No Tweet found with id - " + id);
		} else {
			if (email.equals(tweet.getUser().getEmail()) && id == tweet.getTweetId()) {
				tweet.setTweetContent(tweetContent);
				tweetRepository.save(tweet);
//				kafkaMessage.setMessage("Tweet Updated Successfully.");
//				kafkaProducerService.sendMessage(kafkaMessage);
				log.info("END - updateTweetById() of TweetService");
				log.info("END - updateTweet() of TweetAppController");
				return tweet;
			} else {
//				kafkaMessage.setMessage("Invalid Email or Tweet Id.");
//				kafkaProducerService.sendMessage(kafkaMessage);
				log.info("END - updateTweetById() of TweetService");
				log.info("END - updateTweet() of TweetAppController");
				throw new InvalidInputException("Invalid Username or Tweet Id.");
			}
		}

	}

	public String deleteTweetById(String email, int id) throws TweetNotFoundException {
		log.info("START - deleteTweetById() of TweetService");
		var tweet = tweetRepository.findById(id);
		if (tweet == null) {
//			kafkaMessage.setMessage("No Tweet found with id - " + id);
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - deleteTweetById() of TweetService");
			log.info("END - deleteTweet() of TweetAppController");
			throw new TweetNotFoundException("No Tweet found with id - " + id);
		} else {
			if (email.equals(tweet.getUser().getEmail()) && id == tweet.getTweetId()) {
				tweetRepository.deleteById(id);
//				kafkaMessage.setMessage("Tweet Deleted Successfully.");
//				kafkaProducerService.sendMessage(kafkaMessage);
				log.info("END - deleteTweetById() of TweetService");
				log.info("END - deleteTweet() of TweetAppController");
				return "Tweet Deleted Successfully";
			} else {
//				kafkaMessage.setMessage("Invalid Username or Tweet Id.");
//				kafkaProducerService.sendMessage(kafkaMessage);
				log.info("END - deleteTweetById() of TweetService");
				log.info("END - deleteTweet() of TweetAppController");
				return "Invalid Email or Tweet Id.";
			}
		}

	}

	public Tweet likeTweet(String email, int id) throws TweetNotFoundException {
		log.info("START - likeTweet() of TweetService");
		var tweet = tweetRepository.findById(id);
		if (tweet == null) {
//			kafkaMessage.setMessage("No Tweet found with id - " + id);
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - likeTweet() of TweetService");
			log.info("END - likeTweet() of TweetAppController");
			throw new TweetNotFoundException("No Tweet found with id - " + id);
		} else {
			Set<String> likedBySet = new HashSet<>();
			likedBySet.add(email);
			tweet.setLikedBy(likedBySet);
			tweetRepository.save(tweet);
//			kafkaMessage.setMessage("Tweet Liked Successfully.");
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - likeTweet() of TweetService");
			log.info("END - likeTweet() of TweetAppController");
			return tweet;
		}
	}

	public Tweet replyToTweet(String email, int id, String tweetReply) throws TweetNotFoundException {
		log.info("START - replyToTweet() of TweetService");
		var tweet = tweetRepository.findById(id);
		if (tweet == null) {
//			kafkaMessage.setMessage("No Tweet found with id - " + id);
//			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - replyToTweet() of TweetService");
			log.info("END - replyToTweet() of TweetAppController");
			throw new TweetNotFoundException("No Tweet found with id - " + id);
		} else {
			var reply = new Reply();
			reply.setReplyContent(tweetReply);
			reply.setRepliedBy(email);
			List<Reply> repliesList = new ArrayList<>();
			repliesList.add(reply);
			tweet.setReply(repliesList);
			replyRepositoy.save(reply);
			tweetRepository.save(tweet);
//			-;
			log.info("END - replyToTweet() of TweetService");
			log.info("END - replyToTweet() of TweetAppController");
			return tweet;
		}
	}
}
