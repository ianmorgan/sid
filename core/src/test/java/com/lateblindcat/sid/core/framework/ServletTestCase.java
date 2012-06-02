package com.lateblindcat.sid.core.framework;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.mock.web.MockHttpServletRequest;

import com.lateblindcat.sid.core.framework.Request;

/**
 * <p>
 * A base class for unit tests that provides servlet mocking functionality
 * </p>
 * 
 * @author Ian Morgan
 * 
 */
public abstract class ServletTestCase {

	protected Request servletRequest(String method, String path) {
		MockHttpServletRequest servletRequest = new MockHttpServletRequest();
		servletRequest.setPathInfo(path);
		servletRequest.setMethod(method);
		Request request = new Request(servletRequest);
		return request;
	}

	protected Request servletRequest(String method, String path, Map<String, String> params) {
		MockHttpServletRequest servletRequest = new MockHttpServletRequest();
		servletRequest.setPathInfo(path);
		servletRequest.setMethod(method);
		for (Entry<String, String> entry : params.entrySet()) {
			servletRequest.addParameter(entry.getKey(), entry.getValue());
		}
		Request request = new Request(servletRequest);
		return request;
	}

}
