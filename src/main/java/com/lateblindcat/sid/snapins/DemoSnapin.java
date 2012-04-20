package com.lateblindcat.sid.snapins;

import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;

public class DemoSnapin implements Snapin{

	@Override
	public String getName() {
		return "Demo Snapin";
	}

	@Override
	public String getRoute(){
		return "GET:/demo";
	}
	
	@Override
	public PageResponse process() {		
		return PageResponseFactory.text("This is the demo snapin");
	}
	
}
