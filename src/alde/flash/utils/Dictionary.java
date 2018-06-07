package alde.flash.utils;

import java.util.HashMap;
import java.util.Iterator;

public class Dictionary<K, V> extends HashMap<K, V> implements Iterable<V> {

	@Override
	public Iterator<V> iterator() {
		return values().iterator();
	}

	public boolean contains(Object param1) {
		return containsKey(param1);
	}

	public boolean booleanValue() {
		return true;
	}

	public boolean hasOwnProperty(V v) {
		return contains(v);
	}
}
