package com.lateblindcat.sid.framework;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class SimpleRouteMatcherTest {
	
	@Test
	public void simpleGetRequest() {
		Route route = new Route("GET:/test");
		
		MockHttpServletRequest servletRequest = new MockHttpServletRequest();
		servletRequest.setPathInfo("/test");
		servletRequest.setMethod("GET");
		HttpRequest request = new HttpRequest (servletRequest);
		
		assertTrue (new SimpleRouteMatcher(route).natches(request).matched);
//
//		assertEquals(RequestType.GET, route.requestType());
//		assertEquals(1, route.parts().length);
//		assertEquals("test", route.parts()[0]);
	}

}
