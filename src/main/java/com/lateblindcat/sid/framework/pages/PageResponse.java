package com.lateblindcat.sid.framework.pages;

import java.io.InputStream;

import com.lateblindcat.sid.framework.ResponseCode;

/**
 * A simplified way of defining the HTTP response. This is 
 * used to generate the final HttpServletResponse
 *  
 * @author Ian Morgan
 *
 */
public interface PageResponse {
	
	/**
	 * The content to be returned, as a stream
	 * 
	 *
	 * @return
	 */
	InputStream getContent();
	
	/**
	 * The HTTP status, e.g 200(OK)
	 * @return
	 */
	ResponseCode getStatus();
	
	/**
	 * The MIME type and optional encoding, e.g "text/html;charset:utf-8" 
	 * @return
	 */
	String getContentType();
	
	/**
	 * Is this to be rendered inline. Only for file content type lke PDF
	 * @return
	 */
	boolean isInline();
	
	/**
	 * The file name. Only for file content type lke PDF
	 * @return
	 */
	String getFileName();
}
