package com.lateblindcat.sid.framework.templates;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.framework.Context;

public class VelocityRendererTest {

	private Context context;
	private VelocityRenderer renderer = new VelocityRenderer();

	@Before
	public void setUp() {
		context = new Context();

	}

	@Test
	public void shouldRenderTemplate() {

		context.setBean("name", "John Smith");
		assertEquals("Hello, John Smith", renderer.render(context, ExpressionFactory.string("Hello, $name"))
				.eval());

	}

}
