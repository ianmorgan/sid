package com.lateblindcat.sid.core.framework;

/**
 * Enum representing the the underling Http Request method 
 * 
 * @author Ian Morgan
 *
 */
public enum RequestType {
	GET, POST;

	public static RequestType fromString(String type) {
		if ("GET".equals(type)) {
			return GET;
		}
		if ("POST".equals(type)) {
			return POST;
		}
		return null;
	}
}
