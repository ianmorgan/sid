package com.lateblindcat.sid.framework;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;

/**
 * A generalised class for passing data into a template for evaluation
 * 
 * @author Ian Morgan
 * 
 */
public class Context {

	private Map<String, Object> data;

	public Context() {
		this.data = new HashMap<String, Object>();
	}
	
	public Context(HttpRequest request) {
		this.data = new HashMap<String, Object>();
		
		// TODO -really should make an immutable copy here 
		setBean("request",request);
	}


	public void setBean(String beanName, Object bean) {
		data.put(beanName, bean);
	}

	public VelocityContext toVelocity() {
		VelocityContext context = new VelocityContext();
		context.put("now", new Date());

		for (String beanName : data.keySet()) {
			context.put(beanName, data.get(beanName));
		}

		return context;
	}

}
