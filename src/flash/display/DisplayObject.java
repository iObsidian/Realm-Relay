package flash.display;

import com.sun.prism.ps.Shader;
import flash.events.EventDispatcher;
import flash.geom.Matrix;
import flash.geom.Point;
import flash.geom.Rectangle;
import flash.geom.Vector3D;
import javafx.stage.Stage;

public class DisplayObject extends EventDispatcher implements IBitmapDrawable {

	native public DisplayObject getRoot();

	native public Stage getStage();

	native public String getName();

	native public void setName(String value);

	native public DisplayObjectContainer getParent();

	native public DisplayObject getMask();

	native public void setMask(DisplayObject value);

	native public Boolean getVisible();

	native public void setVisible(Boolean value);

	native public Number getX();

	native public void setX(Number value);

	native public Number getY();

	native public void setY(Number value);

	native public Number getZ();

	native public void setZ(Number value);

	native public Number getScaleX();

	native public void setScaleX(Number value);

	native public Number getScaleY();

	native public void setScaleY(Number value);

	native public Number getScaleZ();

	native public void setScaleZ(Number value);

	native public Number getMouseX();

	native public Number getMouseY();

	native public Number getRotation();

	native public void setRotation(Number value);

	native public Number getRotationX();

	native public void setRotationX(Number value);

	native public Number getRotationY();

	native public void setRotationY(Number value);

	native public Number getRotationZ();

	native public void setRotationZ(Number value);

	native public Number getAlpha();

	native public void setAlpha(Number value);

	native public Number getWidth();

	native public void setWidth(Number value);

	native public Number getHeight();

	native public void setHeight(Number value);

	native public Boolean getCacheAsBitmap();

	native public void setCacheAsBitmap(Boolean value);

	native public Object getOpaqueBackground();

	native public void setOpaqueBackground(Object value);

	native public Rectangle getScrollRect();

	native public void setScrollRect(Rectangle value);

	native public Array getFilters();

	native public void setFilters(Array value);

	native public String getBlendMode();

	native public void setBlendMode(String value);

	native public Transform getTransform();

	native public void setTransform(Transform value);

	native public Rectangle getScale9Grid();

	native public void setScale9Grid(Rectangle innerRectangle);

	native public Point globalToLocal(Point point);

	native public Point localToGlobal(Point point);

	native public Rectangle getBounds(DisplayObject targetCoordinateSpace);

	native public Rectangle getRect(DisplayObject targetCoordinateSpace);

	native public LoaderInfo getLoaderInfo();

	native public Boolean hitTestObject(DisplayObject obj);

	native public function hitTestPoint(Number x, Number y, Boolean shapeFlag);

	native private Boolean _hitTest(Boolean use_xy, Number x, Number y, Boolean useShape, DisplayObject hitTestObject);

	native public AccessibilityProperties getAccessibilityProperties();

	native public void setAccessibilityProperties(AccessibilityProperties value);

	native public Vector3D globalToLocal3D(flash.geom.Point point);

	native public Point local3DToGlobal(flash.geom.Vector3D point3d);

	native public void setBlendShader(Shader value);

	native public Matrix getCacheAsBitmapMatrix();

	native public void setCacheAsBitmapMatrix(Matrix value);

}
