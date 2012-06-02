package com.lateblindcat.sid.framework.handlers;

import java.io.InputStream;

import com.lateblindcat.sid.framework.Context;
import com.lateblindcat.sid.framework.ExpressionFactory;
import com.lateblindcat.sid.framework.Renderer;
import com.lateblindcat.sid.framework.Request;
import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;
import com.lateblindcat.sid.framework.templates.LessRenderer;

public class CSSHandler extends AbstractResouceBasedHandler {

	private SimpleRouteMatcher routeMatcher = new SimpleRouteMatcher(new Route("GET:/resources/css/**"));
	private Renderer renderer = new LessRenderer();
	
	@Override
	protected PageResponse buildResponse(InputStream is) {
		StringExpression rawCss = ExpressionFactory.string(is);
		return PageResponseFactory.css(renderer.render(new Context(),rawCss));
	}

	@Override
	protected RouteMatchResult checkRoute(Request request, RequestData requestDate) {
		return routeMatcher.matches(request);
	}

	@Override
	protected String buildResourcePath(RouteMatchResult matchResult) {
		return "classpath:css/" + matchResult.expandedParts.expandToPath();
	}
}
