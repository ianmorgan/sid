package com.lateblindcat.sid.core.exception;

/**
 * Indicates something bad has happened and there is no point continuing, for
 * instance there was a problem initializing the application.
 * 
 * @author Ian Morgan
 * 
 */
public class FatalException extends RuntimeException {
	private static final long serialVersionUID = 1651862351877201575L;

	public FatalException(String message) {
		super(message);
	}

	public FatalException(String message, Throwable cause) {
		super(message, cause);
	}
}
