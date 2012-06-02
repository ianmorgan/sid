package com.lateblindcat.sid.core.handlers;

import java.util.List;

import com.lateblindcat.sid.core.framework.Request;
import com.lateblindcat.sid.core.framework.RequestData;
import com.lateblindcat.sid.core.framework.Route;
import com.lateblindcat.sid.core.framework.RouteMatchResult;
import com.lateblindcat.sid.core.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.handlers.Handler;
import com.lateblindcat.sid.framework.pages.Page;
import com.lateblindcat.sid.framework.pages.PageResponse;

public class PageHandler implements Handler  {
	
	private List<Page> pages;

	public PageHandler(List<Page> pages) {
		this.pages = pages;
	}

	
	@Override
	public PageResponse process(Request request, RequestData requestData) {
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
