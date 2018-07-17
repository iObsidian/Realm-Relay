package flash.display;

import flash.airglobal.Graphics;
import flash.geom.Rectangle;
import flash.media.SoundTransform;

public class Sprite extends DisplayObjectContainer {

	public Graphics graphics = new Graphics();

	native public Boolean getButtonMode();

	native public void setButtonMode(Boolean value);

	native public void startDrag(Boolean lockCenter, Rectangle bounds);

	native public void stopDrag();

	native public void startTouchDrag(int touchPointID, Boolean lockCenter, Rectangle bounds);

	native public void stopTouchDrag(int touchPointID);

	native public DisplayObject getDropTarget();

	private void constructChildren() {

	}

	native public Sprite getHitArea();

	native public void setHitArea(Sprite value);

	native public Boolean getUseHandCursor();

	native public void setUseHandCursor(Boolean value);

	native public SoundTransform getSoundTransform();

	native public void setSoundTransform(SoundTransform sndTransform);


}
