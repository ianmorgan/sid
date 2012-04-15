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
public class HttpRequest {
	
	public HttpRequest(HttpServletRequest req){
		path = new RequestPath(req);
		type = RequestType.fromString(req.getMethod());
	}
	
	public HttpRequest(HttpRequest matched, RequestPath remaining){
		path = remaining;
		type = matched.type;
	}

	public RequestType type;
	public RequestPath path;
}
