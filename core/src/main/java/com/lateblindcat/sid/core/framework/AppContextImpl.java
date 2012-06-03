package com.lateblindcat.sid.core.framework;

import org.apache.log4j.Logger;

public class AppContextImpl implements AppContext {

	@Override
	public Logger logger(Class<?> clazz) {
		return Logger.getLogger(clazz);

	}

	@Override
	public ClassLoader classLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

}
