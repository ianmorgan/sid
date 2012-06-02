package com.lateblindcat.sid.snapins;

import com.lateblindcat.sid.core.framework.Request;
import com.lateblindcat.sid.core.framework.Route;
import com.lateblindcat.sid.framework.forms.FormBuilder;
import com.lateblindcat.sid.framework.forms.Label;
import com.lateblindcat.sid.framework.forms.SubmitButton;
import com.lateblindcat.sid.framework.forms.TextField;
import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.framework.pages.PageResponseFactory;

public class TwitterSearchFormSnapin implements Snapin {

	@Override
	public String getName() {
		return "Twitter Search Form";
	}

	@Override
	public Route getRoute() {
		return new Route("GET:/twittersearchform");
	}

	@Override
	public PageResponse process(Request request) {
		FormBuilder builder = new FormBuilder("test", "GET", "/dotwittersearch");
		builder.with(new TextField(new Label("search"), "q")).with(new SubmitButton("submit"));

		return PageResponseFactory.html(builder.render(null));
	}

}
