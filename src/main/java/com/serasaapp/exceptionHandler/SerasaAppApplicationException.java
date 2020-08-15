package com.serasaapp.exceptionHandler;

public class SerasaAppApplicationException  extends Exception {

	private static final long serialVersionUID = -7988221606506030394L;

	public SerasaAppApplicationException() {}

    public SerasaAppApplicationException(String message) {
        super(message);
    }
    public SerasaAppApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
    public SerasaAppApplicationException(Throwable cause) {
        super(cause);
    }    
    public SerasaAppApplicationException(Exception e) {
        super(e);
    }
}
