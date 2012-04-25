package com.lateblindcat.sid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.lateblindcat.sid.framework.HttpRequest;
import com.lateblindcat.sid.framework.HttpServletResponseBuilder;
import com.lateblindcat.sid.framework.handlers.CSSHandler;
import com.lateblindcat.sid.framework.handlers.ContentPageHandler;
import com.lateblindcat.sid.framework.handlers.Handler;
import com.lateblindcat.sid.framework.handlers.ImageHandler;
import com.lateblindcat.sid.framework.handlers.PageHandler;
import com.lateblindcat.sid.framework.handlers.SnapinHandler;
import com.lateblindcat.sid.framework.handlers.TemplateHandler;
import com.lateblindcat.sid.framework.pages.HomePage;
import com.lateblindcat.sid.framework.pages.Page;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.snapins.DemoSnapin;
import com.lateblindcat.sid.snapins.Snapin;

public class Sid extends AbstractHandler {
	public void handle(String target, Request baseRequest, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException, ServletException {

		// Build a list of handlers
		List<Handler> handlers = new ArrayList<Handler>();

		handlers.add(new ImageHandler());
		handlers.add(new TemplateHandler());
		handlers.add(new CSSHandler());
		handlers.add(new ContentPageHandler());
		handlers.add(buildSnapins());
		handlers.add(buildPageHandlers());

		HttpRequest request = new HttpRequest(httpServletRequest);

		for (Handler handler : handlers) {
			PageResponse pageResponse = handler.process(request, null);

			if (pageResponse != null) {
				HttpServletResponseBuilder.fromPageResponse(httpServletResponse, pageResponse);
				baseRequest.setHandled(true);
				break;
			}
		}

		if (!baseRequest.isHandled()) {
			httpServletResponse.setContentType("text/html;charset=utf-8");
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			baseRequest.setHandled(true);

			httpServletResponse.getWriter().println("<h1>Page not found...</h1>");
		}
		// }
	}

	private Handler buildPageHandlers() {
		// TODO: some form of auto registration based on package
		// or using annotations
		List<Page> pages = new ArrayList<Page>();
		pages.add(new HomePage());

		return new PageHandler(pages);
	}

	private Handler buildSnapins() {
		// TODO: some form of auto registration based on package
		// or using annotations
		List<Snapin> snapins = new ArrayList<Snapin>();
		snapins.add(new DemoSnapin());

		return new SnapinHandler(snapins);

	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		server.setHandler(new Sid());

		server.start();
		server.join();
	}
}