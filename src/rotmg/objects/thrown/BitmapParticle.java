package rotmg.objects.thrown;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
import flash.display.GraphicsBitmapFill;
import flash.display.GraphicsPath;
import flash.display.IGraphicsData;
import flash.geom.Matrix;
import rotmg.map.Camera;
import rotmg.objects.BasicObject;
import rotmg.objects.Square;
import rotmg.util.GraphicsUtil;

public class BitmapParticle extends BasicObject {

	public int size;
	public BitmapData _bitmapData;
	public double _rotation = 0;
	protected GraphicsBitmapFill bitmapFill;
	protected GraphicsPath path;
	protected Vector<Double> vS;
	protected Matrix fillMatrix;
	protected double _rotationDelta = 0;

	public BitmapParticle(BitmapData param1, double param2) {
		super();
		this.bitmapFill = new GraphicsBitmapFill(null, null, false, false);
		this.path = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, null);
		this.vS = new Vector<Double>();
		this.fillMatrix = new Matrix();
		hasShadow = false;
		objectId = getNextFakeObjectId();
		this._bitmapData = param1;
		z = param2;
	}

	public boolean moveTo(double param1, double param2) {
		Square loc3 = null;
		loc3 = map.getSquare(param1, param2);
		if (loc3 == null) {
			return false;
		}
		x = param1;
		y = param2;
		square = loc3;
		return true;
	}

	public void setSize(int param1) {
		this.size = param1 / 100 * 5;
	}

	public void drawShadow(Vector<IGraphicsData> param1, Camera param2, int param3) {
	}

	public void draw(Vector<IGraphicsData> param1, Camera param2, int param3) {
		BitmapData texture = null;
		int w = 0;
		int h = 0;
		Vector<IGraphicsData> graphicsData = param1;
		Camera camera = param2;
		int time = param3;
		try {
			texture = this._bitmapData;
			w = texture.width;
			h = texture.height;
			if (w == 0 || h == 0) {
				return;
			}
			this.vS.length = 0;
			this.vS.add(posS.get(3) - w / 2, posS.get(4) - h / 2, posS.get(3) + w / 2, posS.get(4) - h / 2, posS.get(3) + w / 2, posS.get(4) + h / 2, posS.get(3) - w / 2, posS.get(4) + h / 2);
			this.path.data = this.vS;
			this.bitmapFill.bitmapData = texture;
			this.fillMatrix.identity();
			if (this._rotation != 0 || this._rotationDelta != 0) {
				if (this._rotationDelta != 0) {
					this._rotation = this._rotation + this._rotationDelta;
				}
				this.fillMatrix.translate(-w / 2, -h / 2);
				this.fillMatrix.rotate(this._rotation);
				this.fillMatrix.translate(w / 2, h / 2);
			}
			this.fillMatrix.translate(this.vS.get(0), this.vS.get(1));
			this.bitmapFill.matrix = this.fillMatrix;
			graphicsData.add(this.bitmapFill);
			graphicsData.add(this.bitmapFill);
			graphicsData.add(this.path);
			graphicsData.add(GraphicsUtil.END_FILL);
			return;
		} catch (Error error) {
			return;
		}
	}


}
