package com.lateblindcat.sid.framework;

import java.io.InputStream;
import java.io.StringWriter;

public class StringExpressionFactory {

	public static StringExpression fromString(String data) {
		return new ObjectBasedStringExpression(data);
	}
	
	public static StringExpression fromStringWriter(StringWriter data) {
		return new ObjectBasedStringExpression(data);
	}

	public static StringExpression fromInputStream(InputStream is) {
		return new ObjectBasedStringExpression("TODO");
	}

	private static class ObjectBasedStringExpression implements StringExpression {

		private final Object object;

		public ObjectBasedStringExpression(Object object) {
			this.object = object;
		}

		@Override
		public String evalute() {
			return object.toString();
		}

	}
}
