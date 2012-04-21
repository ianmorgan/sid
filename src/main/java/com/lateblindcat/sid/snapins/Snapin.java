package com.lateblindcat.sid.snapins;

import com.lateblindcat.sid.framework.pages.PageResponse;

/**
 * Defines a "snapin" type component. These automatically register and provide 
 * a simple MMC (Microsoft Management Console) style user interface 
 * 
 * @author Ian Morgan
 *
 */
public interface Snapin {
	/**
	 * The display name - used in titles, menus
	 * @return
	 */
	String getName();
	
	/**
	 * The route to match against
	 * @return
	 */
	String getRoute();

	PageResponse process();
}