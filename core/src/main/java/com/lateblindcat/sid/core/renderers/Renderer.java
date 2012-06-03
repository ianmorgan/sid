package com.lateblindcat.sid.core.renderers;

import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Context;

/**
 * A simple interface to invoke any rendering engine. 
 * 
 * @author Ian Morgan
 *
 */
public interface Renderer {
	
	StringExpression render(Context context, StringExpression template); 

}
