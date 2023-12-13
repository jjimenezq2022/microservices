package com.microservice.cuentamovimiento.exception;

public class MovimientoException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovimientoException(String mensaje) {
        super(mensaje);
    }

}
