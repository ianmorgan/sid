package com.lateblindcat.sid.snapins;

import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.pages.PageResponse;

/**
 * Defines a "snapin" type component. These automatically register and provide 
 * a simple MMC (Microsoft Management Console) style user interface 
 * 
 * @author Ian Morgan
 *
 */
public interface Snapin {
	String getName();
	RouteMatchResult matchesRoute (Route route);
	PageResponse process();
}
