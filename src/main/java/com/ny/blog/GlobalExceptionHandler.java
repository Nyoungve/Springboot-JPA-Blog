package com.ny.blog;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	//모든 Exception 타도록 추가....
	@ExceptionHandler(value=Exception.class)
	public String handleException(Exception e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
		
	//IllegalArgumentException 추가....
//	@ExceptionHandler(value=IllegalArgumentException.class)
//	public String handleArgumentException(IllegalArgumentException e) {
//		return "<h1>"+e.getMessage()+"</h1>";
//	}
//	
//	@ExceptionHandler(value=EmptyResultDataAccessException.class)
//	public String handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
//		return "<h1>"+e.getMessage()+"</h1>";
//	}
	
}
