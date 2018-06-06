package flash.display;

import flash.geom.Point;

public interface DisplayObjectContainer extends InteractiveObject {

	public DisplayObject addChild(DisplayObject child);

	public DisplayObject addChildAt(DisplayObject child, int index);

	public DisplayObject removeChild(DisplayObject child);

	public DisplayObject removeChildAt(int index);

	public int getChildIndex(DisplayObject child);

	public void setChildIndex(DisplayObject child, int index);

	public DisplayObject getChildAt(int index);

	public DisplayObject getChildByName(String name);

	public int getNumChildren();

	public TextSnapshot getTextSnapshot();

	public Array getObjectsUnderPoint(Point point);

	public Boolean areInaccessibleObjectsUnderPoint(Point point);

	public Boolean getTabChildren();

	public void setTabChildren(Boolean enable);

	public Boolean getMouseChildren();

	public void setMouseChildren(Boolean enable);

	public Boolean contains(DisplayObject child);

	public void swapChildrenAt(int index1, int index2);

	public void swapChildren(DisplayObject child1, DisplayObject child2);
}
