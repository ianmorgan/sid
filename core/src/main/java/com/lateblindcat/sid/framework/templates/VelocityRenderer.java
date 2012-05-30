package com.lateblindcat.sid.framework.templates;

import java.io.StringWriter;
import java.util.Date;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.lateblindcat.sid.framework.Context;
import com.lateblindcat.sid.framework.Renderer;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.ExpressionFactory;
import com.lateblindcat.sid.framework.exception.NotFoundException;
import com.lateblindcat.sid.framework.exception.ParserException;
import com.lateblindcat.sid.framework.exception.ProcessingException;

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

	// @Override
	// public StringExpression render(StringExpression templateName) {
	//
	// VelocityContext context = new VelocityContext();
	// context.put("name", new String("Velocity"));
	// context.put("now", new Date());
	// Template template = null;
	//
	// String name = null;
	// try {
	// name = templateName.evalute();
	// template = Velocity.getTemplate(name);
	// StringWriter sw = new StringWriter();
	// template.merge(context, sw);
	// return StringExpressionFactory.fromStringWriter(sw);
	// } catch (ResourceNotFoundException rnfe) {
	// throw new NotFoundException("Velocity", name);
	// } catch (ParseErrorException pee) {
	// throw new ParserException(pee.getMessage());
	// } catch (MethodInvocationException mie) {
	// throw new ProcessingException(mie.getMessage());
	// } catch (Exception e) {
	// throw new ProcessingException(e.getMessage());
	// }
	// }

}
