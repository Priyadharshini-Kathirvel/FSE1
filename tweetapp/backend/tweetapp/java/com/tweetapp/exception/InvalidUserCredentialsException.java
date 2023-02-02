package com.tweetapp.exception;

public class InvalidUserCredentialsException extends Exception {

	private static final long serialVersionUID = 2656024907783195594L;

	public InvalidUserCredentialsException() {
		super();
	}

	public InvalidUserCredentialsException(final String message) {
		super(message);
	}

}
