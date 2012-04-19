package com.lateblindcat.sid.framework;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RequestPath {
	
	private String path;
	public RequestPath(HttpServletRequest req){
		this.path = req.getPathInfo();
		System.out.print(this.path);
	}
	
	public RequestPath(String path ){
		this.path = path;
	}

	
	public String getPath(){
		return path;
	}
	
	
	public List<String> getParts(){
		String working = path;
		if (working.startsWith("/")){
			working = working.substring(1,working.length());
		}
		
		return Arrays.asList(working.split("/"));
	}


}
