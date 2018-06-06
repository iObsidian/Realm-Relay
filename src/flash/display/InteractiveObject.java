package flash.display;

import flash.geom.Rectangle;

public interface InteractiveObject extends DisplayObject {

	public Boolean getTabEnabled();
	public void setTabEnabled(Boolean enabled);

	public int getTabIndex();
	public void setTabIndex(int index);
	public Object getFocusRect();
	public void setFocusRect(Object focusRect);
	public Boolean getMouseEnabled();

	public void setMouseEnabled(Boolean enabled);
	public Boolean getDoubleClickEnabled();
	public void setDoubleClickEnabled(Boolean enabled);
	
	public AccessibilityImplementation getAccessibilityImplementation();
	public void setAccessibilityImplementation(AccessibilityImplementation value);

	public Rectangle getSoftKeyboardInputAreaOfInterest();
	public void setSoftKeyboardInputAreaOfInterest(Rectangle value);
	public Boolean getNeedsSoftKeyboard();
	public void setNeedsSoftKeyboard(Boolean value);
	public Boolean requestSoftKeyboard();

	public ContextMenu getContextMenu();
	public void setContextMenu(ContextMenu contextMenu);
}
