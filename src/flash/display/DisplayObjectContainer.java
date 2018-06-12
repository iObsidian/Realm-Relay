package flash.display;

import alde.flash.utils.Vector;
import flash.geom.Point;
import flash.text.TextSnapshot;

public class DisplayObjectContainer extends InteractiveObject {

	public int numChildren;
	public TextSnapshot textSnapshot;
	public Boolean tabChildren;
	public Boolean mouseChildren;

	public DisplayObject addChild(DisplayObject child) {
		return null;
	}

	public DisplayObject addChildAt(DisplayObject child, int index) {
		return null;
	}

	public DisplayObject removeChild(DisplayObject child) {
		return null;
	}

	public DisplayObject removeChildAt(int index) {
		return null;
	}

	public int getChildIndex(DisplayObject child) {
		return 1;
	}

	public DisplayObject getChildAt(int index) {
		return null;
	}

	public DisplayObject getChildByName(String name) {
		return null;
	}

	public Vector getObjectsUnderPoint(Point point) {
		return null;
	}

	public Boolean areInaccessibleObjectsUnderPoint(Point point) {
		return null;
	}

	public Boolean contains(DisplayObject child) {
		return null;
	}

	public void swapChildrenAt(int index1, int index2) {
		return;
	}

	public void swapChildren(DisplayObject child1, DisplayObject child2) {
		return;
	}

}
