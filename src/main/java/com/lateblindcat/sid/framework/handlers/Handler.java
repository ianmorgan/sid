package com.lateblindcat.sid.framework.handlers;

import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.HttpRequest;
import com.lateblindcat.sid.framework.pages.PageResponse;

public interface Handler {

	PageResponse process (HttpRequest route, RequestData requestData);
}
