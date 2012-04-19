package com.lateblindcat.sid.framework;


/**
 * <p>Tests to see if a route matches the HttpRequest</p>
 * 
 * <pre>
 * Examples:
 * 
 * Route              HttpRequest               Result
 *                    method  path
 * -----              -----------               -------
 * GET:/test          GET     /test             matched
 * GET:/test          GET     /test/this        not matched
 * GET:/test/*        GET     /test/this        matched 
 * 
 * 
 *</pre>
 * 
 * 
 * 
 * @author Ian Morgan 
 *
 */
public class SimpleRouteMatcher {
	private Route route;

	public SimpleRouteMatcher(Route route) {
		this.route = route;
	}

	public RouteMatchResult natches(HttpRequest request) {
		RouteMatchResult result = new RouteMatchResult();
		System.out.println(request.path.getParts());

		boolean matched = true;
		if (request.path.getParts().size() >= route.parts().length) {
			for (int i = 0; i < route.parts().length; i++) {
				if (!request.path.getParts().get(i).equals(route.parts()[i])) {
					matched = false;
					break;
				}
			}

		} else {
			matched = false;
		}
		if (matched) {
			result.matched = true;
			// result.remaining = new HttpRequest(route, new
			// RequestPath(.path.getPath().substring(pattern.length())));
		} else {
			result.matched = false;
		}

		return result;
	}

}
