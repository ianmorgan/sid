package com.lateblindcat.sid.snapins;

import com.lateblindcat.sid.framework.HttpRequest;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;

public class DemoSnapin implements Snapin{

	@Override
	public String getName() {
		return "Demo Snapin";
	}

	@Override
	public String getRoute(){
		return "GET:/demo/:params";
	}
	
	@Override
	public RouteMatchResult matchesRoute (HttpRequest route){
		return new SimpleRouteMatcher("/demo").natches(route);
	}

	@Override
	public PageResponse process() {		
		return PageResponseFactory.text("This is the demo snapin");
	}
	
}
