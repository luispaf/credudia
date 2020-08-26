package com.serasaapp.service.exceptions;

import com.serasaapp.exceptionHandler.SerasaAppApplicationException;

public class EmpresaExceptions  extends SerasaAppApplicationException{

	private static final long serialVersionUID = -525426767408552590L;

	public EmpresaExceptions() {}

    public EmpresaExceptions(String message) {
        super(message);
    }
    public EmpresaExceptions(String message, Throwable cause) {
        super(message, cause);
    }
    public EmpresaExceptions(Throwable cause) {
        super(cause);
    }    
    public EmpresaExceptions(Exception e) {
        super(e);
    }
}
