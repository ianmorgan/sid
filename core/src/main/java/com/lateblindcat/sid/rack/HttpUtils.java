package com.lateblindcat.sid.rack;

import java.util.HashMap;
import java.util.Map;

/**
 * A home  for useful http util methods 
 * 
 * @author Ian Morgan
 *
 */
public class HttpUtils {
	
	/**
	 * Parses the query params String. 
	 *  
	 * @param query
	 * @return
	 */
	public static Map<String, String> parseQueryParams(String query)  
	{  
	    String[] params = query.split("&");  
	    Map<String, String> map = new HashMap<String, String>();  
	    for (String param : params)  
	    {  
	        String name = param.split("=")[0];  
	        String value = param.split("=")[1];  
	        map.put(name, value);  
	    }  
	    return map;  
	}  

}
