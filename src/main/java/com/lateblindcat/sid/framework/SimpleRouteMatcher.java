package com.lateblindcat.sid.framework;

public class SimpleRouteMatcher {
	private String pattern;
	public SimpleRouteMatcher (String pattern){
		this.pattern = pattern;
	}
	
	public RouteMatchResult natches (Route route){
		RouteMatchResult result = new RouteMatchResult();
		System.out.println(route.path.getParts());
		if (route.path.getPath().startsWith(pattern)){
			result.matched = true;
			result.remaining = new Route(route, new RequestPath(route.path.getPath().substring(pattern.length())));
		}
		else {
			result.matched = false;
		}
		
		return result;
	}

}
