package org.osflash.signals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Implementation of AS3's Signal
 * 
 * AS3 :
 * 		public const selected:Signal = new Signal(CharacterClass);
 * 		select.add(this.method);
 *
 * 
 * Java : 
 * 		public final Signal<CharacterClass> selected = new Signal();
 * 		select.add(this::method);
 *
 *
 * 	Note :
 * 	    Instead of using StaticInjector, use SignalName.getInstance();
 * 
 */
public class Signal<T> {

	public List<Consumer<T>> consumers;

	public Signal() {
		consumers = new ArrayList<Consumer<T>>();
	}

	public void add(Consumer<T> consumer) {
		consumers.add(consumer);
	}

	public void remove(Consumer<T> consumer) {
		consumers.remove(consumer);
	}

	public void dispatch(T object) {
		for (Consumer<T> consumer : consumers) {
			consumer.accept(object);
		}
	}

	/**
	 * Replaces Signal.dispatch() from AS3
	 * Consumer will accept null data.
	 */
	public void dispatch() {
		for (Consumer<T> consumer : consumers) {
			consumer.accept(null);
		}
	}



}
