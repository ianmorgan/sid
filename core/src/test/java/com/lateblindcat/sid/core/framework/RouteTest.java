package com.lateblindcat.sid.core.framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//@Test
public class RouteTest {

	@Test
	public void simpleGetRequest() {
		Route route = new Route("GET:/test");

		assertEquals("GET", route.requestType());
		assertEquals(1, route.parts().size());
		assertEquals("test", route.parts().head());
	}
	
	@Test
	public void simpleGetRequestWithMultipleParts() {
		Route route = new Route("GET:/test/this");

		assertEquals("GET", route.requestType());
		assertEquals(2, route.parts().size());
		assertEquals("test", route.parts().head());
		assertEquals("this", route.parts().tail().head());
	}
	
	
	@Test
	public void simpleGetRequestWithId() {
		Route route = new Route("GET:/test/:id");

		assertEquals("GET", route.requestType());
		assertEquals(2, route.parts().size());
		assertEquals("test", route.parts().head());
		assertEquals(":id", route.parts().tail().head());
		//assertTrue(route.named("id"));
	}



}
