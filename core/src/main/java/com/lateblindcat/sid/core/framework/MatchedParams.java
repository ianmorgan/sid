package com.lateblindcat.sid.core.framework;

import java.util.Map;

import com.lateblindcat.sid.core.fp.ImmutableList;
import com.lateblindcat.sid.core.fp.ImmutableMap;

public class MatchedParams extends ImmutableMap<String, Object>{

	public MatchedParams(Map<String, Object> data) {
		super(data);
	}
	
	@SuppressWarnings("unchecked")
	public ImmutableList<String> splats() {
		return (ImmutableList<String>) this.get("splats");
	}
	
	@SuppressWarnings("unchecked")
	public ImmutableList<String> captures() {
		return (ImmutableList<String>) this.get("captures");
	}


}
