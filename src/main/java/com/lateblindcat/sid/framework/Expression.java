package com.lateblindcat.sid.framework;

/**
 * 
 * A generic interface for defining a lamda like expression that can 
 * be evaluated by calling its eval() method.
 * 
 * @author Ian Morgan
 *
 * @param <T> The class of the object returned by the expression
 */
public interface Expression<T> {

	T eval();
	
}
