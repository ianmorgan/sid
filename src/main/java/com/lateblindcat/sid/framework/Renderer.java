package com.lateblindcat.sid.framework;

/**
 * A simple interface to invoke any rendering engine 
 * 
 * @author Ian Morgan
 *
 */
public interface Renderer {
	
	StringExpression render(Context context, StringExpression template); 

}
