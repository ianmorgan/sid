package com.lateblindcat.sid.snapins;

import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;

public class DemoSnapin implements Snapin{

	@Override
	public String getName() {
		return "Demo Snapin";
	}

	@Override
	public Route getRoute(){
		return new Route("GET:/demo");
	}
	
	@Override
	public PageResponse process() {		
		return PageResponseFactory.text("This is the demo snapin");
	}
	
}
