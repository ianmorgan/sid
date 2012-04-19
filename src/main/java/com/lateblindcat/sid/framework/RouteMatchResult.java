package com.lateblindcat.sid.framework;

public class RouteMatchResult {
	
	/**
	 * Did this request match this route?
	 */
	public boolean matched;

	/**
	 * If the route contained wildcard or named components 
	 * then the expanded values (i,e the value in the actual 
	 * request when matched( is here
	 */
	public PartsList expandedParts;
	
	//public PartsList remainingParts;
	//public HttpRequest remaining;

}
