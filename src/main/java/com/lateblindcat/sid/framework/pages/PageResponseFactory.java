package com.lateblindcat.sid.framework.pages;

import java.io.InputStream;

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
	
	public static PageResponse text(String text) {
		return new PageResponseImpl(text, "text/plain");
	}
	
	public static PageResponse jpeg(InputStream image){
		return new PageResponseImpl(image, "image/jpeg");
		
	}

}
