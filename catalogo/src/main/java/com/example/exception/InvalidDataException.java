package com.example.exception;

public class InvalidDataException extends Exception {

	public InvalidDataException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public boolean hasErrors() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getErrors() {
		// TODO Auto-generated method stub
		return null;
	}

}
