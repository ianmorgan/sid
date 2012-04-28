package com.lateblindcat.sid.framework;

import java.util.HashMap;
import java.util.Map;

import com.lateblindcat.sid.framework.exception.ProcessingException;
import com.lateblindcat.sid.framework.templates.LessRenderer;
import com.lateblindcat.sid.framework.templates.MarkdownRenderer;
import com.lateblindcat.sid.framework.templates.VelocityRenderer;

/**
 * 
 * 
 * @author Ian Morgan
 * 
 */
public class TemplateEngine {

	private static final Map<String, Renderer> RENDERERS;

	static {
		RENDERERS = new HashMap<String, Renderer>();
		RENDERERS.put("vtl", new VelocityRenderer());
		RENDERERS.put("md", new MarkdownRenderer());
		RENDERERS.put("less", new LessRenderer());
	}

	public StringExpression render(Context context, StringExpression content, String[] extensions) {
		StringExpression rendered = content;
		for (String ext : extensions) {
			Renderer renderer = RENDERERS.get(ext);
			if (renderer != null) {
				rendered = renderer.render(context, rendered);
			} else {
				throw new ProcessingException("Unknown template type: " + ext);
			}
		}
		return rendered;
	}

}
