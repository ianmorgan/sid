package com.lateblindcat.sid.core.fp;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * A Immutable Map with support for functional style programming.
 * </p>
 * 
 * @author Ian Morgan
 * 
 */
public class ImmutableMap<K, V> {

	private Map<K, V> data;

	public ImmutableMap(Map<K, V> data) {
		this.data = data;
	}

	public V get(K key) {
		return data.get(key);
	}

	public boolean containsKey(K key) {
		return data.containsKey(key);
	}

	public Set<K> keySet() {
		return data.keySet();
	}

	public Set<Map.Entry<K, V>> entrySet() {
		return data.entrySet();
	}

}
