package com.lateblindcat.sid.core.handlers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.ServletTestCase;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.rack.RackRequest;

public class CSSHandlerTest extends ServletTestCase {

	private Handler handler = new CSSHandler();

	@Test
	public void shouldRenderCSSTemplate() {
		RackRequest request = this.servletRequest("GET", "/resources/css/layout.css");
		PageResponse response = handler.process(request);
		StringExpression s = ExpressionFactory.string(response.getContent());

		assertNotNull(s.eval());
	}
}
