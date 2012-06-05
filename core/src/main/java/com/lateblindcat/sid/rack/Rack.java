package com.lateblindcat.sid.rack;

import java.util.Map;

import com.lateblindcat.sid.core.fp.StringExpression;

public interface Rack {

	public Response call (Env env);
	
	public class Response{
		int status;
		Map<String, StringExpression> headers;
		StringExpression [] body;
		
	}
}
