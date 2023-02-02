package com.tweetapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tweetapp.model.ExceptionResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	private ExceptionResponse setExceptionResponseDetails(String message, String url) {
		log.info("START - setExceptionResponseDetails() of ExceptionHandlerControllerAdvice");
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setErrorMessage(message);
		exceptionResponse.setRequestedURI(url);
		log.info("END - setExceptionResponseDetails() of ExceptionHandlerControllerAdvice");
		return exceptionResponse;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest request) {
		log.info("START - handleUserNotFoundException() of ExceptionHandlerControllerAdvice");
		log.info("END - handleUserNotFoundException() of ExceptionHandlerControllerAdvice");
		return setExceptionResponseDetails(exception.getMessage(), request.getRequestURI());
	}
	
	@ExceptionHandler(TweetNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleTweetNotFoundException(TweetNotFoundException exception, HttpServletRequest request) {
		log.info("START - handleTweetNotFoundException() of ExceptionHandlerControllerAdvice");
		log.info("END - handleTweetNotFoundException() of ExceptionHandlerControllerAdvice");
		return setExceptionResponseDetails(exception.getMessage(), request.getRequestURI());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(Exception exception, HttpServletRequest request) {
		log.info("START - handleException() of ExceptionHandlerControllerAdvice");
		log.info("END - handleException() of ExceptionHandlerControllerAdvice");
		return setExceptionResponseDetails(exception.getMessage(), request.getRequestURI());
	}

}
