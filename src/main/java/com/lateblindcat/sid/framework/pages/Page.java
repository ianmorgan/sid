package com.lateblindcat.sid.framework.pages;

public interface Page {

	/**
	 * The route to match against
	 * @return
	 */
	String getRoute();

	PageResponse process();
}
