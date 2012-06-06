package com.lateblindcat.sid.rack;

import java.util.Iterator;
import java.util.Map;

import com.lateblindcat.sid.core.fp.StringExpression;

public interface Rack {

	public Response call(Env env);

	public class Response {
		public int status;
		public Map<String, StringExpression> headers;
		public Iterator<StringExpression> body;
	}
}
