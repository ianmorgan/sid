package com.lateblindcat.sid.rack;

import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpUtilsTest {

	@Test
	public void shouldParseSimpleQueryParams() {
		Map<String, String> results = HttpUtils.parseQueryParams("a=1");

		assertEquals(1, results.size());
		assertEquals("1", results.get("a"));
	}

	@Test
	public void shouldParseComplexQueryParams() {
		Map<String, String> results = HttpUtils.parseQueryParams("a=1&b=2&c=3");

		assertEquals(3, results.size());
		assertEquals("1", results.get("a"));
		assertEquals("2", results.get("b"));
		assertEquals("3", results.get("c"));
	}

}
