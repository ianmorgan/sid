package com.lateblindcat.sid.core.handlers;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Context;
import com.lateblindcat.sid.core.framework.Request;
import com.lateblindcat.sid.core.framework.RequestData;
import com.lateblindcat.sid.core.framework.Route;
import com.lateblindcat.sid.core.framework.RouteMatchResult;
import com.lateblindcat.sid.core.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.exception.ProcessingException;
import com.lateblindcat.sid.framework.handlers.Handler;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;
import com.lateblindcat.sid.framework.templates.MarkdownRenderer;
import com.lateblindcat.sid.framework.templates.VelocityRenderer;

/**
 * <p>
 * This is a simple handler for implementing content style pages with content
 * added to a layout page by looking for a custom tag,
 * </p>
 * 
 * <p>
 * Its really an interim solution until a more fully featured layout manager is
 * integrated.
 * </p>
 * 
 * @author ianmorgan
 * 
 */
public class ContentPageHandler implements Handler {

	private SimpleRouteMatcher routeMatcher = new SimpleRouteMatcher(new Route("GET:/content/**"));
	private ResourceLoader loader = new DefaultResourceLoader();

	@Override
	public PageResponse process(Request request, RequestData requestData) {
		RouteMatchResult matchResult = routeMatcher.matches(request);

		if (matchResult.matched) {
			StringExpression rawContent = ExpressionFactory
					.string(loader.getResource("classpath:templates/layout.vtl"));
			String layout = new VelocityRenderer().render(new Context(), rawContent).eval();

			layout = mergeContentTemplate(matchResult, layout);

			return PageResponseFactory.html(ExpressionFactory.string(layout));
		} else {
			return null;
		}

	}

	private String mergeContentTemplate(RouteMatchResult matchResult, String layout) {
		StringExpression rawContent;
		if (matchResult.expandedParts.size() > 0) {
			String content = "";
			try {
				rawContent = ExpressionFactory.string(new File("src/main/resources/templates/"
						+ matchResult.expandedParts.expandToPath() + ".md"));

				content = new MarkdownRenderer().render(new Context(), rawContent).eval();
			} catch (ProcessingException ex) {
				content = "<strong>Failed to load content:</strong> " + ex.getMessage();
			}

			layout = StringUtils.replace(layout, "content-goes-here", content);
		}
		return layout;
	}

}
