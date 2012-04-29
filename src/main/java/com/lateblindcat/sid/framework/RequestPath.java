package com.lateblindcat.sid.framework;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @deprecated - use Params object 
 *
 */
public class RequestPath {

	private String path;
	private Params parts;

	public RequestPath(HttpServletRequest req) {
		this.path = req.getPathInfo();
	}

	public RequestPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public Params getParts() {
		if (parts == null) {
			String working = path;
			if (working.startsWith("/")) {
				working = working.substring(1, working.length());
			}

			parts = new Params(working.split("/"));
		}
		return parts;
	}

}
