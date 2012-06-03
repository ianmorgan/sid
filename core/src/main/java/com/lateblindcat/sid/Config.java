package com.lateblindcat.sid;

import com.lateblindcat.sid.core.framework.ModuleConfig;

public class Config implements ModuleConfig {

	@Override
	public String name() {
		return "Sid Core";
	}

	@Override
	public String prefix() {
		return "core";
	}

}
