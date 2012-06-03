package com.lateblindcat.sid.core.framework;

import java.util.HashMap;
import java.util.Map;

import com.lateblindcat.sid.core.exception.ProcessingException;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.renderers.LessRenderer;
import com.lateblindcat.sid.core.renderers.MarkdownRenderer;
import com.lateblindcat.sid.core.renderers.Renderer;
import com.lateblindcat.sid.core.renderers.VelocityRenderer;

/**
 * <p>
 * Provides access to all registered template engines such as velocity, markdown
 * etc.
 * </p>
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

	/**
	 * <p>
	 * Renders the provided content using the template engines registered
	 * against the extensions. Throws a {@link ProcessingException} (or
	 * subclass) if there is a problem.
	 * </p>
	 * 
	 * <p>
	 * If there are multiple extensions then content is simply passed through
	 * each templating engine in turn
	 * </p>
	 * 
	 * 
	 * @param context
	 * @param content
	 * @param extensions
	 * @return
	 */
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
