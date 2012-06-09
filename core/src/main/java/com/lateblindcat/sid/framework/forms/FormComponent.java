package com.lateblindcat.sid.framework.forms;

import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.rack.RackRequest;

public interface FormComponent {
	
	StringExpression render(RackRequest request);

}
