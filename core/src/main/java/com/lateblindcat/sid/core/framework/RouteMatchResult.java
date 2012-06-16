package com.lateblindcat.sid.core.framework;


public class RouteMatchResult {
	
	/**
	 * Did this request match this route?
	 */
	public boolean matched;

	/**
	 * If the route contained wildcard or named components 
	 * then the expanded values (i,e the value in the actual 
	 * request when matched( is here
	 * 
	 * @deprecated
	 * 
	 */
	public Params expandedParts;

	/**
	 * This holds the matched parameter values.
	 *    
	 */
	public MatchedParams matchedParams;
		

}
