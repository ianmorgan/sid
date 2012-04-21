package com.lateblindcat.sid.framework.exception;

public class NotFoundException extends ProcessingException {

	private static final long serialVersionUID = -3738348150808081142L;

	public NotFoundException(String resourceType, String resoureName) {
		super("could not locate " + resourceType + " named " + resoureName);
	}

	public NotFoundException(String resoureName) {
		super("could not locate " + resoureName);
	}

}
