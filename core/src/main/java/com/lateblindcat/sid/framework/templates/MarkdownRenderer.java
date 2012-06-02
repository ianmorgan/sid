package com.lateblindcat.sid.framework.templates;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Context;
import com.lateblindcat.sid.core.framework.Renderer;
import com.petebevin.markdown.MarkdownProcessor;

public class MarkdownRenderer implements Renderer {

	@Override
	public StringExpression render(Context context, StringExpression template) {
		MarkdownProcessor processor = new MarkdownProcessor();
		return ExpressionFactory.string(processor.markdown(template.eval()));
	}

}
