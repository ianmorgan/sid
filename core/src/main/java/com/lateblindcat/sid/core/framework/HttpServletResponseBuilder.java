package com.lateblindcat.sid.core.framework;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lateblindcat.sid.framework.pages.PageResponse;

public class HttpServletResponseBuilder {

	public static void fromPageResponse(HttpServletResponse httpServletResponse, PageResponse pageResponse)
			throws IOException {

		byte[] buf = new byte[50000];
		int len = pageResponse.getContent().read(buf);
		httpServletResponse.getOutputStream().write(buf, 0, len);

		httpServletResponse.setStatus(pageResponse.getStatus().getHttpCode());
		httpServletResponse.setContentType(pageResponse.getContentType());

		if (pageResponse.isInline()) {
			httpServletResponse.addHeader("content-disposition", "inline");
			httpServletResponse.addHeader("content-length", Integer.toString(len));
		}
	}

}
