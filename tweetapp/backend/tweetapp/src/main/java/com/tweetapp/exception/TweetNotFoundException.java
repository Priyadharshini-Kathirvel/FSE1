package com.tweetapp.exception;

public class TweetNotFoundException extends Exception{
	
	private static final long serialVersionUID = 7515536425404721609L;

	public TweetNotFoundException() {
		super();
	}
	
	public TweetNotFoundException(final String message) {
		super(message);
	}

}
