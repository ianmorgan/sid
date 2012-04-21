package com.lateblindcat.sid.framework.handlers;

import java.util.HashMap;
import java.util.Map;

import com.lateblindcat.sid.framework.HttpRequest;
import com.lateblindcat.sid.framework.Renderer;
import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.StringExpressionFactory;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;
import com.lateblindcat.sid.framework.templates.MarkdownRenderer;
import com.lateblindcat.sid.framework.templates.VelocityRenderer;

public class TemplateHandler implements Handler {

	private SimpleRouteMatcher routeMatcher = new SimpleRouteMatcher(new Route("GET:/templates/**"));

	static Map<String, Renderer> renderers;
	static {
		renderers = new HashMap<String, Renderer>();
		renderers.put("vtl", new VelocityRenderer());
		renderers.put("md", new MarkdownRenderer());

	}

	@Override
	public PageResponse process(HttpRequest request, RequestData requestData) {
		RouteMatchResult matchResult = routeMatcher.matches(request);

		if (matchResult.matched) {
			PageResponse response;
			try {
				String[] parts = matchResult.expandedParts.last().split("\\.");
				Renderer renderer = null;
				if (parts.length == 2) {
					String templateType = parts[1];
					renderer = renderers.get(templateType);
				}
				if (renderer != null) {
					StringExpression content = renderer.render(StringExpressionFactory
							.fromString("src/main/resources/templates/" + matchResult.expandedParts.expandToPath()));
					response = PageResponseFactory.html(content);
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
