package com.lateblindcat.sid.framework.pages;

import java.io.InputStream;

import com.lateblindcat.sid.framework.ResponseCode;
import com.lateblindcat.sid.framework.StringExpression;

/**
 * 
 * A simple factory style class for create new types of responses, e.g. html,
 * images
 * 
 * @author Ian Morgan
 * 
 */
public class PageResponseFactory {

	public static PageResponse html(String html) {
		return new PageResponseImpl(html, "text/html");
	}
	
	public static PageResponse html(StringExpression html) {
		return new PageResponseImpl(html.evalute(), "text/html");
	}

	public static PageResponse text(String text) {
		return new PageResponseImpl(text, "text/plain");
	}
	
	public static PageResponse jpeg(InputStream image){
		return new PageResponseImpl(image, "image/jpeg");		
	}
	
	public static PageResponse notFound(){
		return new PageResponseImpl(ResponseCode.SC_NOT_FOUND);
	}

	public static PageResponse internalError() {
		return new PageResponseImpl(ResponseCode.SC_INTERNAL_SERVER_ERROR);
	}

}