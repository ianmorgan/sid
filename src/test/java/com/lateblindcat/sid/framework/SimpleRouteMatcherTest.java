package com.lateblindcat.sid.framework;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class SimpleRouteMatcherTest {
	private SimpleRouteMatcher matcher;
	
	@Test
	public void simpleGetRequest() {
		// setup:
		matcher = new SimpleRouteMatcher(new Route("GET:/test"));
		
		// verify:
		assertTrue (matcher.matches(servletRequest("GET", "/test")).matched);
		assertFalse (matcher.matches(servletRequest("GET", "/Test")).matched);
		assertFalse (matcher.matches(servletRequest("GET", "/testx")).matched);
		assertFalse (matcher.matches(servletRequest("GET", "/xxx")).matched);
	}
	
	@Test
	public void getRequestWithNestedParts() {
		// setup:
		matcher = new SimpleRouteMatcher(new Route("GET:/test/this"));
		
		// verify:
		assertTrue (matcher.matches(servletRequest("GET", "/test/this")).matched);
		assertFalse (matcher.matches(servletRequest("GET", "/test")).matched);
		assertFalse (matcher.matches(servletRequest("GET", "/test/x")).matched);
	}
	
	
	@Test
	public void simpleGetRequestWithWildcard() {
		// setup:
		matcher = new SimpleRouteMatcher(new Route("GET:/test/*"));
		
		// verify:
		assertTrue (matcher.matches(servletRequest("GET", "/test/this")).matched);
		assertEquals("this",matcher.matches(servletRequest("GET", "/test/this")).expandedParts.head());
		assertFalse (matcher.matches(servletRequest("GET", "/testx/this")).matched);
	}

	private HttpRequest servletRequest(String method, String path) {
		MockHttpServletRequest servletRequest = new MockHttpServletRequest();
		servletRequest.setPathInfo(path);
		servletRequest.setMethod(method);
		HttpRequest request = new HttpRequest (servletRequest);
		return request;
	}


	


}
