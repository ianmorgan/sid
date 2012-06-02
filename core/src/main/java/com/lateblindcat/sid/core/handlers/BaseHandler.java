package com.lateblindcat.sid.core.handlers;

import java.util.Arrays;

/**
 * <p>
 * A base class providing common functions for use in handlers.
 * </p>
 * 
 * @author Ian Morgan
 * 
 */
public abstract class BaseHandler {

	// TODO - this logic should be contained with in the Request object
	protected String[] fileExtensions(String filePath) {
		String[] parts = filePath.split("\\.");

		if (parts.length >= 2) {
			return Arrays.copyOfRange(parts, 1, parts.length);
		} else {
			return new String[0];
		}
	}

}
