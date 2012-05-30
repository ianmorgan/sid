package com.lateblindcat.sid.framework.handlers;

import java.io.File;
import java.util.Arrays;

import com.lateblindcat.sid.framework.Context;
import com.lateblindcat.sid.framework.Request;
import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.ExpressionFactory;
import com.lateblindcat.sid.framework.TemplateEngine;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;

/**
 * <p>
 * A handler to render any template in the template directory as HTML.
 * </p>
 * 
 * <p>
 * TODO: These templates are also shared with with other handlers. Maybe they
 * should be split into public and private ?
 * </p>
 * 
 * @author Ian Morgan
 * 
 */
public class TemplateHandler extends BaseHandler implements Handler {

	private SimpleRouteMatcher routeMatcher = new SimpleRouteMatcher(new Route("GET:/templates/**"));

	private TemplateEngine templateEngine = new TemplateEngine();

	@Override
	public PageResponse process(Request request, RequestData requestData) {
		RouteMatchResult matchResult = routeMatcher.matches(request);

		if (matchResult.matched) {
			PageResponse response;
			try {
				String[] templates = this.fileExtensions(matchResult.expandedParts.last().value);

				StringExpression rawContent = ExpressionFactory.string(new File("src/main/resources/templates/"
						+ matchResult.expandedParts.expandToPath()));

				StringExpression rendered = templateEngine.render(new Context(request), rawContent, templates);
				response = PageResponseFactory.html(rendered);

			} catch (RuntimeException re) {
				response = PageResponseFactory.notFound();
			}
			return response;
		} else {
			return null;
		}

	}

}
