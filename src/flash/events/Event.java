package flash.events;

public class Event {

	static public final String ADDED_TO_STAGE = "addedToStage";
	static public final String ENTER_FRAME = "enterFrame";
	static public final String REMOVED_FROM_STAGE = "removedFromStage";
	static public final String ACTIVATE = "activate";
	static public final String ADDED = "added";
	static public final String CANCEL = "cancel";
	static public final String CHANGE = "change";
	static public final String CLEAR = "clear";
	static public final String CLOSE = "close";
	static public final String COMPLETE = "complete";
	static public final String CONNECT = "connect";
	static public final String COPY = "copy";
	static public final String CUT = "cut";
	static public final String DEACTIVATE = "deactivate";
	static public final String FRAME_CONSTRUCTED = "frameConstructed";
	static public final String EXIT_FRAME = "exitFrame";
	static public final String ID3 = "id3";
	static public final String INIT = "init";
	static public final String MOUSE_LEAVE = "mouseLeave";
	static public final String OPEN = "open";
	static public final String PASTE = "paste";
	static public final String REMOVED = "removed";
	static public final String RENDER = "render";
	static public final String RESIZE = "resize";
	static public final String SCROLL = "scroll";
	static public final String TEXT_INTERACTION_MODE_CHANGE = "textInteractionModeChange";
	static public final String SELECT = "select";
	static public final String SELECT_ALL = "selectAll";
	static public final String SOUND_COMPLETE = "soundComplete";
	static public final String TAB_CHILDREN_CHANGE = "tabChildrenChange";
	static public final String TAB_ENABLED_CHANGE = "tabEnabledChange";
	static public final String TAB_INDEX_CHANGE = "tabIndexChange";
	static public final String UNLOAD = "unload";
	static public final String FULLSCREEN = "fullScreen";
	static public final String CONTEXT3D_CREATE = "context3DCreate";
	static public final String CLOSING = "closing";
	static public final String EXITING = "exiting";
	static public final String DISPLAYING = "displaying";
	static public final String PREPARING = "preparing";
	static public final String NETWORK_CHANGE = "networkChange";
	static public final String USER_IDLE = "userIdle";
	static public final String USER_PRESENT = "userPresent";
	static public final String STANDARD_OUTPUT_CLOSE = "standardOutputClose";
	static public final String STANDARD_ERROR_CLOSE = "standardErrorClose";
	static public final String STANDARD_INPUT_CLOSE = "standardInputClose";
	static public final String HTML_BOUNDS_CHANGE = "htmlBoundsChange";
	static public final String HTML_RENDER = "htmlRender";
	static public final String HTML_DOM_INITIALIZE = "htmlDOMInitialize";
	static public final String LOCATION_CHANGE = "locationChange";
	public String toString;
	public String type;
	public Boolean bubbles;
	public Boolean cancelable;
	public Object target;
	public Object currentTarget;
	public int eventPhase;

	public Event(String type) {
		this(type, false, false);
	}

	public Event(String type, boolean bubbles) {

	}

	public Event(String type, boolean bubbles, boolean cancelable) {

	}

	public String formatToString(String... className) {
		return "";
	}

	private void ctor(String type, Boolean bubbles, Boolean cancelable) {
		return;
	}

	public Event clone() {
		return null;
	}

	public void stopPropagation() {

	}

	public void stopImmediatePropagation() {

	}

	public void preventDefault() {

	}

}
