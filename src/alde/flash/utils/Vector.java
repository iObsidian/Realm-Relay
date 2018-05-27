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
		this(0);
	}

	public Vector(int initialCapacity) {
		map = new HashMap<>();

		for (int i = 0; i < initialCapacity; i++) {
			push(null);
		}
	}

	public Vector(T... addAll) {
		add(addAll);
	}


	public void push(T t) {
		put(map.size() + 1, t);
	}

	public void set(int index, T t) {
		put(index, t);
	}

	public void put(int index, T t) {
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


	public void add(T t) {
		push(t);
	}

	public void add(T... list) {
		for (T t : list) {
			push(t);
		}
	}

	public T concat() {
	}
}
