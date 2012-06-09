package com.lateblindcat.sid.core.framework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lateblindcat.sid.core.handlers.Handler;

public class HandlerList<H extends Handler> implements Iterable<H> {
	private List<HandlerListItem> items = new ArrayList<HandlerListItem>();

	public void addAll(ModuleConfig moduleConfig, List<H> handlers) {
		for (H h : handlers) {
			HandlerListItem item = new HandlerListItem();
			item.handler = h;
			item.moduleConfig = moduleConfig;
			items.add(item);
		}
	}

	@Override
	public Iterator<H> iterator() {
		List<H> results = new ArrayList<H>();
		for (HandlerListItem item : items) {
			results.add(item.handler);
		}
		return results.iterator();
	}

	public Iterable<H> handlersForModule(ModuleConfig moduleConfig) {
		List<H> results = new ArrayList<H>();
		for (HandlerListItem item : items) {
			if (item.moduleConfig.equals(moduleConfig)) {
				results.add(item.handler);
			}
		}
		return results;
	}

	private class HandlerListItem {
		public H handler;
		public ModuleConfig moduleConfig;
	}
}
