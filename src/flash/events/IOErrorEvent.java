package flash.events;

public class IOErrorEvent extends Event {

	static public final String IO_ERROR = "ioError";
	static public final String NETWORK_ERROR = "networkError";
	static public final String DISK_ERROR = "diskError";
	static public final String VERIFY_ERROR = "verifyError";

	public IOErrorEvent(String type) {
		super(type);
	}
}
