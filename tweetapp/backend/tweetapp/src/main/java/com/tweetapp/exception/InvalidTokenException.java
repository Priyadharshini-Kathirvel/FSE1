package com.tweetapp.exception;

public class InvalidTokenException extends Exception {

	private static final long serialVersionUID = 5397950484169962787L;
	
	public InvalidTokenException() {
		super();
	}

	public InvalidTokenException(final String message) {
		super(message);
	}

}
