package com.lateblindcat.sid.core.renderers;

import org.apache.velocity.exception.ParseErrorException;

import com.asual.lesscss.LessEngine;
import com.asual.lesscss.LessException;
import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Context;
import com.lateblindcat.sid.core.framework.Renderer;

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
