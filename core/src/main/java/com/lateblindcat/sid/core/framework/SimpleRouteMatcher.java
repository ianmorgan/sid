package com.lateblindcat.sid.core.framework;

import java.util.ArrayList;
import java.util.List;

import com.lateblindcat.sid.core.fp.ImmutableList;
import com.lateblindcat.sid.core.framework.Params.Param;
import com.lateblindcat.sid.rack.RackRequest;


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

	public RouteMatchResult matches(RackRequest request) {
		
		System.out.println (">> route " + this.route.parts().toString());;
		RouteMatchResult result = new RouteMatchResult();

		boolean matched = false;
		//Params routeParts = this.route.parts();
		List<String> parts = new ArrayList<String>();
		for (Param p : route.parts()){
			parts.add(p.value);
		}
		ImmutableList<String> routeParts = new ImmutableList<String>(parts);
		Params requestParts = new Params(request);
		Params matchedParts = new Params();
		
		// TODO: This really could be quite a lot neater	
		while (true) {
			if (!routeParts.isEmpty() && !requestParts.isEmpty()) {		
				if (routeParts.head().equals("*")){
					matchedParts = matchedParts.append(requestParts.head().value);
					requestParts = requestParts.tail();
				}
				else if (routeParts.head().equals("**")){
					// TODO: this is a very simple globbing rule that 
					// assumes the ** is at the end of the param list
					while (!requestParts.isEmpty()){
						matchedParts = matchedParts.append(requestParts.head().value);
						requestParts = requestParts.tail();			
					}
				}
				else if (!requestParts.head().value.equals(routeParts.head())) {
					break;
				}
				else {
					requestParts = requestParts.tail();
				}
				routeParts = routeParts.tail();
		
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
