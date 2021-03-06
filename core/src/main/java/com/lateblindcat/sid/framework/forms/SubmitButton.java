package com.lateblindcat.sid.framework.forms;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.fp.StringExpression;
import com.lateblindcat.sid.rack.RackRequest;

public class SubmitButton implements FormComponent {
	private String name;

	public SubmitButton(String name) {
		this.name = name;
	}

	@Override
	public StringExpression render(RackRequest request) {
		StringBuilder sb = new StringBuilder();

		sb.append("<input type=\"submit\" value=\"").append(name).append("\"/>");

		return ExpressionFactory.string(sb);
	}

}
