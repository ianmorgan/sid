package com.lateblindcat.sid.core.framework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lateblindcat.sid.core.handlers.Handler;

public class HandlerList<H extends Handler> implements Iterable<H> {
	private static final long serialVersionUID = -473297410372065849L;
	private List<HandlerListItem<H>> items = new ArrayList<HandlerListItem<H>>();

	public void addAll(ModuleConfig moduleConfig, List<H> handlers) {
		for (H h : handlers) {
			HandlerListItem<H> item = new HandlerListItem<H>();
			item.handler = h;
			item.moduleConfig = moduleConfig;
			items.add(item);
		}
	}

	@Override
	public Iterator<H> iterator() {
		List<H> results = new ArrayList<H>();
		for (HandlerListItem<H> item : items) {
			results.add(item.handler);
		}
		return results.iterator();
	}

	public Iterable<H> handlersForModule(ModuleConfig moduleConfig) {
		List<H> results = new ArrayList<H>();
		for (HandlerListItem<H> item : items) {
			if (item.moduleConfig.equals(moduleConfig)) {
				results.add(item.handler);
			}
		}
		return results;
	}

	private class HandlerListItem<H extends Handler> {
		public H handler;
		public ModuleConfig moduleConfig;
	}
}
