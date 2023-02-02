package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer>{
	Tweet findById(int id);
	List<Tweet> findByUser_FirstName(String username);
	

}
