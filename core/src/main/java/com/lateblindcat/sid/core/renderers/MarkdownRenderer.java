package com.lateblindcat.sid.core.renderers;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Context;
import com.petebevin.markdown.MarkdownProcessor;

public class MarkdownRenderer implements Renderer {

	@Override
	public StringExpression render(Context context, StringExpression template) {
		MarkdownProcessor processor = new MarkdownProcessor();
		return ExpressionFactory.string(processor.markdown(template.eval()));
	}

}
