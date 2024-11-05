package com.ezmaid.exception;

@SuppressWarnings("serial")
public class MaidNotFoundException extends RuntimeException {
	
	public MaidNotFoundException(String message) {
		super(message);
	}
}
