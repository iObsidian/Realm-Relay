package flash.events;

import flash.display.InteractiveObject;

public class MouseEvent extends Event {

	static public final String CLICK = "click";
	static public final String DOUBLE_CLICK = "doubleClick";
	static public final String MOUSE_DOWN = "mouseDown";
	static public final String MOUSE_MOVE = "mouseMove";
	static public final String MOUSE_OUT = "mouseOut";
	static public final String MOUSE_OVER = "mouseOver";
	static public final String MOUSE_UP = "mouseUp";
	static public final String MOUSE_WHEEL = "mouseWheel";
	static public final String ROLL_OUT = "rollOut";
	static public final String ROLL_OVER = "rollOver";
	static public final String MIDDLE_CLICK = "middleClick";
	static public final String MIDDLE_MOUSE_DOWN = "middleMouseDown";
	static public final String MIDDLE_MOUSE_UP = "middleMouseUp";
	static public final String RIGHT_CLICK = "rightClick";
	static public final String RIGHT_MOUSE_DOWN = "rightMouseDown";
	static public final String RIGHT_MOUSE_UP = "rightMouseUp";
	static public final String CONTEXT_MENU = "contextMenu";
	public Number localX;
	public Number localY;
	public InteractiveObject relatedObject;
	public Boolean ctrlKey;
	public Boolean altKey;
	public Boolean shiftKey;
	public Boolean buttonDown;
	public int delta;
	public Number stageX;
	public boolean updateAfterEvent;
	public Boolean isRelatedObjectInaccessible;
	private Number StageY;

	public MouseEvent(String type) {
		this(type, true, false, 0, 0, null, false, false, false, false, 0);
	}

	public MouseEvent(String type, Boolean bubbles, Boolean cancelable, Number localX, Number localY, InteractiveObject relatedObject, Boolean ctrlKey, Boolean altKey, Boolean shiftKey, Boolean buttonDown, int delta) {
		super(type);
	}

}
