package com.tweetapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.KafkaMessage;
import com.tweetapp.model.Reply;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
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

	@Autowired
	private KafkaProducerService kafkaProducerService;

	private KafkaMessage kafkaMessage = new KafkaMessage();

	public String addNewTweet(String username, Tweet tweet) throws UserNotFoundException {
		log.info("START - addNewTweet() of TweetService");
		User user = userRepository.findByFirstName(username);
		if (user == null) {
			kafkaMessage.setMessage("No User found with Username - " + username);
			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - addNewTweet() of TweetService");
			log.info("END - postNewTweet() of TweetAppController");
			throw new UserNotFoundException("No User found with Username - " + username);
		} else {
			tweet.setUser(userRepository.findByFirstName(username));
			tweetRepository.save(tweet);
			List<Tweet> tweetsList = new ArrayList<>();
			tweetsList.add(tweet);
			user.setTweets(tweetsList);
			userRepository.save(user);
			kafkaMessage.setMessage("Tweet Posted Successfully.");
			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - addNewTweet() of TweetService");
			log.info("END - postNewTweet() of TweetAppController");
			return "Tweet Posted Successfully.";
		}

	}

	public List<Tweet> getTweetsByUsername(String username) throws TweetNotFoundException {
		log.info("START - getTweetsByUsername() of TweetService");
		List<Tweet> tweetsList = tweetRepository.findByUser_FirstName(username);
		if (tweetsList.isEmpty()) {
			kafkaMessage.setMessage("No Tweet Found for User " + username);
			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - getTweetsByUsername() of TweetService");
			log.info("END - getAllTweetsOfUser() of TweetAppController");
			throw new TweetNotFoundException("No Tweet Found for User " + username);
		} else {
			kafkaMessage.setMessage("Returned Tweets of provided User Successfully.");
			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - getTweetsByUsername() of TweetService");
			log.info("END - getAllTweetsOfUser() of TweetAppController");
			return tweetsList;
		}
	}

	public List<Tweet> returnAllTweets() throws TweetNotFoundException {
		log.info("START - returnAllTweets() of TweetService");
		List<Tweet> tweetsList = tweetRepository.findAll();
		if (tweetsList.isEmpty()) {
			kafkaMessage.setMessage("No Tweet Found.");
			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - returnAllTweets() of TweetService");
			log.info("END - getAllTweets() of TweetAppController");
			throw new TweetNotFoundException("No Tweet Found.");
		} else {
			kafkaMessage.setMessage("Returned all Tweets Successfully.");
			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - returnAllTweets() of TweetService");
			log.info("END - getAllTweets() of TweetAppController");
			return tweetsList;
		}
	}

	public String updateTweetById(String username, int id, String tweetContent)
			throws TweetNotFoundException{
		log.info("START - updateTweetById() of TweetService");
		Tweet tweet = tweetRepository.findById(id);
		if (tweet == null) {
			kafkaMessage.setMessage("No Tweet found with id - " + id);
			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - updateTweetById() of TweetService");
			log.info("END - updateTweet() of TweetAppController");
			throw new TweetNotFoundException("No Tweet found with id - " + id);
		} else {
			if (username.equals(tweet.getUser().getFirstName()) && id == tweet.getTweetId()) {
				tweet.setTweetContent(tweetContent);
				tweetRepository.save(tweet);
				kafkaMessage.setMessage("Tweet Updated Successfully.");
				kafkaProducerService.sendMessage(kafkaMessage);
				log.info("END - updateTweetById() of TweetService");
				log.info("END - updateTweet() of TweetAppController");
				return "Tweet Updated Successfully";
			} else {
				kafkaMessage.setMessage("Invalid Username or Tweet Id.");
				kafkaProducerService.sendMessage(kafkaMessage);
				log.info("END - updateTweetById() of TweetService");
				log.info("END - updateTweet() of TweetAppController");
				return "Invalid Username or Tweet Id.";
			}
		}

	}

	public String deleteTweetById(String username, int id) throws TweetNotFoundException {
		log.info("START - deleteTweetById() of TweetService");
		Tweet tweet = tweetRepository.findById(id);
		if (tweet == null) {
			kafkaMessage.setMessage("No Tweet found with id - " + id);
			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - deleteTweetById() of TweetService");
			log.info("END - deleteTweet() of TweetAppController");
			throw new TweetNotFoundException("No Tweet found with id - " + id);
		} else {
			if (username.equals(tweet.getUser().getFirstName()) && id == tweet.getTweetId()) {
				tweetRepository.deleteById(id);
				kafkaMessage.setMessage("Tweet Deleted Successfully.");
				kafkaProducerService.sendMessage(kafkaMessage);
				log.info("END - deleteTweetById() of TweetService");
				log.info("END - deleteTweet() of TweetAppController");
				return "Tweet Deleted Successfully";
			} else {
				kafkaMessage.setMessage("Invalid Username or Tweet Id.");
				kafkaProducerService.sendMessage(kafkaMessage);
				log.info("END - deleteTweetById() of TweetService");
				log.info("END - deleteTweet() of TweetAppController");
				return "Invalid Username or Tweet Id.";
			}
		}

	}

	public String likeTweet(String username, int id) throws TweetNotFoundException {
		log.info("START - likeTweet() of TweetService");
		Tweet tweet = tweetRepository.findById(id);
		if (tweet == null) {
			kafkaMessage.setMessage("No Tweet found with id - " + id);
			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - likeTweet() of TweetService");
			log.info("END - likeTweet() of TweetAppController");
			throw new TweetNotFoundException("No Tweet found with id - " + id);
		} else {
			Set<String> likedBySet = new HashSet<>();
			likedBySet.add(username);
			tweet.setLikedBy(likedBySet);
			tweetRepository.save(tweet);
			kafkaMessage.setMessage("Tweet Liked Successfully.");
			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - likeTweet() of TweetService");
			log.info("END - likeTweet() of TweetAppController");
			return "Tweet Liked Successfully.";
		}
	}

	public String replyToTweet(String username, int id, String tweetReply) throws TweetNotFoundException {
		log.info("START - replyToTweet() of TweetService");
		Tweet tweet = tweetRepository.findById(id);
		if (tweet == null) {
			kafkaMessage.setMessage("No Tweet found with id - " + id);
			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - replyToTweet() of TweetService");
			log.info("END - replyToTweet() of TweetAppController");
			throw new TweetNotFoundException("No Tweet found with id - " + id);
		} else {
			Reply reply = new Reply();
			reply.setReplyContent(tweetReply);
			reply.setRepliedBy(username);
			List<Reply> repliesList = new ArrayList<>();
			repliesList.add(reply);
			tweet.setReply(repliesList);
			replyRepositoy.save(reply);
			tweetRepository.save(tweet);
			kafkaMessage.setMessage("Tweet Reply Added Successfully.");
			kafkaProducerService.sendMessage(kafkaMessage);
			log.info("END - replyToTweet() of TweetService");
			log.info("END - replyToTweet() of TweetAppController");
			return "Tweet Reply Added Successfully.";
		}
	}
}
