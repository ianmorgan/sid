package com.lateblindcat.sid.core.framework;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import com.lateblindcat.sid.core.exception.FatalException;

/**
 * <p>
 * This class searches for {@link ModuleConfig} classes by first loading the
 * META-INF/bootstrap.properties file and then loading the config specified
 * </p>
 * 
 * @author Ian Morgan
 * 
 */
public class ModuleConfigLoader {

	private AppContext ctx;

	public ModuleConfigLoader(AppContext appContext) {
		this.ctx = appContext;
	}

	public List<ModuleConfig> loadConfigs() {
		// See
		// http://bill.burkecentral.com/2008/01/14/scanning-java-annotations-at-runtime/
		// for some notes on techniques for dynamically loading config files.

		List<ModuleConfig> results = new ArrayList<ModuleConfig>();
		try {
			Enumeration<URL> urls = ctx.classLoader().getResources("META-INF/bootstrap.properties");

			while (urls.hasMoreElements()) {
				ModuleConfig config = loadModuleConfig(urls.nextElement());
				if (config != null) {
					results.add(config);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new FatalException("Fatal error laoding module config files ", ex);
		}

		return results;

	}

	private ModuleConfig loadModuleConfig(URL url) throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		URL[] urlsToLoad = new URL[1];
		String path = url.toString().substring(0,url.toString().length()-20);
		urlsToLoad[0] = new URL(path);

		URLClassLoader loader = new URLClassLoader(urlsToLoad);

		Properties prop = new Properties();
		prop.load(loader.getResourceAsStream("bootstrap.properties"));

		Class clazz = this.getClass().getClassLoader().loadClass(prop.getProperty("configClass"));

		ModuleConfig o = (ModuleConfig) clazz.newInstance();
		return o;
	}

}
