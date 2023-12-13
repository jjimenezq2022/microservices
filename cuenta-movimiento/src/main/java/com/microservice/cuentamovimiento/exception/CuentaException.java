package com.microservice.cuentamovimiento.exception;

public class CuentaException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CuentaException(String mensaje) {
        super(mensaje);
    }

}
