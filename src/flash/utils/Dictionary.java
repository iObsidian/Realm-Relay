package flash.utils;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Key, Value wrapper for HashMap to match AS3's Dictionary
 */
public class Dictionary<K, V> extends HashMap<K, V> implements Iterable<V> {

	public Dictionary() {
	}


	public Dictionary(K maxid) {
	}

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

}
