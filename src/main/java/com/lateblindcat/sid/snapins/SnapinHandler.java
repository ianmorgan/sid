package com.lateblindcat.sid.snapins;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

import com.lateblindcat.sid.framework.HttpRequest;
import com.lateblindcat.sid.framework.HttpServletResponseBuilder;
import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.SimpleRouteMatcher;

public class SnapinHandler {

	private List<Snapin> snapins;

	public SnapinHandler(List<Snapin> snapins) {
		this.snapins = snapins;
	}

	public boolean handle(Request baseRequest,
			HttpServletRequest servletRequest, HttpServletResponse response)
			throws IOException, ServletException {

		HttpRequest request = new HttpRequest(servletRequest);
		for (Snapin snapin : snapins) {
			RouteMatchResult matchResult = new SimpleRouteMatcher(new Route(
					snapin.getRoute())).matches(request);
			if (matchResult.matched) {
				HttpServletResponseBuilder.fromPageResponse(response, snapin.process());
				baseRequest.setHandled(true);
				return true;
			}
		}

		return false;
	}

}
