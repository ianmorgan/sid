package com.lateblindcat.sid.framework.forms;

import com.lateblindcat.sid.framework.Request;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.StringExpressionFactory;

public class TextField implements FormComponent {
	private String name;

	public TextField(String name) {
		this.name = name;
	}

	@Override
	public StringExpression render(Request request) {
		StringBuilder sb = new StringBuilder();

		sb.append(name);
		sb.append(": <input type=\"text\" name=\"").append(name).append("\"/><br />\n");

		return StringExpressionFactory.fromString(sb);
	}

}
