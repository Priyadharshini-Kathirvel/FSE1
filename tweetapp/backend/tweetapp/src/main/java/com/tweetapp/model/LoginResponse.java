package com.tweetapp.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse implements Serializable {
	
	private static final long serialVersionUID = -7291414840438273225L;
	private final String token;

}
