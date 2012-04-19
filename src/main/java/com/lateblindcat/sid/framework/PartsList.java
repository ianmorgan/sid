package com.lateblindcat.sid.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Ian Morgan
 * 
 */
public class PartsList implements Iterable<String> {
	private List<String> parts;

	public PartsList(Collection<String> parts) {
		this.parts = new ArrayList<String>(parts);
	}

	public PartsList(String[] parts) {
		this.parts = Arrays.asList(parts);
	}

	@Override
	public Iterator<String> iterator() {
		return parts.iterator();
	}

	public int size() {
		return parts.size();
	}

	public String head() {
		return parts.get(0);
	}

	public PartsList tail() {
		List<String> working = new ArrayList<String>(this.parts);
		working.remove(0);
		return new PartsList(working);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String part : parts) {
			if (sb.length() > 0) {
				sb.append("/");
			}
			sb.append(part);
		}
		return sb.toString();
	}

}
