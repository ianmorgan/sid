package com.lateblindcat.sid.framework.templates;

import org.apache.velocity.exception.ParseErrorException;

import com.asual.lesscss.LessEngine;
import com.asual.lesscss.LessException;
import com.lateblindcat.sid.framework.Context;
import com.lateblindcat.sid.framework.Renderer;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.ExpressionFactory;

public class LessRenderer implements Renderer {

	private LessEngine engine = new LessEngine();

	@Override
	public StringExpression render(Context context, StringExpression template) {
		String css;
		try {
			css = engine.compile(template.eval());
		} catch (LessException e) {
			throw new ParseErrorException(e.getMessage());
		}

		return ExpressionFactory.string(css);
	}

}
