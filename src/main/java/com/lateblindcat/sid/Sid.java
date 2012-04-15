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
import com.lateblindcat.sid.framework.handlers.ImageHandler;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.snapins.DemoSnapin;
import com.lateblindcat.sid.snapins.Snapin;
import com.lateblindcat.sid.snapins.SnapinHandler;

public class Sid extends AbstractHandler {
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		List<Snapin> snapins = new ArrayList<Snapin>();
		snapins.add(new DemoSnapin());

		SnapinHandler runner = new SnapinHandler(snapins);

		if (!runner.handle(baseRequest, request, response)) {

			ImageHandler imageHandler = new ImageHandler();
			PageResponse pageResponse = imageHandler.process(
					new HttpRequest(request), null);

			if (pageResponse != null) {
				byte[] buf = new byte[10000];
				int len = pageResponse.getContent().read(buf);
				response.getOutputStream().write(buf,0,len);
				response.setStatus(pageResponse.getStatus().getHttpCode());
				response.setContentType(pageResponse.getContentType());
				
				if (pageResponse.isInline()){
					response.addHeader("content-disposition","inline");
					response.addHeader("content-length", Integer.toString(len));	
				}
				baseRequest.setHandled(true);

			} else {
				response.setContentType("text/html;charset=utf-8");
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);

				response.getWriter().println("<h1>Hello World!</h1>");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		server.setHandler(new Sid());

		server.start();
		server.join();
	}
}