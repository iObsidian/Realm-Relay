package flash.display;

import alde.flash.utils.Vector;
import flash.accessibility.AccessibilityProperties;
import flash.events.EventDispatcher;
import flash.geom.Matrix;
import flash.geom.Point;
import flash.geom.Rectangle;
import flash.geom.Vector3D;
import mx.filters.BaseFilter;
import mx.geom.Transform;

public class DisplayObject extends EventDispatcher implements IBitmapDrawable {

	public DisplayObject root;

	public Stage stage = Stage.getInstance();

	public String name;

	public DisplayObjectContainer parent;

	public DisplayObject mask;

	public Boolean visible;

	public double x;

	public double y;

	public double z;

	public double scaleX;

	public double scaleY;

	public double scaleZ;

	public double mouseX;

	public double mouseY;

	public double rotation;

	public double rotationX;

	public double rotationY;

	public double rotationZ;

	public double alpha;

	public int width;

	public int height;

	public Boolean cacheAsBitmap;

	public Object opaqueBackground;

	public Rectangle scrollRect;

	public Vector<BaseFilter> filters;

	public String blendMode;

	public Transform transform;

	public Rectangle scale9Grid;
	public LoaderInfo LoaderInfo;
	public AccessibilityProperties AccessibilityProperties;
	public Matrix CacheAsBitmapMatrix;

	public Point globalToLocal(Point point) {
		return null;
	}

	public Point localToGlobal(Point point) {
		return null;
	}

	public Rectangle Bounds(DisplayObject tarCoordinateSpace) {
		return null;
	}

	public Rectangle Rect(DisplayObject tarCoordinateSpace) {
		return null;
	}

	public Boolean hitTestObject(DisplayObject obj) {
		return null;
	}

	public void hitTestPoint(double x, double y, Boolean shapeFlag) {
	}

	private Boolean _hitTest(Boolean use_xy, double x, double y, Boolean useShape, DisplayObject hitTestObject) {
		return null;
	}

	public void setAccessibilityProperties(AccessibilityProperties value) {

	}

	public Vector3D globalToLocal3D(flash.geom.Point point) {
		return null;
	}

	public Point local3DToGlobal(flash.geom.Vector3D point3d) {
		return null;
	}

	public void setBlendShader(Shader value) {

	}

	public void setCacheAsBitmapMatrix(Matrix value) {

	}

	protected Rectangle getRect(Stage stage) {
		return new Rectangle(0, 0, 0, 0);
	}

}
