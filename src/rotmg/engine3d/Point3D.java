package rotmg.engine3d;

import alde.flash.utils.Vector;
import flash.display.*;
import flash.geom.Matrix;
import flash.geom.Matrix3D;
import flash.geom.Utils3D;
import flash.geom.Vector3D;
import rotmg.map.Camera;
import rotmg.util.Trig;

public class Point3D {

	private static final Vector<Integer> commands = new Vector<Integer>(GraphicsPathCommand.MOVE_TO, GraphicsPathCommand.LINE_TO, GraphicsPathCommand.LINE_TO, GraphicsPathCommand.LINE_TO);

	private static final GraphicsEndFill END_FILL = new GraphicsEndFill();
	private final Vector<Double> data = new Vector<Double>();
	private final GraphicsPath path = new GraphicsPath(commands, this.data);
	private final GraphicsBitmapFill bitmapFill = new GraphicsBitmapFill(null, new Matrix(), false, false);
	private final GraphicsSolidFill solidFill = new GraphicsSolidFill(0, 1);
	public double size;
	public Vector3D posS;

	public Point3D(double param1) {
		super();
		this.size = param1;
	}

	public void setSize(double param1) {
		this.size = param1;
	}


	public void draw(Vector<IGraphicsData> param1, Vector3D param2, double param3, Matrix3D param4, Camera param5, BitmapData param6) {
		draw(param1, param2, param3, param4, param5, param6, 0);
	}

	public void draw(Vector<IGraphicsData> param1, Vector3D param2, double param3, Matrix3D param4, Camera param5, BitmapData param6, int param7) {
		double loc10 = 0;
		double loc11 = 0;
		Matrix loc12 = null;
		this.posS = Utils3D.projectVector(param4, param2);
		if (this.posS.w < 0) {
			return;
		}
		double loc8 = this.posS.w * Math.sin(param5.pp.fieldOfView / 2 * Trig.toRadians);
		double loc9 = this.size / loc8;
		this.data.length = 0;
		if (param3 == 0) {
			this.data.add(this.posS.x - loc9, this.posS.y - loc9, this.posS.x + loc9, this.posS.y - loc9, this.posS.x + loc9, this.posS.y + loc9, this.posS.x - loc9, this.posS.y + loc9);
		} else {
			loc10 = Math.cos(param3);
			loc11 = Math.sin(param3);
			this.data.add(this.posS.x + (loc10 * -loc9 + loc11 * -loc9), this.posS.y + (loc11 * -loc9 - loc10 * -loc9), this.posS.x + (loc10 * loc9 + loc11 * -loc9), this.posS.y + (loc11 * loc9 - loc10 * -loc9), this.posS.x + (loc10 * loc9 + loc11 * loc9), this.posS.y + (loc11 * loc9 - loc10 * loc9), this.posS.x + (loc10 * -loc9 + loc11 * loc9), this.posS.y + (loc11 * -loc9 - loc10 * loc9));
		}
		if (param6 != null) {
			this.bitmapFill.bitmapData = param6;
			loc12 = this.bitmapFill.matrix;
			loc12.identity();
			loc12.scale(2 * loc9 / param6.width, 2 * loc9 / param6.height);
			loc12.translate(-loc9, -loc9);
			loc12.rotate(param3);
			loc12.translate(this.posS.x, this.posS.y);
			param1.add(this.bitmapFill);
		} else {
			this.solidFill.color = param7;
			param1.add(this.solidFill);
		}
		param1.add(this.path);
		param1.add(END_FILL);
	}

}
