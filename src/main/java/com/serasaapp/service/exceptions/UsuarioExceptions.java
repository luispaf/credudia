package com.serasaapp.service.exceptions;

import com.serasaapp.exceptionHandler.SerasaAppApplicationException;

public class UsuarioExceptions  extends SerasaAppApplicationException{

	private static final long serialVersionUID = 3789725839170853602L;

	public UsuarioExceptions() {}

    public UsuarioExceptions(String message) {
        super(message);
    }
    public UsuarioExceptions(String message, Throwable cause) {
        super(message, cause);
    }
    public UsuarioExceptions(Throwable cause) {
        super(cause);
    }    
    public UsuarioExceptions(Exception e) {
        super(e);
    }
}
