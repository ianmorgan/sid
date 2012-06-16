package com.lateblindcat.sid.core.framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.lateblindcat.sid.core.fp.ImmutableList;

public class SimpleRouteMatcherTest extends ServletTestCase {
	private SimpleRouteMatcher matcher;

	@Test
	public void simpleGetRequest() {
		// setup:
		matcher = new SimpleRouteMatcher(new Route("GET:/test"));

		// verify:
		assertTrue(matcher.matches(servletRequest("GET", "/test")).matched);
		assertFalse(matcher.matches(servletRequest("GET", "/Test")).matched);
		assertFalse(matcher.matches(servletRequest("GET", "/testx")).matched);
		assertFalse(matcher.matches(servletRequest("GET", "/xxx")).matched);
		assertFalse(matcher.matches(servletRequest("GET", "/test/x")).matched);
	}

	@Test
	public void getRequestWithNestedParts() {
		// setup:
		matcher = new SimpleRouteMatcher(new Route("GET:/test/this"));

		// verify:
		assertTrue(matcher.matches(servletRequest("GET", "/test/this")).matched);
		assertFalse(matcher.matches(servletRequest("GET", "/test")).matched);
		assertFalse(matcher.matches(servletRequest("GET", "/test/x")).matched);
	}

	@Test
	public void getRequestWithParams() {
		// setup:
		matcher = new SimpleRouteMatcher(new Route("GET:/test/*"));

		// verify:
		Map<String, String> params = new HashMap<String, String>();
		params.put("q", "wibble");
		assertTrue(matcher.matches(servletRequest("GET", "/test", params)).matched);
		assertFalse(matcher.matches(servletRequest("GET", "/test")).matched);
	}

	@Test
	public void simpleGetRequestWithWildcard() {
		// setup:
		matcher = new SimpleRouteMatcher(new Route("GET:/test/*"));

		// verify:
		assertTrue(matcher.matches(servletRequest("GET", "/test/this")).matched);
		assertEquals("this", matcher.matches(servletRequest("GET", "/test/this")).matchedParams.splats().head());
		assertFalse(matcher.matches(servletRequest("GET", "/testx/this")).matched);
		assertFalse(matcher.matches(servletRequest("GET", "/test/a/b/")).matched);
	}

	@Test
	public void complexGetRequestWithMultipleWildcards() {
		// setup:
		matcher = new SimpleRouteMatcher(new Route("GET:/say/*/to/*"));

		// verify:
		assertTrue(matcher.matches(servletRequest("GET", "/say/hello/to/world")).matched);
		assertFalse(matcher.matches(servletRequest("GET", "/say/hello/to")).matched);

		// todo - should be passing out params into a splat like structure (as
		// per sinatra)
		// assertEquals("this", matcher.matches(servletRequest("GET",
		// "/test/this")).expandedParts.head().value);
		// assertFalse(matcher.matches(servletRequest("GET",
		// "/testx/this")).matched);
		// assertFalse(matcher.matches(servletRequest("GET",
		// "/test/a/b/")).matched);
	}

	@Test
	public void splatsArePopluatedForSimpleWildcards() {
		// setup:
		matcher = new SimpleRouteMatcher(new Route("GET:/documents/*"));

		// verify:
		ImmutableList<String> splats = matcher.matches(servletRequest("GET", "/documents/design")).matchedParams
				.splats();

		assertEquals(1, splats.size());
		assertEquals("design", splats.get(0));
	}

	@Test
	public void splatsArePopluatedForMultipleWildcards() {
		// setup:
		matcher = new SimpleRouteMatcher(new Route("GET:/say/*/to/*"));

		// verify:
		ImmutableList<String> splats = (ImmutableList<String>) matcher.matches(servletRequest("GET",
				"/say/hello/to/world")).matchedParams.get("splats");

		assertEquals(2, splats.size());
		assertEquals("hello", splats.get(0));
		assertEquals("world", splats.get(1));

	}

	@Test
	public void simpleGetRequestWithGlobbingWildcard() {
		// setup:
		matcher = new SimpleRouteMatcher(new Route("GET:/test/**"));

		// verify:
		assertTrue(matcher.matches(servletRequest("GET", "/test/this")).matched);
		assertEquals("this", matcher.matches(servletRequest("GET", "/test/this")).matchedParams.splats().head());
		assertTrue(matcher.matches(servletRequest("GET", "/test/a/b/c")).matched);
		
		ImmutableList<String> splats = matcher.matches(servletRequest("GET", "/test/a/b/c")).matchedParams.splats();
		//assertEquals("a/b/c", matcher.matches(servletRequest("GET", "/test/a/b/c")).expandedParts.expandToPath());
		assertEquals("a",splats.get(0));
		assertEquals("b",splats.get(1));
		assertEquals("c",splats.get(2));


		assertFalse(matcher.matches(servletRequest("GET", "/testx/this")).matched);
	}

	@Test
	public void shouldPassWithExampleUsedInDocumentation() {
		matcher = new SimpleRouteMatcher(new Route("GET:/demo/**"));
		assertTrue(matcher.matches(servletRequest("GET", "/demo/examples/helloworld")).matched);
		assertTrue(matcher.matches(servletRequest("GET", "/demo/routing")).matched);

	}

}
