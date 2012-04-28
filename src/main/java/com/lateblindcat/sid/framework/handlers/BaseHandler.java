package com.lateblindcat.sid.framework.handlers;

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

	protected String[] fileExtensions(String filePath) {
		String[] parts = filePath.split("\\.");

		if (parts.length >= 2) {
			return Arrays.copyOfRange(parts, 1, parts.length);
		} else {
			return new String[0];
		}

	}

}
