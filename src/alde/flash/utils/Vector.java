package alde.flash.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A wrapper for HashMap that simulates AS3's Vector
 */
public class Vector<T> implements Iterable<T> {

	private Map<Integer, T> map;
	public int length;

	public Vector() {
		map = new HashMap<Integer, T>();
		length = 0;
	}

	public Vector(int initialCapacity) {
		map = new HashMap<Integer, T>();

		for (int i = 0; i < initialCapacity; i++) {
			push(null);
		}
	}

	public void push(T t) {
		map.put(map.size() + 1, t);
		length = map.size();
	}

	public void put(int index, T t) {
		map.put(index, t);
		length = map.size();
	}

	public void set(int index, T t) {
		map.put(index, t);
		length = map.size();
	}

	public T get(int index) {
		return map.get(index);
	}

	@Override
	public Iterator<T> iterator() {
		return map.values().iterator();
	}


}
