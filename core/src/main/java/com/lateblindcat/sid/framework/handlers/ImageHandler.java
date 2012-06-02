package com.lateblindcat.sid.framework.handlers;

import java.io.InputStream;

import com.lateblindcat.sid.framework.Request;
import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;

public class ImageHandler extends AbstractResouceBasedHandler {
	private SimpleRouteMatcher routeMatcher = new SimpleRouteMatcher(new Route("GET:/images/*"));

	@Override
	protected PageResponse buildResponse(InputStream is) {
		return PageResponseFactory.jpeg(is);
	}

	@Override
	protected RouteMatchResult checkRoute(Request request, RequestData requestDate) {
		return routeMatcher.matches(request);
	}

	@Override
	protected String buildResourcePath(RouteMatchResult matchResult) {
		return "classpath:images/" + matchResult.expandedParts.expandToPath();
	}

}
