package com.lateblindcat.sid.core.framework;

import com.lateblindcat.sid.core.framework.Params.Param;

/**
 * A ruby on rails like routing model
 * 
 * @author Ian Morgan
 * 
 */
public class Route {

	private String requestType;
	private final Params parts;

	/**
	 * 
	 * @param
	 */
	public Route(String route) {
		String[] split = split(route);
		requestType = split[0];
		parts = buildParts(split[1]);
	}

	public boolean named(String name) {
		name = ":" + name;
		for (Param part : parts) {
			if (name.equals(part.value)) {
				return true;
			}
		}
		return false;
	}

	public String requestType() {
		return requestType;
	}

	public Params parts() {
		return parts;
	}

	private Params buildParts(String url) {
		if (url.startsWith("/")) {
			url = url.substring(1, url.length());
		}
		return new Params(url.split("/"));
	}

	private String[] split(String route) {
		int index = route.indexOf(":");
		String[] results = new String[2];
		results[0] = route.substring(0, index);
		results[1] = route.substring(index + 1, route.length());
		return results;
	}
}
