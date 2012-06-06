package com.lateblindcat.sid.rack.servlet;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.rack.Env;
import com.lateblindcat.sid.rack.Rack.Response;

/**
 * Moves between the servlet world and the Rack world
 * 
 * @author Ian Morgan
 * 
 */

public class ServletRackBridge {

	public Env buildRackEnv(HttpServletRequest request) {
		Env env = new Env();
		env.REQUEST_METHOD = request.getMethod();
		env.PATH_INFO = request.getPathInfo();
		if (request.getQueryString() != null) {
			env.QUERY_STRING = request.getQueryString();
		}
		return env;
	}

	public void populateServletResponse(Response rackResponse, HttpServletResponse servletResponse) throws IOException {
		for (Entry<String, StringExpression> header : rackResponse.headers.entrySet()) {
			servletResponse.addHeader(header.getKey(), header.getValue().eval());
		}
		while (rackResponse.body.hasNext()) {
			servletResponse.getOutputStream().print(rackResponse.body.next().eval());
		}
		servletResponse.setStatus(rackResponse.status);
	}

}
