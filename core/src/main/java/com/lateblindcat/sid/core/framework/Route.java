package com.lateblindcat.sid.core.framework;

import java.util.Arrays;
import java.util.List;

import com.lateblindcat.sid.core.fp.ImmutableList;


/**
 * A ruby on rails like routing model
 * 
 * @author Ian Morgan
 * 
 */
public class Route {

	private String requestType;
	private final ImmutableList<String> parts;
	

	/**
	 * 
	 * @param
	 */
	public Route(String route) {
		String[] split = split(route);
		requestType = split[0];
		parts = new ImmutableList<String>(buildParts(split[1]));
	}
	

	public String requestType() {
		return requestType;
	}

	public ImmutableList<String> parts() {
		return parts;
	}

	private List<String> buildParts(String url) {
		if (url.startsWith("/")) {
			url = url.substring(1, url.length());
		}
		return Arrays.asList(url.split("/"));
	}

	private String[] split(String route) {
		int index = route.indexOf(":");
		String[] results = new String[2];
		results[0] = route.substring(0, index);
		results[1] = route.substring(index + 1, route.length());
		return results;
	}
}
