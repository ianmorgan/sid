package com.lateblindcat.sid.rack;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RackRequestTest {

	@Test
	public void shouldBuildRequestForSimpleGet() throws Exception {
		Env env = new Env();

		env.REQUEST_METHOD = "GET";
		env.PATH_INFO = "/test";
		env.QUERY_STRING = "id=99";

		RackRequest result = new RackRequest(env);
		assertEquals("GET", result.requestMethod());
		assertEquals("/test", result.pathInfo());
		assertEquals(jsonMap("{\"id\":\"99\"}"), result.params());
	}

	protected Map<String, String> jsonMap(String json) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		Map<String, String> result = mapper.readValue(json, new TypeReference<Map<String, String>>() {
		});

		System.out.println(result);
		return result;

	}
}
