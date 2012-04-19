package com.lateblindcat.sid.framework;

/**
 * A ruby on rails like routing model
 * 
 * @author Ian Morgan
 * 
 */
public class Route {

	private final RequestType requestType;
	private final PartsList parts;

	/**
	 * 
	 * @param
	 */
	public Route(String route) {
		String[] split = split(route);
		requestType = RequestType.fromString(split[0]);
		parts = buildParts(split[1]);
	}

	public boolean named(String name) {
		name = ":" + name;
		for (String part : parts) {
			if (name.equals(part)) {
				return true;
			}
		}
		return false;
	}

	public RequestType requestType() {
		return requestType;
	}

	public PartsList parts() {
		return parts;
	}

	private PartsList buildParts(String url) {
		if (url.startsWith("/")) {
			url = url.substring(1, url.length());
		}
		return new PartsList(url.split("/"));
	}

	private String[] split(String route) {
		int index = route.indexOf(":");
		String[] results = new String[2];
		results[0] = route.substring(0, index);
		results[1] = route.substring(index + 1, route.length());
		return results;
	}
}
