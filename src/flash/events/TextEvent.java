package flash.events;

public class TextEvent extends Event {

	public TextEvent(String type) {
		super(type);
	}

	public TextEvent(String type, boolean bubbles, Boolean cancelable) {
		super(type, bubbles, cancelable);
	}
}
