package com.lateblindcat.sid.framework.pages;

public class HomePage implements Page{

	@Override
	public String getRoute() {
		return "GET:/";
	}

	@Override
	public PageResponse process() {
		return PageResponseFactory.html("<html><body>this is my homne page</body></html>");
	}

}
