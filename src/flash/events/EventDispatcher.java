package flash.events;

import alde.flash.utils.EventConsumer;
import flash.utils.Dictionary;

import java.util.function.Consumer;


public abstract class EventDispatcher implements IEventDispatcher {

	public Dictionary<EventConsumer, String> listeners;
	public boolean visible;
	public boolean mouseEnabled;

	public EventDispatcher() {
		listeners = new Dictionary<>();
	}

	public native void removeEventListener(String type, EventConsumer listener, Boolean useCapture);

	public boolean dispatchEvent(Event event) {
		for (EventConsumer c : listeners.keySet()) {
			if (listeners.get(c).equals(event.type)) {
				c.accept(event);
			}
		}
		return true;
	}

	public boolean hasEventListener(String type) {
		return false;
	}

	public boolean willTrigger(String type) {
		return false;
	}

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
				c.accept(new Event(EVENT_TYPE));
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
