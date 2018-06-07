package flash.events;

import alde.flash.utils.Function;

import java.util.HashMap;
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




	private HashMap<Consumer<Event>, String> listeners;

	int startTime;
	public boolean visible;
	public boolean mouseEnabled;
	public Graphics graphics;

	public Sprite() {
		listeners = new HashMap<>();

	}

	protected int getTimer() {
		return (int) (System.currentTimeMillis() - startTime);
	}

	public void addEventListener(String event, Consumer<Event> consumer) {
		listeners.put(consumer, event);
	}

	public void removeEventListener(String event, Consumer<Event> consumer) {
		listeners.remove(consumer, event);
	}

	protected void trigger(String EVENT_TYPE) {
		for (Consumer<Event> c : listeners.keySet()) {
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
