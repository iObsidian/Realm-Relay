package rotmg.engine3d;

import alde.flash.utils.Vector;
import flash.display.*;
import flash.geom.Utils3D;
import flash.geom.Vector3D;
import rotmg.map.Camera;
import rotmg.util.GraphicsUtil;
import rotmg.util.TextureRedrawer;

public class Face3D {

	private static final GraphicsSolidFill blackOutFill = new GraphicsSolidFill(0, 1);

	public BitmapData origTexture;

	public Vector<Double> vin;

	public Vector<Double> uvt;

	public Vector<Double> vout;

	public boolean backfaceCull;

	public double shade = 1.0;

	public boolean blackOut = false;
	public GraphicsBitmapFill bitmapFill;
	private boolean needGen = true;
	private TextureMatrix textureMatrix = null;
	private GraphicsPath path;

	public Face3D(BitmapData param1, Vector<Double> param2, Vector<Double> param3) {
		this(param1, param2, param3, false, false);
	}

	public Face3D(BitmapData param1, Vector<Double> param2, Vector<Double> param3, boolean param4, boolean param5) {
		Vector3D loc7 = null;
		this.vout = new Vector<Double>();
		this.bitmapFill = new GraphicsBitmapFill(null, null, false, false);
		this.path = new GraphicsPath(new Vector<Integer>(), null);
		this.origTexture = param1;
		this.vin = param2;
		this.uvt = param3;
		this.backfaceCull = param4;
		if (param5) {
			loc7 = new Vector3D();
			Plane3D.computeNormalVec(param2, loc7);
			this.shade = Lighting3D.shadeValue(loc7, 0.75);
		}
		this.path.commands.add(GraphicsPathCommand.MOVE_TO);
		int loc6 = 3;
		while (loc6 < this.vin.length) {
			this.path.commands.add(GraphicsPathCommand.LINE_TO);
			loc6 = loc6 + 3;
		}
		this.path.data = this.vout;
	}

	public void dispose() {
		this.origTexture = null;
		this.vin = null;
		this.uvt = null;
		this.vout = null;
		this.textureMatrix = null;
		this.bitmapFill = null;
		this.path.commands = null;
		this.path.data = null;
		this.path = null;
	}

	public void setTexture(BitmapData param1) {
		if (this.origTexture == param1) {
			return;
		}
		this.origTexture = param1;
		this.needGen = true;
	}

	public void setUVT(Vector<Double> param1) {
		this.uvt = param1;
		this.needGen = true;
	}

	public double maxY() {
		double loc1 = -Double.MAX_VALUE;
		int loc2 = this.vout.length;
		int loc3 = 0;
		while (loc3 < loc2) {
			if (this.vout.get(loc3 + 1) > loc1) {
				loc1 = this.vout.get(loc3 + 1);
			}
			loc3 = loc3 + 2;
		}
		return loc1;
	}

	public boolean draw(Vector<IGraphicsData> param1, Camera param2) {
		Vector<Double> loc10 = null;
		double loc11 = 0;
		double loc12 = 0;
		double loc13 = 0;
		double loc14 = 0;
		int loc15 = 0;
		Utils3D.projectVectors(param2.wToS, this.vin, this.vout, this.uvt);
		if (this.backfaceCull) {
			loc10 = this.vout;
			loc11 = loc10.get(2) - loc10.get(0);
			loc12 = loc10.get(3) - loc10.get(1);
			loc13 = loc10.get(4) - loc10.get(0);
			loc14 = loc10.get(5) - loc10.get(1);
			if (loc11 * loc14 - loc12 * loc13 > 0) {
				return false;
			}
		}
		double loc3 = param2.clipRect.x - 10;
		double loc4 = param2.clipRect.y - 10;
		double loc5 = param2.clipRect.right + 10;
		double loc6 = param2.clipRect.bottom + 10;
		boolean loc7 = true;
		int loc8 = this.vout.length;
		int loc9 = 0;
		while (loc9 < loc8) {
			loc15 = loc9 + 1;
			if (this.vout.get(loc9) >= loc3 && this.vout.get(loc9) <= loc5 && this.vout.get(loc15) >= loc4 && this.vout.get(loc15) <= loc6) {
				loc7 = false;
				break;
			}
			loc9 = loc9 + 2;
		}
		if (loc7) {
			return false;
		}
		if (this.blackOut) {
			param1.add(blackOutFill);
			param1.add(this.path);
			param1.add(GraphicsUtil.END_FILL);
			return true;
		}
		if (this.needGen) {
			this.generateTextureMatrix();
		}
		this.textureMatrix.calculateTextureMatrix(this.vout);
		this.bitmapFill.bitmapData = this.textureMatrix.texture;
		this.bitmapFill.matrix = this.textureMatrix.tToS;
		param1.add(this.bitmapFill);
		param1.add(this.path);
		param1.add(GraphicsUtil.END_FILL);
		return true;
	}

	public boolean contains(double param1, double param2) {
		if (Triangle.containsXY(this.vout.get(0), this.vout.get(1), this.vout.get(2), this.vout.get(3), this.vout.get(4), this.vout.get(5), param1, param2)) {
			return true;
		}
		if (this.vout.length == 8 && Triangle.containsXY(this.vout.get(0), this.vout.get(1), this.vout.get(4), this.vout.get(5), this.vout.get(6), this.vout.get(7), param1, param2)) {
			return true;
		}
		return false;
	}

	private void generateTextureMatrix() {
		BitmapData loc1 = TextureRedrawer.redrawFace(this.origTexture, this.shade);
		if (this.textureMatrix == null) {
			this.textureMatrix = new TextureMatrix(loc1, this.uvt);
		} else {
			this.textureMatrix.texture = loc1;
			this.textureMatrix.calculateUVMatrix(this.uvt);
		}
		this.needGen = false;
	}

}
