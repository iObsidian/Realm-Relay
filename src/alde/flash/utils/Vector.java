package alde.flash.utils;

import spark.filters.DropShadowFilter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * A wrapper for HashMap that simulates AS3's Vector
 */
public class Vector<T> implements Iterable<T> {

	public int length;
	private LinkedHashMap<Integer, T> map;

	public Vector() {
		this(0);
	}

	public Vector(int initialCapacity) {
		map = new LinkedHashMap<>();

		for (int i = 0; i < initialCapacity; i++) {
			push(null);
		}
	}

	public Vector(T... addAll) {
		add(addAll);
	}

	public Vector(List<T> addAll) {
		for (T t : addAll) {
			push(t);
		}
	}

	/**
	 * Used remove instead
	 */
	@Deprecated
	public void splice(double start, double deleteCount) {
	}

	public void remove(T t) {
		Iterator<T> e = this.iterator();

		for (Iterator<T> it = e; it.hasNext(); ) {
			T a = it.next();
			if (a.equals(t)) {
				it.remove();
			}
		}
	}

	public boolean contains(T t) {
		return map.containsValue(t);
	}

	public void push(T t) {
		put(map.size() + 1, t);
	}

	public void set(int index, T t) {
		put(index, t);
	}

	public T put(int index, T t) {
		map.put(index, t);
		length = map.size();

		return t;
	}

	public T get(int index) {
		return map.get(index);
	}

	//Removes the last element from the Vector and returns that element.
	public T pop() {
		if (length > 0) {
			length--;
		}
		return map.remove(map.size());
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


	/*
	 * Concatenates the Vectors specified in the parameters list with the elements in this Vector and creates a new Vector.
	 */
	public Vector<T> concat(Vector<T>... vectors) {
		List<T> data = new ArrayList<>();

		for (Vector<T> vec : vectors) {
			for (T t : vec) {
				data.add(t);
			}
		}
		return new Vector<T>(data);
	}


	public void clear() {
		map.clear();
	}

	public void sortOn(Vector sortOnFields, Vector sortOnParams) {
	}

	public void splice(T i, T i1, T lineTo, T lineTo1, T lineTo2) {
	}

	public void splice(int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
	}

	public boolean hasOwnProperty(T i) {
		return contains(i);
	}

	@Deprecated
	public int indexOf(T loc2) {
		return -1;
	}


	public void set(T set) {
		clear();
		add(set);
	}

	public int size() {
		return length;
	}
}
