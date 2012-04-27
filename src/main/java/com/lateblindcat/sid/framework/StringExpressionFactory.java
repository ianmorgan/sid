package com.lateblindcat.sid.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import com.lateblindcat.sid.framework.exception.NotFoundException;
import com.lateblindcat.sid.framework.exception.ProcessingException;

public class StringExpressionFactory {

	public static StringExpression fromString(String data) {
		return new ObjectBasedStringExpression(data);
	}

	public static StringExpression fromStringWriter(StringWriter data) {
		return new ObjectBasedStringExpression(data);
	}

	public static StringExpression fromInputStream(InputStream is) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String s;
			StringBuilder sb = new StringBuilder();
			while ((s = br.readLine()) != null) {
				sb.append(s);
				sb.append("\n");
			}
			return new ObjectBasedStringExpression(sb);

		} catch (FileNotFoundException e) {
			throw new NotFoundException(e.getMessage());
		} catch (IOException e) {
			throw new ProcessingException("Error reading stream " + e.getMessage());
		}
	}

	public static StringExpression fromFile(File f) {
		try {
			FileReader reader = new FileReader(f);
			BufferedReader br = new BufferedReader(reader);
			String s;
			StringBuilder sb = new StringBuilder();
			while ((s = br.readLine()) != null) {
				sb.append(s);
				sb.append("\n");
			}
			return new ObjectBasedStringExpression(sb);

		} catch (FileNotFoundException e) {
			throw new NotFoundException(f.getAbsolutePath());
		} catch (IOException e) {
			throw new ProcessingException("Error reading " + f.getAbsolutePath());
		}
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

		@Override
		public String toString() {
			return object.toString();
		}

	}
}
