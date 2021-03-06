package com.lateblindcat.sid.framework.forms;

import java.util.ArrayList;
import java.util.List;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.rack.RackRequest;

public class FormBuilder {

	private List<FormComponent> components = new ArrayList<FormComponent>();
	private AttrList attrs = new AttrList();

	public FormBuilder(String name, String method, String action) {
		this.attrs.put("name", name);
		this.attrs.put("method", method);
		this.attrs.put("action", action);
	}

	public FormBuilder with(FormComponent component) {
		components.add(component);
		return this;
	}

	public StringExpression render(RackRequest request) {
		StringBuilder sb = new StringBuilder();

		sb.append("<FORM ");
		sb.append(attrs.render()).append(">\n");
		for (FormComponent component : components) {
			sb.append(component.render(request).eval());
		}
		sb.append("</FORM>");

		return ExpressionFactory.string(sb);
	}
}
