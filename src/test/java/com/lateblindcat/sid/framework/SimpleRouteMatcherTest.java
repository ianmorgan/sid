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
		assertFalse (matcher.matches(servletRequest("GET", "/test/x")).matched);
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
		assertFalse (matcher.matches(servletRequest("GET", "/test/a/b/")).matched);
	}
	
	@Test
	public void simpleGetRequestWithGlobbingWildcard() {
		// setup:
		matcher = new SimpleRouteMatcher(new Route("GET:/test/**"));
		
		// verify:
		assertTrue (matcher.matches(servletRequest("GET", "/test/this")).matched);
		assertEquals("this",matcher.matches(servletRequest("GET", "/test/this")).expandedParts.head());
		assertTrue (matcher.matches(servletRequest("GET", "/test/a/b/c")).matched);
		assertEquals("a/b/c",matcher.matches(servletRequest("GET", "/test/a/b/c")).expandedParts.expandToPath());

		assertFalse (matcher.matches(servletRequest("GET", "/testx/this")).matched);
	}

	private Request servletRequest(String method, String path) {
		MockHttpServletRequest servletRequest = new MockHttpServletRequest();
		servletRequest.setPathInfo(path);
		servletRequest.setMethod(method);
		Request request = new Request (servletRequest);
		return request;
	}


	


}
