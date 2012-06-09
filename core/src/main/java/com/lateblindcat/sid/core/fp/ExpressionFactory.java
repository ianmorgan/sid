package com.lateblindcat.sid.core.fp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.springframework.core.io.Resource;

import com.lateblindcat.sid.core.exception.NotFoundException;
import com.lateblindcat.sid.core.exception.ProcessingException;

/**
 * Static factory style methods to simplify building of expressions
 * 
 * @author Ian Morgan
 * 
 */
public class ExpressionFactory {

	public static StringExpression string(String data) {
		return new ObjectBasedStringExpression(data);
	}

	public static StringExpression string(StringBuilder sb) {
		return string(sb.toString());
	}

	public static StringExpression string(StringWriter data) {
		return new ObjectBasedStringExpression(data);
	}

	public static StringExpression string(InputStream is) {
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

	public static StringExpression string(File f) {
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

	public static StringExpression string(Resource resource) {
		if (resource.exists()) {
			try {
				return string(resource.getInputStream());
			} catch (IOException e) {
				throw new ProcessingException("Errpr reading Resource: " + resource.getDescription(), e);
			}
		} else {
			throw new ProcessingException("Resource: " + resource.getDescription() + " cannot be located");
		}
	}

	private static class ObjectBasedStringExpression implements StringExpression {

		private final Object object;

		public ObjectBasedStringExpression(Object object) {
			this.object = object;
		}

		@Override
		public String eval() {
			return object.toString();
		}

		@Override
		public String toString() {
			return object.toString();
		}

	}

}
