package com.lateblindcat.sid.core.framework;

import java.util.List;

import com.lateblindcat.sid.snapins.Snapin;

/**
 * This interface describes the configuration of a module.
 * 
 * @author Ian Morgan 
 *
 */
public interface ModuleConfig {
	
	/**
	 * The full name for use in display 
	 */	
	String name();
	
	/**
	 * A short prefix for use in URLs, logging etc
	 */
	String prefix();
	
	/**
	 * A list of the snapins defined by the module, if any  
	 * 
	 * @return a list or null.
	 */
	List<Snapin> snapins();
}
