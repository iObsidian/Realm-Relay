package flash.events;

public class KeyboardEvent extends Event {

	static public final String KEY_DOWN = "keyDown";

	static public final String KEY_UP = "keyUp";
	public final int keyCode;
	public int m_keyLocation;
	public int m_keyCode;

	public int charCode;
	public int keyLocation;
	public boolean ctrlKey;
	public boolean altKey;
	public boolean shiftKey;

	public KeyboardEvent(String name, int m_keyLocation, int m_keyCode, int charCode, int keyCode, int keyLocation, boolean ctrlKey, boolean altKey, boolean shiftKey) {
		super(name);
		this.m_keyLocation = m_keyLocation;
		this.m_keyCode = m_keyCode;
		this.charCode = charCode;
		this.keyCode = keyCode;
		this.keyLocation = keyLocation;
		this.ctrlKey = ctrlKey;
		this.altKey = altKey;
		this.shiftKey = shiftKey;
	}


}
