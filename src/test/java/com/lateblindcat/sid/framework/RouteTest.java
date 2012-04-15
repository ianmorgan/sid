package com.lateblindcat.sid.framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//@Test
public class RouteTest {

	@Test
	public void simpleGetRequest() {
		Route route = new Route("GET:/test");

		assertEquals(RequestType.GET, route.requestType());
		assertEquals(1, route.parts().length);
		assertEquals("test", route.parts()[0]);
	}
	
	@Test
	public void simpleGetRequestWithMultipleParts() {
		Route route = new Route("GET:/test/this");

		assertEquals(RequestType.GET, route.requestType());
		assertEquals(2, route.parts().length);
		assertEquals("test", route.parts()[0]);
		assertEquals("this", route.parts()[1]);
	}
	
	@Test
	public void simpleGetRequestWithId() {
		Route route = new Route("GET:/test/:id");

		assertEquals(RequestType.GET, route.requestType());
		assertEquals(2, route.parts().length);
		assertEquals("test", route.parts()[0]);
		assertEquals(":id", route.parts()[1]);
		assertTrue(route.named("id"));
	}



}
