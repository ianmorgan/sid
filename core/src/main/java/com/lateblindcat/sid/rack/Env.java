package com.lateblindcat.sid.rack;

import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * A Java equivalent to Ruby Rack (http://rack.rubyforge.org/doc/SPEC.html) Env
 * hash which is used to communicate between Rack process
 * </P>
 * 
 * <p>
 * To avoid confusion Ruby naming conventions are used in preference to Java.
 * Refer the RACK documentation for details.
 * </p>
 * 
 * @author Ian Morgan
 * 
 */
public class Env {
	public String REQUEST_METHOD;

	public String SCRIPT_NAME;

	public String PATH_INFO;

	public String QUERY_STRING;

	public String SERVER_NAME;

	public String SERVER_PORT;

	// common HTTP variables
	public String HTTP_VERSION;
	public String HTTP_HOST;
	public String HTTP_USER_AGENT;
	public String HTTP_ACCEPT;
	public String HTTP_ACCEPT_LANGUAGE;
	public String HTTP_ACCEPT_ENCODING;
	public String HTTP_DNT;
	public String HTTP_CONNECTION;
	public String HTTP_COOKIE;

	// any HTTP variable
	public Map<String, String> HTTP_VARIABLES;

	public InputStream rack_input;
	public InputStream rack_errors;

	public Env() {
	}

	public Env(HttpServletRequest httpServletRequest) {
		REQUEST_METHOD = httpServletRequest.getMethod();
		PATH_INFO = httpServletRequest.getPathInfo();

		// TODO - this is quite inefficient - should be able
		// to pull this straight out the original request string.
		if (httpServletRequest.getParameterMap().size() > 0) {
			StringBuilder params = new StringBuilder();
			for (Entry<String, String[]> entry : httpServletRequest.getParameterMap().entrySet()) {
				if (params.length() > 0) {
					params.append("&");
				}
				params.append(entry.getKey()).append("=").append(entry.getValue()[0]);
			}
			QUERY_STRING = params.toString();
		}
	}

	// TODO what about other HTTP variables

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("com.lateblindcat.sid.rack.Rack holds the following:\n");
		if (StringUtils.isNotEmpty(REQUEST_METHOD)) {
			sb.append("REQUEST_METHOD = " + REQUEST_METHOD + "\n");
		}
		if (StringUtils.isNotEmpty(PATH_INFO)) {
			sb.append("PATH_INFO = " + PATH_INFO + "\n");
		}
		if (StringUtils.isNotEmpty(QUERY_STRING)) {
			sb.append("QUERY_STRING = " + QUERY_STRING + "\n");
		}
		return sb.toString();
	}

}
