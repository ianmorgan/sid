package com.lateblindcat.sid.framework.exception;

/**
 * <p>
 * Base exception class for any problem when processing a request.
 * </p>
 * 
 * @author Ian Morgan
 * 
 */
public class ProcessingException extends RuntimeException {

	private static final long serialVersionUID = 1651862351877201575L;

	public ProcessingException(String message) {
		super(message);
	}
	
	public ProcessingException(String message, Throwable cause) {
		super(message,cause);
	}

}
