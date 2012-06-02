package com.lateblindcat.sid.core.handlers;

import com.lateblindcat.sid.core.framework.Request;
import com.lateblindcat.sid.core.framework.RequestData;
import com.lateblindcat.sid.framework.pages.PageResponse;

public interface Handler {

	PageResponse process (Request route, RequestData requestData);
}
