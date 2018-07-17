package rotmg.engine3d;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
import flash.geom.Matrix;

/**
 * This is a 100% match
 */
public class TextureMatrix {

	public BitmapData texture = null;

	public Matrix tToS;

	private Matrix uvMatrix = null;

	private Matrix tempMatrix;

	public TextureMatrix(BitmapData param1, Vector<Double> param2) {
		super();
		this.tToS = new Matrix();
		this.tempMatrix = new Matrix();
		this.texture = param1;
		this.calculateUVMatrix(param2);
	}

	public void setUVT(Vector<Double> param1) {
		this.calculateUVMatrix(param1);
	}

	public void setVOut(Vector<Double> param1) {
		this.calculateTextureMatrix(param1);
	}

	public void calculateTextureMatrix(Vector<Double> param1) {
		this.tToS.a = this.uvMatrix.a;
		this.tToS.b = this.uvMatrix.b;
		this.tToS.c = this.uvMatrix.c;
		this.tToS.d = this.uvMatrix.d;
		this.tToS.tx = this.uvMatrix.tx;
		this.tToS.ty = this.uvMatrix.ty;
		int loc2 = param1.length - 2;
		int loc3 = loc2 + 1;
		this.tempMatrix.a = param1.get(2) - param1.get(0);
		this.tempMatrix.b = param1.get(3) - param1.get(1);
		this.tempMatrix.c = param1.get(loc2) - param1.get(0);
		this.tempMatrix.d = param1.get(loc3) - param1.get(1);
		this.tempMatrix.tx = param1.get(0);
		this.tempMatrix.ty = param1.get(1);
		this.tToS.concat(this.tempMatrix);
	}

	public void calculateUVMatrix(Vector<Double> param1) {
		/*if (this.texture == null) {
			this.uvMatrix = null;
			return;
		}
		int loc2 = param1.length - 3;
		double loc3 = param1.get(0) * this.texture.width;
		double loc4 = param1.get(1) * this.texture.height;
		double loc5 = param1.get(3) * this.texture.width;
		double loc6 = param1.get(4) * this.texture.height;
		double loc7 = param1.get(loc2) * this.texture.width;
		double loc8 = param1.get(loc2 + 1) * this.texture.height;
		double loc9 = loc5 - loc3;
		double loc10 = loc6 - loc4;
		double loc11 = loc7 - loc3;
		double loc12 = loc8 - loc4;
		this.uvMatrix = new Matrix(loc9, loc10, loc11, loc12, loc3, loc4);
		this.uvMatrix.invert();*/
	}


}
