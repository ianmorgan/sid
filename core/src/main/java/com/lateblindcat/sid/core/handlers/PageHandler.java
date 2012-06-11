package com.lateblindcat.sid.core.handlers;

import java.util.List;

import com.lateblindcat.sid.core.framework.Route;
import com.lateblindcat.sid.core.framework.RouteMatchResult;
import com.lateblindcat.sid.core.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.pages.Page;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.rack.RackRequest;

public class PageHandler implements Handler  {
	
	private List<Page> pages;

	public PageHandler(List<Page> pages) {
		this.pages = pages;
	}

	
	@Override
	public PageResponse process(RackRequest request) {
		for (Page page : pages) {
			RouteMatchResult matchResult = new SimpleRouteMatcher(new Route(
					page.getRoute())).matches(request);
			if (matchResult.matched) {
				return page.process();
			}
		}

		return null;
	}

	

}
