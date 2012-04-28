package com.lateblindcat.sid.framework.templates;

import com.lateblindcat.sid.framework.Context;
import com.lateblindcat.sid.framework.Renderer;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.StringExpressionFactory;
import com.petebevin.markdown.MarkdownProcessor;

public class MarkdownRenderer implements Renderer {

	@Override
	public StringExpression render(Context context, StringExpression template) {
		MarkdownProcessor processor = new MarkdownProcessor();
		return StringExpressionFactory.fromString(processor.markdown(template.eval()));
	}

}
