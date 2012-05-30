package com.lateblindcat.sid.framework.forms;

import com.lateblindcat.sid.framework.Request;
import com.lateblindcat.sid.framework.StringExpression;
import com.lateblindcat.sid.framework.ExpressionFactory;

public class SubmitButton implements FormComponent {
	private String name;

	public SubmitButton(String name) {
		this.name = name;
	}

	@Override
	public StringExpression render(Request request) {
		StringBuilder sb = new StringBuilder();

		sb.append("<input type=\"submit\" value=\"").append(name).append("\"/>");

		return ExpressionFactory.string(sb);
	}

}
