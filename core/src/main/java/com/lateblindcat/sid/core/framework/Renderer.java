package com.lateblindcat.sid.core.framework;

import com.lateblindcat.sid.core.fp.StringExpression;

/**
 * A simple interface to invoke any rendering engine. 
 * 
 * @author Ian Morgan
 *
 */
public interface Renderer {
	
	StringExpression render(Context context, StringExpression template); 

}
