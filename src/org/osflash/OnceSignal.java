package org.osflash;

import org.osflash.signals.Signal;

public class OnceSignal<T> extends Signal<T> {

	public boolean hasBeenDispatched;

	@Override
	public void dispatch(T o) {
		if (!hasBeenDispatched) {
			hasBeenDispatched = true;
			super.dispatch(o);
		}
	}
}
