package com.lateblindcat.sid.core.handlers;

import java.io.InputStream;

import com.lateblindcat.sid.core.framework.Route;
import com.lateblindcat.sid.core.framework.RouteMatchResult;
import com.lateblindcat.sid.core.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;
import com.lateblindcat.sid.rack.RackRequest;

public class ImageHandler extends AbstractResouceBasedHandler {
	private SimpleRouteMatcher routeMatcher = new SimpleRouteMatcher(new Route("GET:/images/*"));

	@Override
	protected PageResponse buildResponse(InputStream is) {
		return PageResponseFactory.jpeg(is);
	}

	@Override
	protected RouteMatchResult checkRoute(RackRequest request) {
		return routeMatcher.matches(request);
	}

	@Override
	protected String buildResourcePath(RouteMatchResult matchResult) {
		return "classpath:images/" + matchResult.expandedParts.expandToPath();
	}

}
