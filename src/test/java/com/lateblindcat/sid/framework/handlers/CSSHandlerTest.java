package com.lateblindcat.sid.framework.handlers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lateblindcat.sid.framework.Request;
import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.ServletTestCase;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.StringExpressionFactory;
import com.lateblindcat.sid.framework.pages.PageResponse;

public class CSSHandlerTest extends ServletTestCase {

	private Handler handler = new CSSHandler();

	@Test
	public void shouldRenderCSSTemplate() {
		Request request = this.servletRequest("GET", "/resources/css/layout.css");
		PageResponse response = handler.process(request, new RequestData());
		StringExpression s = StringExpressionFactory.fromInputStream(response.getContent());

		assertNotNull(s.eval());
	}
}
