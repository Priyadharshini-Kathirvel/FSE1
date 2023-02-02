package com.tweetapp.exception;

public class InvalidInputException extends Exception {

	private static final long serialVersionUID = 2656024907783195594L;

	public InvalidInputException() {
		super();
	}

	public InvalidInputException(final String message) {
		super(message);
	}

}
