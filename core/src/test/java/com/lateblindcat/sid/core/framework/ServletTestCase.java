package com.lateblindcat.sid.core.framework;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.mock.web.MockHttpServletRequest;

import com.lateblindcat.sid.rack.Env;
import com.lateblindcat.sid.rack.RackRequest;

/**
 * <p>
 * A base class for unit tests that provides servlet mocking functionality
 * </p>
 * 
 * @author Ian Morgan
 * 
 */
public abstract class ServletTestCase {

	protected RackRequest servletRequest(String method, String path) {
		MockHttpServletRequest servletRequest = new MockHttpServletRequest();
		servletRequest.setPathInfo(path);
		servletRequest.setMethod(method);
		RackRequest request = new RackRequest(new Env(servletRequest));
		return request;
	}

	protected RackRequest servletRequest(String method, String path, Map<String, String> params) {
		MockHttpServletRequest servletRequest = new MockHttpServletRequest();
		servletRequest.setPathInfo(path);
		servletRequest.setMethod(method);
		for (Entry<String, String> entry : params.entrySet()) {
			servletRequest.addParameter(entry.getKey(), entry.getValue());
		}
		RackRequest request = new RackRequest(new Env(servletRequest));
		return request;
	}

}
