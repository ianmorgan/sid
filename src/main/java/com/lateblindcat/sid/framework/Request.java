package com.lateblindcat.sid.framework;

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
	private RequestPath path;
	private Params params;

	public Request(HttpServletRequest req) {
		path = new RequestPath(req);
		type = RequestType.fromString(req.getMethod());
		params = new Params(req);
	}

	public Request(Request matched, RequestPath remaining) {
		path = remaining;
		type = matched.type;
	}

	public RequestType type() {
		return type;
	}
	
	public Params params() {
		return params;
	}

	// better be use params
	@Deprecated
	public RequestPath path() {
		return path;
	}

}
