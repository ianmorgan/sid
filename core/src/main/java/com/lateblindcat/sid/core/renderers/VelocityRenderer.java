package com.lateblindcat.sid.core.renderers;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.lateblindcat.sid.core.exception.NotFoundException;
import com.lateblindcat.sid.core.exception.ParserException;
import com.lateblindcat.sid.core.exception.ProcessingException;
import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.core.framework.Context;

/**
 * Velocity template integration
 * 
 * @author Ian Morgan
 * 
 */
public class VelocityRenderer implements Renderer {

	public VelocityRenderer() {
		org.apache.velocity.app.Velocity.init();
	}

	@Override
	public StringExpression render(Context ctx, StringExpression template) {

		VelocityContext context = ctx.toVelocity();
	
		String name = null;
		try {
			// TODO - should be caching template - see
			// http://stackoverflow.com/questions/1432468/how-to-use-string-as-velocity-template

			String templateText = template.eval();
			StringWriter sw = new StringWriter();
			Velocity.evaluate(context, sw, "dont-care", templateText);
			return ExpressionFactory.string(sw);
		} catch (ResourceNotFoundException rnfe) {
			throw new NotFoundException("Velocity", name);
		} catch (ParseErrorException pee) {
			throw new ParserException(pee.getMessage());
		} catch (MethodInvocationException mie) {
			throw new ProcessingException(mie.getMessage());
		} catch (Exception e) {
			throw new ProcessingException(e.getMessage());
		}
	}

}
