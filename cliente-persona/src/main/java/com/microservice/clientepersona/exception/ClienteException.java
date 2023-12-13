package com.microservice.clientepersona.exception;

public class ClienteException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ClienteException(String mensaje) {
        super(mensaje);
    }

}
