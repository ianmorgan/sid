package com.lateblindcat.sid.framework.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.lateblindcat.sid.framework.Context;
import com.lateblindcat.sid.framework.Renderer;
import com.lateblindcat.sid.framework.Request;
import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.ExpressionFactory;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;
import com.lateblindcat.sid.framework.templates.LessRenderer;

public class CSSHandler implements Handler {

	private SimpleRouteMatcher routeMatcher = new SimpleRouteMatcher(new Route("GET:/resources/css/**"));
	private Renderer renderer = new LessRenderer();

	@Override
	public PageResponse process(Request request, RequestData requestData) {
		RouteMatchResult matchResult = routeMatcher.matches(request);

		if (matchResult.matched) {
			PageResponse response;
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(
						new File("src/main/resources/css/" + matchResult.expandedParts.expandToPath()));

				String rawCss = ExpressionFactory.string(fis).eval();
				response = PageResponseFactory.css(renderer.render(new Context(),
						ExpressionFactory.string(rawCss)));
			} catch (FileNotFoundException fnfex) {
				response = PageResponseFactory.notFound();
			} catch (Exception ex) {
				response = PageResponseFactory.internalError();
			}
			return response;
		} else {
			return null;
		}

	}
}
