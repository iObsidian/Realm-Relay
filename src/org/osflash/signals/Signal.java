package org.osflash.signals;

import alde.flash.utils.SignalConsumer;

import java.util.Vector;

public class Signal<T> {

	public Vector<SignalConsumer<? super T>> listeners;

	public Signal() {
		listeners = new Vector<>();
	}

	public void add(SignalConsumer<? super T> t) {
		listeners.add(t);
	}

	public void dispatch(T t) {
		for (SignalConsumer<? super T> sf : listeners) {
			sf.dispatch(t);
		}
	}

	public void dispatch() {
		for (SignalConsumer sf : listeners) {
			sf.dispatch();
		}
	}

	public void addOnce(SignalConsumer onTextChanged) {
	}

	public void remove(SignalConsumer<? super T> onShowPackage) {
	}

	public void removeAll() {
	}
}
