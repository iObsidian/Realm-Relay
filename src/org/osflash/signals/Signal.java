package org.osflash.signals;

import alde.flash.utils.SignalFunction;

import java.util.Vector;
import java.util.function.Consumer;

public class Signal<T> {

	public Vector<SignalFunction<? super T>> listeners;

	public void add(Consumer<? super T> t) {
		listeners.add(new SignalFunction(t));
	}

	public void add(Runnable t) {
		listeners.add(new SignalFunction(t));
	}

	public void dispatch(T t) {
		for (SignalFunction<? super T> sf : listeners) {
			sf.dispatch(t);
		}
	}

	public void dispatch() {
		for (SignalFunction sf : listeners) {
			sf.dispatch();
		}
	}


}
