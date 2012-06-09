package com.lateblindcat.sid.snapins;

import com.lateblindcat.sid.core.fp.ExpressionFactory;
import com.lateblindcat.sid.core.framework.Route;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;
import com.lateblindcat.sid.rack.RackRequest;

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
	public PageResponse process(RackRequest request) {		
		return PageResponseFactory.text(ExpressionFactory.string("This is the demo snapin"));
	}
	
}
