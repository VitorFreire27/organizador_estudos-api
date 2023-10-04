package com.senac.df.organizador.resources.exceptions;

public class EmailDuplicadoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmailDuplicadoException(String message, Throwable cause) {
		super(message, cause);

	}

	public EmailDuplicadoException(String message) {
		super(message);

	}

	
}
