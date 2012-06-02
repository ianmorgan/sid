package com.lateblindcat.sid.core.handlers;

import java.io.IOException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Context;
import com.lateblindcat.sid.core.framework.Request;
import com.lateblindcat.sid.core.framework.RequestData;
import com.lateblindcat.sid.core.framework.Route;
import com.lateblindcat.sid.core.framework.RouteMatchResult;
import com.lateblindcat.sid.core.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.core.framework.TemplateEngine;
import com.lateblindcat.sid.framework.handlers.Handler;
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
	private ResourceLoader loader = new DefaultResourceLoader();

	@Override
	public PageResponse process(Request request, RequestData requestData) {
		RouteMatchResult matchResult = routeMatcher.matches(request);

		if (matchResult.matched) {
			// try {
			String[] templates = this.fileExtensions(matchResult.expandedParts.last().value);

			Resource template = loader.getResource("classpath:templates/" + matchResult.expandedParts.expandToPath());
			if (template.exists()) {
				try {
					StringExpression rawContent = ExpressionFactory.string(template.getInputStream());
					StringExpression rendered = templateEngine.render(new Context(request), rawContent, templates);
					return PageResponseFactory.html(rendered);
				} catch (IOException e) {
					return PageResponseFactory.internalError();
				}
			} else {
				return PageResponseFactory.notFound();
			}

			// } catch (RuntimeException re) {
			// // TOD
			// response = PageResponseFactory.notFound();
			// }
		} else {
			return null;
		}

	}

}
