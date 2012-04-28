package com.lateblindcat.sid.framework.handlers;

import java.io.File;
import java.util.Arrays;

import com.lateblindcat.sid.framework.Context;
import com.lateblindcat.sid.framework.Request;
import com.lateblindcat.sid.framework.Renderer;
import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.StringExpressionFactory;
import com.lateblindcat.sid.framework.TemplateEngine;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;

public class TemplateHandler implements Handler {

	private SimpleRouteMatcher routeMatcher = new SimpleRouteMatcher(new Route("GET:/templates/**"));

	private TemplateEngine templateEngine = new TemplateEngine();

	@Override
	public PageResponse process(Request request, RequestData requestData) {
		RouteMatchResult matchResult = routeMatcher.matches(request);

		if (matchResult.matched) {
			PageResponse response;
			try {
				String[] parts = matchResult.expandedParts.last().split("\\.");

				if (parts.length >= 2) {
					String[] templates = Arrays.copyOfRange(parts, 1, parts.length);

					StringExpression rawContent = StringExpressionFactory.fromFile(new File(
							"src/main/resources/templates/" + matchResult.expandedParts.expandToPath()));

					StringExpression rendered = templateEngine.render(new Context(request), rawContent, templates);

					response = PageResponseFactory.html(rendered);
				} else {
					response = PageResponseFactory.html("unrecognized template type");
				}
			} catch (RuntimeException re) {
				response = PageResponseFactory.notFound();
			}
			return response;
		} else {
			return null;
		}

	}

}
