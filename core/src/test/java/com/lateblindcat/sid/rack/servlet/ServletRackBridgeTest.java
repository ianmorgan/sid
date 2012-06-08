package com.lateblindcat.sid.rack.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.rack.Env;
import com.lateblindcat.sid.rack.Rack.Response;

public class ServletRackBridgeTest {

	private ServletRackBridge bridge = new ServletRackBridge();

	@Test
	public void shouldBuildEnvForSimpleGet() {
		Env env = bridge.buildRackEnv(servletRequest("GET", "/test"));

		assertEquals("GET", env.REQUEST_METHOD);
		assertEquals("/test", env.PATH_INFO);
		assertNull(env.QUERY_STRING);
	}

	@Test
	public void shouldBuildEnvForRequestWithQueryParams() {
		Env env = bridge.buildRackEnv(servletRequest("GET", "/test", "foo=bar"));

		assertEquals("GET", env.REQUEST_METHOD);
		assertEquals("/test", env.PATH_INFO);
		assertEquals("foo=bar", env.QUERY_STRING);
	}

	@Test
	public void shouldBuildSimpleTextResponse() throws Exception {
		Response rackResponse = new Response();
		rackResponse.status = 200;
		rackResponse.headers = new HashMap<String, StringExpression>();
		rackResponse.headers.put("Content-Type", ExpressionFactory.string("text/plain"));
		List<StringExpression> body = new ArrayList<StringExpression>();
		body.add(ExpressionFactory.string("hello world"));
		rackResponse.body = body.iterator();

		MockHttpServletResponse servletResponse = new MockHttpServletResponse();
		bridge.populateServletResponse(rackResponse, servletResponse);

		assertEquals("hello world", servletResponse.getContentAsString());
		assertEquals(1, servletResponse.getHeaderNames().size());
		assertEquals("text/plain", servletResponse.getHeader("Content-Type"));
		assertEquals(200, servletResponse.getStatus());
		

	}

	protected HttpServletRequest servletRequest(String method, String path) {
		return servletRequest(method, path, null);
	}

	protected HttpServletRequest servletRequest(String method, String path, String queryString) {
		MockHttpServletRequest servletRequest = new MockHttpServletRequest();
		servletRequest.setPathInfo(path);
		servletRequest.setMethod(method);
		servletRequest.setQueryString(queryString);

		return servletRequest;
	}

}
