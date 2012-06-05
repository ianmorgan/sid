package com.lateblindcat.sid.core.framework;

import org.apache.log4j.Logger;



/**
 * This is the root form which any application level 
 * setting or functions can be accessed. It basically equivalent to a 
 * spring managed application context
 * 
 * In the core framework it is injected manually. 
 * 
 */
public interface AppContext {
	
	Logger logger(Class<?> context);

	ClassLoader classLoader();
	
}
