package com.jtt.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * This is card custom exception class for java-technical-test application.
 * 
 * @author Vamsi_Gandala
 * @date 04-October-2021
 */
@Getter
@Setter
public class CardException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String error;
    private String message;

	public CardException(String message) {
		super(message);
	}

	public CardException(String error, String message) {
		super(message);
		this.error = error;
	}
}