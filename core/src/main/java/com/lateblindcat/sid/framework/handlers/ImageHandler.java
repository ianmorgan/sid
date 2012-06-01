package com.lateblindcat.sid.framework.handlers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.lateblindcat.sid.framework.Request;
import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;

public class ImageHandler implements Handler {
	private ResourceLoader loader = new DefaultResourceLoader();
	private SimpleRouteMatcher routeMatcher = new SimpleRouteMatcher(new Route("GET:/images/*"));

	@Override
	public PageResponse process(Request request, RequestData requestData) {
		RouteMatchResult matchResult = routeMatcher.matches(request);
		if (matchResult.matched) {
			System.out.println (">> loading image " + matchResult.expandedParts.expandToPath());
			Resource image = loader.getResource("classpath:images/" + matchResult.expandedParts.expandToPath());
			
			System.out.println (">> loaded using resource: " + image.getClass().getName());
			if (image.exists()) {
				try {
					InputStream is = image.getInputStream();
					System.out.println (">> loaded stream : " + is.getClass().getName());
					return PageResponseFactory.jpeg(is );
				} catch (IOException e) {
					return PageResponseFactory.internalError();
				}
			} else {
				return PageResponseFactory.notFound();
			}
		} else {
			return null;
		}

	}

}
