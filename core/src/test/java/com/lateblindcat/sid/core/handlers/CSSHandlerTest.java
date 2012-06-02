package com.lateblindcat.sid.core.handlers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Request;
import com.lateblindcat.sid.core.framework.RequestData;
import com.lateblindcat.sid.core.handlers.CSSHandler;
import com.lateblindcat.sid.framework.ServletTestCase;
import com.lateblindcat.sid.framework.handlers.Handler;
import com.lateblindcat.sid.framework.pages.PageResponse;

public class CSSHandlerTest extends ServletTestCase {

	private Handler handler = new CSSHandler();

	@Test
	public void shouldRenderCSSTemplate() {
		Request request = this.servletRequest("GET", "/resources/css/layout.css");
		PageResponse response = handler.process(request, new RequestData());
		StringExpression s = ExpressionFactory.string(response.getContent());

		assertNotNull(s.eval());
	}
}
