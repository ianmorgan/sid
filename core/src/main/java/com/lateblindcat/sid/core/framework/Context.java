package com.lateblindcat.sid.core.framework;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;

/**
 * A generalized class for passing data into a template for evaluation
 * 
 * @author Ian Morgan
 * 
 */
public class Context {

	private Map<String, Object> data;

	public Context() {
		this.data = new HashMap<String, Object>();
		setBean("now", new Date());
	}

	public Context(Request request) {
		this();
		setBean("request", request);
	}

	public void setBean(String beanName, Object bean) {
		data.put(beanName, bean);
	}

	public VelocityContext toVelocity() {
		VelocityContext context = new VelocityContext();

		for (String beanName : data.keySet()) {
			context.put(beanName, data.get(beanName));
		}

		return context;
	}

}
