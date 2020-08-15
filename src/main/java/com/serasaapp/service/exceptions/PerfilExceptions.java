package com.serasaapp.service.exceptions;

import com.serasaapp.exceptionHandler.SerasaAppApplicationException;

public class PerfilExceptions extends SerasaAppApplicationException{

	private static final long serialVersionUID = -9046368041388788952L;
	
	public PerfilExceptions() {}

    public PerfilExceptions(String message) {
        super(message);
    }
    public PerfilExceptions(String message, Throwable cause) {
        super(message, cause);
    }
    public PerfilExceptions(Throwable cause) {
        super(cause);
    }    
    public PerfilExceptions(Exception e) {
        super(e);
    }
}
