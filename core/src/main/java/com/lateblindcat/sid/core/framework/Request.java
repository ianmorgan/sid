package com.lateblindcat.sid.core.framework;

import javax.servlet.http.HttpServletRequest;

/**
 * Represents the data in an HtpRequest. This is essentially a simplified
 * wrapper on the HttpServletRequest that is designed to work in conjunction
 * with the RouteMatcher.
 * 
 * 
 * @author Ian Morgan
 * 
 */
public class Request {

	private RequestType type;
	private Params params;

	public Request(HttpServletRequest req) {
		type = RequestType.fromString(req.getMethod());
		params = new Params(req);
	}

	public Request(Request matched, Params remaining) {
		params = remaining;
		type = matched.type;
	}

	public RequestType type() {
		return type;
	}
	
	public Params params() {
		return params;
	}



}
