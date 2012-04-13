package com.lateblindcat.sid.framework.handlers;

import com.lateblindcat.sid.framework.RequestData;
import com.lateblindcat.sid.framework.Route;
import com.lateblindcat.sid.framework.pages.PageResponse;

public interface Handler {

	PageResponse process (Route route, RequestData requestData);
}
