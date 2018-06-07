package flash.display;

import flash.geom.Point;

public class DisplayObjectContainer extends InteractiveObject {

	public DisplayObject addChild(DisplayObject child) {
		return null;
	}

	public DisplayObject addChildAt(DisplayObject child, int index){
		return null;
	}
	public DisplayObject removeChild(DisplayObject child){
		return null;
	}
	public DisplayObject removeChildAt(int index){
		return null;
	}

	public int getChildIndex(DisplayObject child){
		return 1;
	}

	public void setChildIndex(DisplayObject child, int index){
	}

	public DisplayObject getChildAt(int index){
		return null;
	}

	public DisplayObject getChildByName(String name){
		return null;
	}

	public int getNumChildren(){
		return 1;
	}

	public TextSnapshot getTextSnapshot(){
		return null;
	}

	public Array getObjectsUnderPoint(Point point){
		return null;
	}

	public Boolean areInaccessibleObjectsUnderPoint(Point point){
		return null;
	}

	public Boolean getTabChildren(){
		return null;
	}

	public void setTabChildren(Boolean enable){
	}

	public Boolean getMouseChildren(){
		return null;
	}

	public void setMouseChildren(Boolean enable){
	}

	public Boolean contains(DisplayObject child){
		return null;
	}

	public void swapChildrenAt(int index1, int index2){
	}

	public void swapChildren(DisplayObject child1, DisplayObject child2){
	}
}
