package flash.events;

import alde.flash.utils.Function;

import java.util.function.Consumer;

public class EventDispatcher implements IEventDispatcher {

	public void addEventListener(String type, Consumer listener) {
		addEventListener(type, listener, false, 0, false);
	}

	native void addEventListener(String type, Consumer listener, boolean useCapture, int priority, Boolean useWeakReference);

	public native void removeEventListener(String type, Consumer listener);

	native void removeEventListener(String type, Consumer listener, Boolean useCapture);

	native Boolean dispatchEvent(Event event);

	native Boolean hasEventListener(String type);

	native Boolean willTrigger(String type);

}
