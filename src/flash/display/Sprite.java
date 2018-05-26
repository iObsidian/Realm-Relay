package flash.display;

import flash.events.Event;

import java.util.HashMap;
import java.util.function.Consumer;

public class Sprite {

	private HashMap<Consumer<Event>, String> listeners;

	int startTime;

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
