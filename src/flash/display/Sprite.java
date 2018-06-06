package flash.display;

import flash.airglobal.Graphics;
import flash.events.Event;
import flash.geom.Rectangle;

import java.util.HashMap;
import java.util.function.Consumer;

public class Sprite extends DisplayObjectContainer {

	public abstract Graphics getGraphics();
	public abstract Boolean getButtonMode();

	public abstract void setButtonMode(Boolean value);

	public abstract void startDrag(Boolean lockCenter, Rectangle bounds);

	public abstract void stopDrag();

	public abstract void startTouchDrag(int touchPointID, Boolean lockCenter, Rectangle bounds);

	public abstract void stopTouchDrag(int touchPointID);

	public abstract DisplayObject getDropTarget();

	private void constructChildren();

	public abstract Sprite getHitArea();

	public abstract void setHitArea(Sprite value);

	public abstract Boolean getUseHandCursor();

	public abstract void setUseHandCursor(Boolean value);

	public abstract SoundTransform getSoundTransform();

	public abstract void setSoundTransform(SoundTransform sndTransform);


}
