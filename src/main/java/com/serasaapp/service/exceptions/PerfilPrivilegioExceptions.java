package com.serasaapp.service.exceptions;

import com.serasaapp.exceptionHandler.SerasaAppApplicationException;

public class PerfilPrivilegioExceptions  extends SerasaAppApplicationException{

	private static final long serialVersionUID = 1983054385866823275L;
	
	public PerfilPrivilegioExceptions() {}

    public PerfilPrivilegioExceptions(String message) {
        super(message);
    }
    public PerfilPrivilegioExceptions(String message, Throwable cause) {
        super(message, cause);
    }
    public PerfilPrivilegioExceptions(Throwable cause) {
        super(cause);
    }    
    public PerfilPrivilegioExceptions(Exception e) {
        super(e);
    }

}
