package com.lateblindcat.sid.core.fp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * A Immutable list with support for functional style programming.
 * </p>
 * 
 * @author Ian Morgan
 * 
 */

public class ImmutableList<T> implements Iterable<T> {
	private List<T> parts;

	public ImmutableList(Collection<T> parts) {
		this.parts = new ArrayList<T>(parts);
	}

	public ImmutableList(List<T> parts, boolean isImmutable) {
		if (isImmutable) {
			this.parts = parts;
		} else {
			this.parts = new ArrayList<T>(parts);
		}
	}

	public ImmutableList(T[] parts) {
		this(Arrays.asList(parts));
	}

	@Override
	public Iterator<T> iterator() {
		return parts.iterator();
	}

	public int size() {
		return parts.size();
	}

	public boolean isEmpty() {
		return parts.isEmpty();
	}

	public T head() {
		return parts.get(0);
	}

	public ImmutableList<T> tail() {
		List<T> working = new ArrayList<T>(this.parts);
		working.remove(0);
		return new ImmutableList<T>(working, true);
	}

	public T last() {
		return parts.get(parts.size() - 1);
	}

	public ImmutableList<T> append(T part) {
		List<T> working = new ArrayList<T>(this.parts);
		working.add(part);
		return new ImmutableList<T>(working, true);
	}

	public String join(String concatenator) {
		StringBuilder sb = new StringBuilder();
		for (T part : parts) {
			if (sb.length() > 0) {
				sb.append(concatenator);
			}
			sb.append(part.toString());
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return join("\n");
	}

}