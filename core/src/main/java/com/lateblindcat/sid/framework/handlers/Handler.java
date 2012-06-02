package com.lateblindcat.sid.framework.handlers;

import com.lateblindcat.sid.core.framework.Request;
import com.lateblindcat.sid.core.framework.RequestData;
import com.lateblindcat.sid.framework.pages.PageResponse;

public interface Handler {

	PageResponse process (Request route, RequestData requestData);
}
