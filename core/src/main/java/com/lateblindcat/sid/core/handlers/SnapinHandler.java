package com.lateblindcat.sid.core.handlers;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Context;
import com.lateblindcat.sid.core.framework.Request;
import com.lateblindcat.sid.core.framework.RequestData;
import com.lateblindcat.sid.core.framework.RouteMatchResult;
import com.lateblindcat.sid.core.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.handlers.Handler;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;
import com.lateblindcat.sid.framework.templates.VelocityRenderer;
import com.lateblindcat.sid.snapins.Snapin;

public class SnapinHandler implements Handler {

	private ResourceLoader loader = new DefaultResourceLoader();
	private List<Snapin> snapins;

	public SnapinHandler(List<Snapin> snapins) {
		this.snapins = snapins;
	}

	@Override
	public PageResponse process(Request request, RequestData requestData) {
		for (Snapin snapin : snapins) {
			RouteMatchResult matchResult = new SimpleRouteMatcher(snapin.getRoute()).matches(request);
			if (matchResult.matched) {

				Context context = new Context(request);
				context.setBean("snapins", snapins);

				StringExpression rawContent = loadConsoleTemplate();
				String layout = new VelocityRenderer().render(context, rawContent).eval();

				// TODO - this is too simplistic - we should be looking at
				// the content type to construct a suitable container (e.g what
				// about an image)
				StringExpression snapinContent = ExpressionFactory.string(snapin.process(request).getContent());
				layout = StringUtils.replace(layout, "content-goes-here", snapinContent.eval());

				return PageResponseFactory.html(ExpressionFactory.string(layout));

			}
		}

		return null;
	}

	private StringExpression loadConsoleTemplate() {
		Resource resource = loader.getResource("classpath:templates/console-layout.vtl");
		return ExpressionFactory.string(resource);
	}

}
