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

		boolean matched = false;
		PartsList routeParts = this.route.parts();
		PartsList requestParts = request.path.getParts();
		PartsList matchedParts = new PartsList();
		
		// TODO: This really could be quite a lot neater	
		while (true) {
			if (!routeParts.isEmpty() && !requestParts.isEmpty()) {		
				if (routeParts.head().equals("*")){
					matchedParts = matchedParts.append(requestParts.head());
				}
				else if (!requestParts.head().equals(routeParts.head())) {
					break;
				}
				routeParts = routeParts.tail();
				requestParts = requestParts.tail();
			}
			else {
				// have we consumed both sides of the list ?
				matched = routeParts.isEmpty() && requestParts.isEmpty();
				break;
			}
		}
		if (matched) {
			result.matched = true;
			result.expandedParts = matchedParts;
		} else {
			result.matched = false;
		}

		return result;
	}

}
