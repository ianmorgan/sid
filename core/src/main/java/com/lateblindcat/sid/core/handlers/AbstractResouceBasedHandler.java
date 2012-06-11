package com.lateblindcat.sid.core.handlers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.lateblindcat.sid.core.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;
import com.lateblindcat.sid.rack.RackRequest;

/**
 * A generic handler that load and return content from a Spring Resource 
 * 
 * @author Ian Morgan
 *
 */
public abstract class AbstractResouceBasedHandler implements Handler {
	
	private ResourceLoader loader = new DefaultResourceLoader();

	public PageResponse process(RackRequest request) {
		RouteMatchResult matchResult = checkRoute(request);
		if (matchResult.matched) {
			Resource resource = loader.getResource(buildResourcePath(matchResult));
			if (resource.exists()) {
				try {
					InputStream is = resource.getInputStream();
					return buildResponse(is);
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
	
	abstract protected PageResponse buildResponse(InputStream is); 
	abstract protected RouteMatchResult checkRoute(RackRequest request);
	abstract protected String buildResourcePath (RouteMatchResult matchResult);

}
