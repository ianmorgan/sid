package com.lateblindcat.sid.core.framework;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class ConfigManager {

	private static final String BOOTSTRAP = "bootstrap.properties";

	public void loadModuleConfigs() throws Exception {
		// see
		// http://bill.burkecentral.com/2008/01/14/scanning-java-annotations-at-runtime/

		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Enumeration<URL> urls = cl.getResources("META-INF/" + BOOTSTRAP);

		List<URL> urlsToLoad = new ArrayList<URL>();
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			String path = url.toString();
			path = path.substring(0, path.length() - BOOTSTRAP.length());
			urlsToLoad.add(new URL(path));

		}
		URLClassLoader loader = new URLClassLoader(urlsToLoad.toArray(new URL[0]));

		BufferedInputStream bis = new BufferedInputStream(loader.getResourceAsStream(BOOTSTRAP));

		byte data[] = new byte[1000];
		bis.read(data);

		//String result = new String(data);


		Properties prop = new Properties();
		prop.load(loader.getResourceAsStream(BOOTSTRAP));

		Class<?> clazz = this.getClass().getClassLoader().loadClass(prop.getProperty("configClass"));
		ModuleConfig o = (ModuleConfig) clazz.newInstance();

	}

}
