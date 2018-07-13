package flash.events;

import alde.flash.utils.EventConsumer;
import flash.airglobal.Graphics;
import flash.utils.Dictionary;

import java.util.function.Consumer;


public class EventDispatcher {

	public Dictionary<EventConsumer, String> listeners;
	public boolean visible;
	public boolean mouseEnabled;
	public Graphics graphics;


	public EventDispatcher() {
		listeners = new Dictionary<>();
	}

	public native void removeEventListener(String type, EventConsumer listener, Boolean useCapture);

	public native Boolean dispatchEvent(Event event);

	public native Boolean hasEventListener(String type);

	public native Boolean willTrigger(String type);


	public void addEventListener(String event, EventConsumer listener) {
		this.addEventListener(event, listener, false, 0, false);
	}

	public void addEventListener(String event, EventConsumer listener, boolean useCapture, int priority, Boolean useWeakReference) {
		listeners.put(listener, event);
	}


	public void addListener(Consumer<? extends Event> consumer) {
	}

	public void removeEventListener(String event, EventConsumer listener) {
		//listeners.remove(listener, event);
	}

	protected void trigger(String EVENT_TYPE) {
		for (EventConsumer c : listeners.keySet()) {
			if (listeners.get(c).equals(EVENT_TYPE)) {
				Event e = new Event(EVENT_TYPE);
				c.accept(e);
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
