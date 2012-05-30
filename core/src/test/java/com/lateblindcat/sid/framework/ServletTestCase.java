package com.lateblindcat.sid.framework;

import org.springframework.mock.web.MockHttpServletRequest;

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

}
