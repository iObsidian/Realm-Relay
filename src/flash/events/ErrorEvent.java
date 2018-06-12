package flash.events;


public class ErrorEvent extends TextEvent {

	static public final String ERROR = "error";

	public ErrorEvent(String type) {
		super(type);
	}
}
