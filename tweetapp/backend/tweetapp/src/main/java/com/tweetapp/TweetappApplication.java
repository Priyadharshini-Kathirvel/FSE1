package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableAutoConfiguration(exclude = {UserDetailsServiceAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class TweetappApplication {

	public static void main(String[] args) {
		log.info("START - main() of TweetappApplication");
		SpringApplication.run(TweetappApplication.class, args);
		log.info("END - main() of TweetappApplication");
	}

}
