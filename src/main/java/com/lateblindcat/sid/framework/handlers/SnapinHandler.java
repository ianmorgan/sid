package com.lateblindcat.sid.framework.handlers;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lateblindcat.sid.framework.Context;
import com.lateblindcat.sid.framework.ExpressionFactory;
import com.lateblindcat.sid.framework.Request;
import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;
import com.lateblindcat.sid.framework.templates.VelocityRenderer;
import com.lateblindcat.sid.snapins.Snapin;

public class SnapinHandler implements Handler {

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

				StringExpression rawContent = ExpressionFactory.string(new File(
						"src/main/resources/templates/console-layout.vtl"));
				String layout = new VelocityRenderer().render(context, rawContent).eval();

				// TODO - this is too simplistic - we should be looking at
				// the content type to construct a suitable container (e.g what
				// about an image)
				StringExpression snapinContent = ExpressionFactory.string(snapin.process(request)
						.getContent());
				layout = StringUtils.replace(layout, "content-goes-here", snapinContent.eval());

				return PageResponseFactory.html(ExpressionFactory.string(layout));

			}
		}

		return null;
	}

}
