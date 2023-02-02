package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class TweetappApplication {

	public static void main(String[] args) {
		log.info("START - main() of TweetappApplication");
		SpringApplication.run(TweetappApplication.class, args);
		log.info("END - main() of TweetappApplication");
	}

}
