package org.osflash;

import org.osflash.signals.Signal;

public class OnceSignal extends Signal {

	public boolean hasBeenDispatched;

	@Override
	public void dispatch(Object o) {
		if (!hasBeenDispatched) {
			hasBeenDispatched = true;
			super.dispatch(o);
		}
	}
}
