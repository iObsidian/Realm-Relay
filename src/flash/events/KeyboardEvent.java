package flash.events;

public class KeyboardEvent extends Event {

	public KeyboardEvent(String type, Boolean bubbles, Boolean cancelable, int charCodeValue, int keyCodeValue, int keyLocationValue, Boolean ctrlKeyValue, Boolean altKeyValue, Boolean shiftKeyValue);

	public int m_keyLocation;
	public int m_keyCode;

	public String toString();

	public int getCharCode();

	public void setCharCode(int value);

	public int getKeyCode();

	public void setKeyCode(int value);

	public int getKeyLocation();

	public void setKeyLocation(int value);

	public Boolean getCtrlKey();

	public void setCtrlKey(Boolean value);

	public Boolean getAltKey();

	public void setAltKey(Boolean value);

	public Boolean getShiftKey();

	public void setShiftKey(Boolean value);

	public void updateAfterEvent();

	static public final String KEY_DOWN = "keyDown";

	static public final String KEY_UP = "keyUp";

}
