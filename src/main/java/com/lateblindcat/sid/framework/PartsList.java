package com.lateblindcat.sid.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * <p>
 * A Immutable list for holding and manipulating the parts (tokens) in an Http
 * Request. Naming conventions and behaviors are based on Scala.
 * </p>
 * 
 * <p>
 * <i>Note: The current implementation is simplistic and not optimised.</i>
 * </p>
 * 
 * <p>
 * <i>Note: Consider implement using generics.</i>
 * </p>
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

	public PartsList() {
		this.parts = new ArrayList<String>();
	}

	@Override
	public Iterator<String> iterator() {
		return parts.iterator();
	}

	public int size() {
		return parts.size();
	}

	public boolean isEmpty() {
		return parts.isEmpty();
	}

	public String head() {
		return parts.get(0);
	}

	public PartsList tail() {
		List<String> working = new ArrayList<String>(this.parts);
		working.remove(0);
		return new PartsList(working);
	}

	public String last() {
		return parts.get(parts.size() - 1);
	}

	public PartsList append(String part) {
		List<String> working = new ArrayList<String>(this.parts);
		working.add(part);
		return new PartsList(working);
	}

	/**
	 * Takes the parts and turn into a path for use in http request 
	 * 
	 * @return
	 */
	public String expandToPath() {
		return toString();
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
