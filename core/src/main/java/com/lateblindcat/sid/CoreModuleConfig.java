package com.lateblindcat.sid;

import java.util.ArrayList;
import java.util.List;

import com.lateblindcat.sid.core.framework.ModuleConfig;
import com.lateblindcat.sid.snapins.Snapin;

public class CoreModuleConfig implements ModuleConfig {

	@Override
	public String name() {
		return "Sid Core";
	}

	@Override
	public String prefix() {
		return "core";
	}
	
	@Override
	public List<Snapin> snapins() {

		List<Snapin> snapins = new ArrayList<Snapin>();
		//snapins.add(new DemoSnapin());
		return snapins;
	}

}
