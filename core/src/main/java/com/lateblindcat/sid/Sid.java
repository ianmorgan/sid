package com.lateblindcat.sid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.lateblindcat.sid.core.framework.AppContext;
import com.lateblindcat.sid.core.framework.AppContextImpl;
import com.lateblindcat.sid.core.framework.Config;
import com.lateblindcat.sid.core.framework.HttpServletResponseBuilder;
import com.lateblindcat.sid.core.framework.ModuleConfig;
import com.lateblindcat.sid.core.framework.ModuleConfigLoader;
import com.lateblindcat.sid.core.handlers.CSSHandler;
import com.lateblindcat.sid.core.handlers.ContentPageHandler;
import com.lateblindcat.sid.core.handlers.Handler;
import com.lateblindcat.sid.core.handlers.ImageHandler;
import com.lateblindcat.sid.core.handlers.PageHandler;
import com.lateblindcat.sid.core.handlers.SnapinHandler;
import com.lateblindcat.sid.core.handlers.TemplateHandler;
import com.lateblindcat.sid.framework.pages.HomePage;
import com.lateblindcat.sid.framework.pages.Page;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.snapins.Snapin;

public class Sid extends AbstractHandler {

	static List<ModuleConfig> configs;

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
		
		//HandlerList<Snapin> snapins = new HandlerList<Snapin>()
		

		com.lateblindcat.sid.core.framework.Request request = new com.lateblindcat.sid.core.framework.Request(
				httpServletRequest);

		for (Handler handler : handlers) {
			System.out.println(">> handler: " + handler.getClass().getName());
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
		List<Snapin> snapins = new ArrayList<Snapin>();
		for (ModuleConfig config : configs) {
			snapins.addAll(config.snapins());
		}
		return new SnapinHandler(snapins);
	}

	public static void main(String[] args) throws Exception {

		// Set up a simple configuration that logs on the console.
		BasicConfigurator.configure();

		AppContext context = new AppContextImpl();
		Config config = loadConfig();
		Server server = new Server(config.getPort());
		server.setHandler(new Sid());

		configs = new ModuleConfigLoader(context).loadConfigs();

		loadConfig();

		server.start();
		server.join();
	}

	private static Config loadConfig() throws Exception {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File("sid.properties")));
			return new Config(properties);
		} catch (FileNotFoundException ex) {
			return new Config();
		}
	}
}