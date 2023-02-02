package com.tweetapp.exception;

public class UserAlreadyExistException extends Exception {

	private static final long serialVersionUID = 2657055329022739184L;

	public UserAlreadyExistException() {
		super();
	}

	public UserAlreadyExistException(final String message) {
		super(message);
	}
}
