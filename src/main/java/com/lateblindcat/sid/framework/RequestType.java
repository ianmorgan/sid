package com.lateblindcat.sid.framework;

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
