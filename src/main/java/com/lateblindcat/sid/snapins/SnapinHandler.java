package com.lateblindcat.sid.snapins;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

import com.lateblindcat.sid.framework.HttpRequest;
import com.lateblindcat.sid.framework.RouteMatchResult;
import com.lateblindcat.sid.framework.pages.PageResponse;

public class SnapinHandler {

	private List<Snapin> snapins;
	
	public SnapinHandler (List<Snapin> snapins) {
		this.snapins = snapins;
	}
	
	public boolean handle (
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException {
		
		HttpRequest route = new HttpRequest(request);
		for (Snapin snapin : snapins){
			RouteMatchResult matchResult = snapin.matchesRoute(route);
			if (matchResult.matched){
				PageResponse r = snapin.process();
				byte[] buf = new byte[10000];
				int len = r.getContent().read(buf);
				response.getOutputStream().write(buf,0,len);
				response.setStatus(r.getStatus().getHttpCode());
				response.setContentType(r.getContentType());
				
				if (r.isInline()){
					response.addHeader("content-disposition","inline");
					response.addHeader("content-length", Integer.toString(len));	
				}
				baseRequest.setHandled(true);
				return true;
			}
		}
	
		return false;
	}
	
	
}
