package com.lateblindcat.sid.framework;

import org.junit.Test;

import static org.junit.Assert.*;

public class TemplateEngineTest {

	private TemplateEngine engine = new TemplateEngine();
	private Context context;

	public TemplateEngineTest() {
		context = new Context();
		context.setBean("name", "John Smith");
	}

	@Test
	public void shouldRenderUsingSuppliedTemplates() {
		StringExpression template = StringExpressionFactory.fromString("## Hello, $name");
		StringExpression rendered = engine.render(context, template, new String[] { "md","vtl" });

		assertEquals ("<h2>Hello, John Smith</h2>\n", rendered.evalute());
	}

}
