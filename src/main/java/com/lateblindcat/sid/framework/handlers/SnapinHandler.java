package com.lateblindcat.sid.framework.handlers;

import java.util.List;

import com.lateblindcat.sid.framework.HttpRequest;
import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.snapins.Snapin;

public class SnapinHandler implements Handler {

	private List<Snapin> snapins;

	public SnapinHandler(List<Snapin> snapins) {
		this.snapins = snapins;
	}


	@Override
	public PageResponse process(HttpRequest request, RequestData requestData) {
		for (Snapin snapin : snapins) {
			RouteMatchResult matchResult = new SimpleRouteMatcher(new Route(
					snapin.getRoute())).matches(request);
			if (matchResult.matched) {
				return snapin.process();
			}
		}

		return null;
	}

}
