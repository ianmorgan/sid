package com.lateblindcat.sid.framework.forms;

import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Request;

public interface FormComponent {
	
	StringExpression render(Request request);

}
