package com.lateblindcat.sid.core.handlers;

import com.lateblindcat.sid.framework.pages.PageResponse;
import com.lateblindcat.sid.rack.RackRequest;

public interface Handler {

	PageResponse process (RackRequest route);
}
