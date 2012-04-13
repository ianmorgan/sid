package com.lateblindcat.sid.framework;

import javax.servlet.http.HttpServletRequest;

public class Route {
	
	public Route(HttpServletRequest req){
		path = new RequestPath(req);
		type = RequestType.fromString(req.getMethod());
	}
	
	public Route(Route matched, RequestPath remaining){
		path = remaining;
		type = matched.type;
	}

	public RequestType type;
	public RequestPath path;
}
