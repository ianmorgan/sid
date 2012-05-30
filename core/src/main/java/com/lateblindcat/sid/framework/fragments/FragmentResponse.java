package com.lateblindcat.sid.framework.fragments;

import java.io.InputStream;

/**
 * Define a fragment response. This may be combined into a 
 * single web page (i.e. become part of a PageRespone) 
 * or be handled as part of an Ajax request. 
 * 
 * @author Ian Morgan
 *
 */
public interface FragmentResponse {
	
	/**
	 * The content to be returned, as a stream.
	 * 
	 * @return
	 */
	InputStream getContent();
	

}
