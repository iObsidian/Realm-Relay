package rotmg.objects.particles;

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
import rotmg.util.TextureRedrawer;

public class Particle extends BasicObject {

	public int size;

	public int color;

	protected GraphicsBitmapFill bitmapFill;

	protected GraphicsPath path;

	protected Vector<Double> vS;

	protected Matrix fillMatrix;

	public Particle(int param1, double param2, int param3) {
		super();
		this.bitmapFill = new GraphicsBitmapFill(null, null, false, false);
		this.path = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, null);
		this.vS = new Vector<Double>();
		this.fillMatrix = new Matrix();
		objectId = getNextFakeObjectId();
		this.setZ(param2);
		this.setColor(param1);
		this.setSize(param3);
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

	public boolean moveToInModal(double param1, double param2) {
		x = param1;
		y = param2;
		return true;
	}

	public void setColor(int param1) {
		this.color = param1;
	}

	public void setZ(double param1) {
		z = param1;
	}

	public void setSize(int param1) {
		this.size = param1 / 100 * 5;
	}

	public void draw(Vector<IGraphicsData> param1, Camera param2, int param3) {
		BitmapData loc4 = TextureRedrawer.redrawSolidSquare(this.color, this.size);
		int loc5 = loc4.width;
		int loc6 = loc4.height;
		this.vS.length = 0;
		this.vS.add(posS.get(3) - loc5 / 2, posS.get(4) - loc6 / 2, posS.get(3) + loc5 / 2, posS.get(4) - loc6 / 2, posS.get(3) + loc5 / 2, posS.get(4) + loc6 / 2, posS.get(3) - loc5 / 2, posS.get(4) + loc6 / 2);
		this.path.data = this.vS;
		this.bitmapFill.bitmapData = loc4;
		this.fillMatrix.identity();
		this.fillMatrix.translate(this.vS.get(0), this.vS.get(1));
		this.bitmapFill.matrix = this.fillMatrix;
		param1.add(this.bitmapFill);
		param1.add(this.path);
		param1.add(GraphicsUtil.END_FILL);
	}


}
