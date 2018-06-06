package flash.display;

import com.sun.prism.ps.Shader;
import flash.geom.Matrix;
import flash.geom.Point;
import flash.geom.Rectangle;
import flash.geom.Vector3D;
import javafx.event.EventDispatcher;
import javafx.stage.Stage;

public interface DisplayObject extends EventDispatcher {

	public DisplayObject getRoot();

	public Stage getStage();

	public String getName();

	public void setName(String value);

	public DisplayObjectContainer getParent();

	public DisplayObject getMask();

	public void setMask(DisplayObject value);

	public Boolean getVisible();

	public void setVisible(Boolean value);

	public Number getX();

	public void setX(Number value);

	public Number getY();

	public void setY(Number value);

	public Number getZ();

	public void setZ(Number value);

	public Number getScaleX();

	public void setScaleX(Number value);

	public Number getScaleY();

	public void setScaleY(Number value);

	public Number getScaleZ();

	public void setScaleZ(Number value);

	public Number getMouseX();

	public Number getMouseY();

	public Number getRotation();

	public void setRotation(Number value);

	public Number getRotationX();

	public void setRotationX(Number value);

	public Number getRotationY();

	public void setRotationY(Number value);

	public Number getRotationZ();

	public void setRotationZ(Number value);

	public Number getAlpha();

	public void setAlpha(Number value);

	public Number getWidth();

	public void setWidth(Number value);

	public Number getHeight();

	public void setHeight(Number value);

	public Boolean getCacheAsBitmap();

	public void setCacheAsBitmap(Boolean value);

	public Object getOpaqueBackground();

	public void setOpaqueBackground(Object value);

	public Rectangle getScrollRect();

	public void setScrollRect(Rectangle value);

	public Array getFilters();

	public void setFilters(Array value);

	public String getBlendMode();

	public void setBlendMode(String value);

	public Transform getTransform();

	public void setTransform(Transform value);

	public Rectangle getScale9Grid();

	public void setScale9Grid(Rectangle innerRectangle);

	public Point globalToLocal(Point point);

	public Point localToGlobal(Point point);

	public Rectangle getBounds(DisplayObject targetCoordinateSpace);

	public Rectangle getRect(DisplayObject targetCoordinateSpace);

	public LoaderInfo getLoaderInfo();

	public Boolean hitTestObject(DisplayObject obj);

	public function hitTestPoint(Number x, Number y, Boolean shapeFlag);

	private Boolean _hitTest(Boolean use_xy, Number x, Number y, Boolean useShape, DisplayObject hitTestObject);

	public AccessibilityProperties getAccessibilityProperties();

	public void setAccessibilityProperties(AccessibilityProperties value);

	public Vector3D globalToLocal3D(flash.geom.Point point);

	public Point local3DToGlobal(flash.geom.Vector3D point3d);

	public void setBlendShader(Shader value);

	public Matrix getCacheAsBitmapMatrix();

	public void setCacheAsBitmapMatrix(Matrix value);


}
