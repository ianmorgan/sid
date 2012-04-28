package com.lateblindcat.sid.framework.templates;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.lateblindcat.sid.framework.Context;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.StringExpressionFactory;

public class LessRendererTest {

	@Test
	public void shouldRenderLessTemplateAsCSS() throws Exception {

		StringExpression css = new LessRenderer().render(new Context(),
				StringExpressionFactory.fromString("div { width: 1 + 1 }"));

		String expected = "div {\n" + "  width: 2;\n" + "}\n";
		assertEquals(expected, css.evalute());
	}
}