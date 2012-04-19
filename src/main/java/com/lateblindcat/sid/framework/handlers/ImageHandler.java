package com.lateblindcat.sid.framework.handlers;

import java.io.File;
import java.io.FileInputStream;

import com.lateblindcat.sid.framework.HttpRequest;
import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.SimpleRouteMatcher;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;

public class ImageHandler implements Handler {

	private SimpleRouteMatcher routeMatcher = new SimpleRouteMatcher(new Route("GET:/images"));
	
	@Override
	public PageResponse process(HttpRequest request, RequestData requestData) {
		RouteMatchResult matchResult = routeMatcher.matches(request);
		
		if (matchResult.matched){
			FileInputStream fis = null;
			try {
				fis = new FileInputStream (new File("src/main/resources/images/sid.jpg"));
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
			PageResponse response = PageResponseFactory.jpeg(fis);
			return response;
		}
		else {
			return null;
		}
		
	}

}
