package com.tweetapp.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 3575530557825760740L;
	
	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(final String message) {
		super(message);
	}

}
