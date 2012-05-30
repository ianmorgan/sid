package com.lateblindcat.sid.framework.forms;

import com.lateblindcat.sid.framework.Request;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.ExpressionFactory;

public class TextField implements FormComponent {
	private String name;
	private Label label;

	public TextField(String name) {
		this(new Label(name), name);
		;
	}

	public TextField(Label label, String name) {
		this.name = name;
		this.label = label;
	}

	@Override
	public StringExpression render(Request request) {
		StringBuilder sb = new StringBuilder();

		sb.append(label.toString());
		sb.append(": <input type=\"text\" name=\"").append(name).append("\"/><br />\n");

		return ExpressionFactory.string(sb);
	}

}
