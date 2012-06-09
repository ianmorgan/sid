package com.lateblindcat.sid.framework.forms;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.rack.RackRequest;

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
	public StringExpression render(RackRequest request) {
		StringBuilder sb = new StringBuilder();

		sb.append(label.toString());
		sb.append(": <input type=\"text\" name=\"").append(name).append("\"/><br />\n");

		return ExpressionFactory.string(sb);
	}

}
