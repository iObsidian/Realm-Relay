package flash.events;

import alde.flash.utils.SignalFunction;
import flash.airglobal.Graphics;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class EventDispatcher {

	public HashMap<SignalFunction, String> listeners;
	public boolean visible;
	public boolean mouseEnabled;
	public Graphics graphics;
	int startTime;

	public EventDispatcher() {
		listeners = new HashMap<>();
	}

	native void removeEventListener(String type, Consumer listener, Boolean useCapture);

	native Boolean dispatchEvent(Event event);

	native Boolean hasEventListener(String type);

	native Boolean willTrigger(String type);

	protected int getTimer() {
		return (int) (System.currentTimeMillis() - startTime);
	}

	public void addEventListener(String event, Consumer<? super Event> listener) {
		addEventListener(event, new SignalFunction(listener), false, 0, false);
	}

	public void addEventListener(String event, Runnable listener) {
		addEventListener(event, new SignalFunction(listener), false, 0, false);
	}

	public void addEventListener(String event, SignalFunction listener) {
		addEventListener(event, listener, false, 0, false);
	}

	void addEventListener(String event, SignalFunction listener, boolean useCapture, int priority, Boolean useWeakReference) {
		listeners.put(listener, event);
	}


	public void addListener(Consumer<? extends Event> consumer) {
	}

	public void removeEventListener(String event, Consumer listener) {
		//listeners.remove(listener, event);
	}

	public void removeEventListener(String event, Runnable listener) {
		//listeners.remove(listener, event);
	}


	protected void trigger(String EVENT_TYPE) {
		for (SignalFunction c : listeners.keySet()) {
			if (listeners.get(c).equals(EVENT_TYPE)) {
				Event e = new Event(EVENT_TYPE);
				c.dispatch(e);
			}
		}
	}

	//TODO link these methods with engine render cycle

	void onAddedToStage(Integer param1) {
		trigger(Event.ADDED_TO_STAGE);
	}

	void onRemovedFromStage(Integer param1) {
		trigger(Event.REMOVED_FROM_STAGE);
	}

	void onEnterFrame(Integer param1) {
		trigger(Event.ENTER_FRAME);
	}

}
