package com.serasaapp.service.exceptions;

import com.serasaapp.exceptionHandler.SerasaAppApplicationException;

public class PrivilegioExceptions extends SerasaAppApplicationException{

	private static final long serialVersionUID = 8749081216223327238L;	

	public PrivilegioExceptions() {}

    public PrivilegioExceptions(String message) {
        super(message);
    }
    public PrivilegioExceptions(String message, Throwable cause) {
        super(message, cause);
    }
    public PrivilegioExceptions(Throwable cause) {
        super(cause);
    }    
    public PrivilegioExceptions(Exception e) {
        super(e);
    }
}
