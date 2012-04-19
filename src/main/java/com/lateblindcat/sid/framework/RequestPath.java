package com.lateblindcat.sid.framework;

import javax.servlet.http.HttpServletRequest;

public class RequestPath {

	private String path;
	private PartsList parts;

	public RequestPath(HttpServletRequest req) {
		this.path = req.getPathInfo();
		System.out.print(this.path);
	}

	public RequestPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public PartsList getParts() {
		if (parts == null) {
			String working = path;
			if (working.startsWith("/")) {
				working = working.substring(1, working.length());
			}

			parts = new PartsList(working.split("/"));
		}
		return parts;
	}

}
