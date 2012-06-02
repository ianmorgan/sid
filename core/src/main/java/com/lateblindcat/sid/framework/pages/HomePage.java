package com.lateblindcat.sid.framework.pages;

import java.io.File;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Context;
import com.lateblindcat.sid.core.renderers.VelocityRenderer;

public class HomePage implements Page {

	@Override
	public String getRoute() {
		return "GET:/";
	}

	@Override
	public PageResponse process() {
		StringExpression rawContent = ExpressionFactory
				.string(new File("src/main/resources/templates/home.vtl"));
		StringExpression content = new VelocityRenderer().render(new Context(),rawContent);
		return PageResponseFactory.html(content);
	}

}
