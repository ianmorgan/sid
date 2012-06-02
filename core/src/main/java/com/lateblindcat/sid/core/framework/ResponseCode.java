package com.lateblindcat.sid.core.framework;

/**
 * Enum for common http response codes
 * 
 * @author Ian Morgan 
 *
 */
public enum ResponseCode {
	SC_OK(200), 
	SC_NOT_FOUND(404), 
	SC_INTERNAL_SERVER_ERROR(500);

	private int httpCode;

	ResponseCode(int value) {
		this.httpCode = value;
	}

	public int getHttpCode() {
		return httpCode;
	}
}
