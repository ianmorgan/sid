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
		assertEquals(1, route.parts().size());
		assertEquals("test", route.parts().head().value);
	}
	
	@Test
	public void simpleGetRequestWithMultipleParts() {
		Route route = new Route("GET:/test/this");

		assertEquals(RequestType.GET, route.requestType());
		assertEquals(2, route.parts().size());
		assertEquals("test", route.parts().head().value);
		assertEquals("this", route.parts().tail().head().value);
	}
	
	@Test
	public void simpleGetRequestWithId() {
		Route route = new Route("GET:/test/:id");

		assertEquals(RequestType.GET, route.requestType());
		assertEquals(2, route.parts().size());
		assertEquals("test", route.parts().head().value);
		assertEquals(":id", route.parts().tail().head().value);
		assertTrue(route.named("id"));
	}



}
