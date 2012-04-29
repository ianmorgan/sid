package com.lateblindcat.sid.framework.forms;

import com.lateblindcat.sid.framework.Request;
import com.lateblindcat.sid.framework.StringExpression;

public interface FormComponent {
	
	StringExpression render(Request request);

}
