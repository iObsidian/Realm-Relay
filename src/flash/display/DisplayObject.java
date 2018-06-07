package flash.display;

import com.sun.prism.ps.Shader;
import flash.accessibility.AccessibilityProperties;
import flash.events.EventDispatcher;
import flash.geom.Matrix;
import flash.geom.Point;
import flash.geom.Rectangle;
import flash.geom.Vector3D;
import mx.geom.Transform;

public class DisplayObject extends EventDispatcher implements IBitmapDrawable {

	public DisplayObject root;

	public Stage stage;

	public String name;

	public DisplayObjectContainer parent;

	public DisplayObject mask;

	public Boolean visible;

	public Number x;

	public Number y;

	public Number z;

	public Number scaleX;

	public Number scaleY;

	public Number scaleZ;

	public Number mouseX;

	public Number mouseY;

	public Number rotation;

	public Number rotationX;

	public Number rotationY;

	public Number rotationZ;

	public Number alpha;

	public Number width;

	public Number height;

	public Boolean cacheAsBitmap;

	public Object opaqueBackground;

	public Rectangle scrollRect;

	public Array filters;

	public String blendMode;

	public Transform transform;

	public Rectangle scale9Grid;

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

	public LoaderInfo LoaderInfo;

	public Boolean hitTestObject(DisplayObject obj) {
		return null;
	}

	public void hitTestPoint(Number x, Number y, Boolean shapeFlag) {
	}

	private Boolean _hitTest(Boolean use_xy, Number x, Number y, Boolean useShape, DisplayObject hitTestObject) {
		return null;
	}

	public AccessibilityProperties AccessibilityProperties;

	public void setAccessibilityProperties(AccessibilityProperties value) {
		return;
	}

	public Vector3D globalToLocal3D(flash.geom.Point point) {
		return null;
	}

	public Point local3DToGlobal(flash.geom.Vector3D point3d) {
		return null;
	}

	public void setBlendShader(Shader value) {
		return;
	}

	public Matrix CacheAsBitmapMatrix;

	public void setCacheAsBitmapMatrix(Matrix value) {
		return;
	}

}
