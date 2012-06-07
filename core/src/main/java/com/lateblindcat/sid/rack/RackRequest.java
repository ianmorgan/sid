package com.lateblindcat.sid.rack;

import java.util.Map;

/**
 * Java equivalent to the Rack::Request class.
 */
public class RackRequest {
	private Env env; 

	public RackRequest(Env env) {

	}
	
	public String scriptName(){
		return env.SCRIPT_NAME;
	}
	
//	public Map<String,String> params(){
//		return env.
//	}
//	
//	public String contentType(){
//		return env
//	}
//
//	public String GET() {
//		POST   []   []=   accept_encoding   body   content_charset   content_length   content_type   cookies   delete?   form_data?   fullpath   get?   head?   host   host_with_port   ip   logger   media_type   media_type_params   new   options?   params   parse_multipart   parse_query   parseable_data?   path   path_info   path_info=   port   post?   put?   query_string   referer   referrer   request_method   scheme   script_name   script_name=   session   session_options   trace?   url   user_agent   values_at   xhr?   
//	}
}
