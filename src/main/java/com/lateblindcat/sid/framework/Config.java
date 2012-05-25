package com.lateblindcat.sid.framework;

import java.util.Properties;

/**
 * Holds all config / profile information.
 * 
 * @author Ian Morgan
 */
public class Config {

	private int port = 8080;

	/**
	 * Construct from a properties file.
	 * 
	 */
	public Config(Properties properties) {
		this.port = Integer.parseInt(properties.getProperty("port"));
	}

	public Config() {
		// nothing - using development defaults
	}

	public int getPort() {
		return port;
	}

}
