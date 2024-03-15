package com.jsp.agro.exception;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException{
	private String message="User Not Found";

	public String getMessage() {
		return message;
	}

	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}

	public UserNotFoundException() {
		super();
	}
}
