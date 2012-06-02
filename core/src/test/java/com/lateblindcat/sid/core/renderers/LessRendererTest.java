package com.lateblindcat.sid.core.renderers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Context;
import com.lateblindcat.sid.core.renderers.LessRenderer;

public class LessRendererTest {

	@Test
	public void shouldRenderLessTemplateAsCSS() throws Exception {

		StringExpression css = new LessRenderer().render(new Context(),
				ExpressionFactory.string("div { width: 1 + 1 }"));

		String expected = "div {\n" + "  width: 2;\n" + "}\n";
		assertEquals(expected, css.eval());
	}
}
