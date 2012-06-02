package com.lateblindcat.sid.core.fp;


/**
 * <p>
 * This interface is used in place of String or InputStream streams when passing
 * the result of template evaluation around
 * </p>
 * 
 * <p>
 * This supports the code style commonly used in Ruby frameworks (such as
 * "Rack", "Ruby on Rails" and "Sinatra") where functions can return either
 * objects (on which to_s is called) or code block that when evaluated yield a
 * string
 * </p>
 * 
 * @author Ian Morgan
 * 
 */
public interface StringExpression extends Expression<String> {
	
}
