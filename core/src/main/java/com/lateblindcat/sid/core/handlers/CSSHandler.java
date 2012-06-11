package com.lateblindcat.sid.core.handlers;

import java.io.InputStream;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Context;
import com.lateblindcat.sid.core.framework.Route;
import com.lateblindcat.sid.core.framework.RouteMatchResult;
import com.lateblindcat.sid.core.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.core.renderers.LessRenderer;
import com.lateblindcat.sid.core.renderers.Renderer;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;
import com.lateblindcat.sid.rack.RackRequest;

public class CSSHandler extends AbstractResouceBasedHandler {

	private SimpleRouteMatcher routeMatcher = new SimpleRouteMatcher(new Route("GET:/resources/css/**"));
	private Renderer renderer = new LessRenderer();
	
	@Override
	protected PageResponse buildResponse(InputStream is) {
		StringExpression rawCss = ExpressionFactory.string(is);
		return PageResponseFactory.css(renderer.render(new Context(),rawCss));
	}

	@Override
	protected RouteMatchResult checkRoute(RackRequest request) {
		return routeMatcher.matches(request);
	}

	@Override
	protected String buildResourcePath(RouteMatchResult matchResult) {
		return "classpath:css/" + matchResult.expandedParts.expandToPath();
	}
}
