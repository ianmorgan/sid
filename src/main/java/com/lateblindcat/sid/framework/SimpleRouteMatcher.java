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
 *</pre>
 *  
 * @author Ian Morgan 
 *
 */
public class SimpleRouteMatcher {
	private final Route route;

	public SimpleRouteMatcher(Route route) {
		this.route = route;
	}

	public RouteMatchResult matches(HttpRequest request) {
		RouteMatchResult result = new RouteMatchResult();
		//System.out.println(request.path.getParts());

		boolean matched = true;
		PartsList routeParts = this.route.parts();
		PartsList requestParts = request.path.getParts();
		PartsList matchedParts = new PartsList();
		
		if (requestParts.size() >= routeParts.size()) {
			for (int i = 0; i < route.parts().size(); i++) {
				if (routeParts.head().equals("*")){
					matchedParts = matchedParts.append(requestParts.head());
				}
				else if (!requestParts.head().equals(routeParts.head())) {
					matched = false;
					break;
				}
				routeParts = routeParts.tail();
				requestParts = requestParts.tail();
			}

		} else {
			matched = false;
		}
		if (matched) {
			result.matched = true;
			result.expandedParts = matchedParts;
			
			// result.remaining = new HttpRequest(route, new
			// RequestPath(.path.getPath().substring(pattern.length())));
		} else {
			result.matched = false;
		}

		return result;
	}

}
