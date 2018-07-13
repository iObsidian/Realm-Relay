package rotmg.map;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
import flash.display.IGraphicsData;
import rotmg.engine3d.Face3D;
import rotmg.objects.Square;

public class SquareFace {

	public int animate;

	public Face3D face;

	public double xOffset = 0;

	public double yOffset = 0;

	public double animateDx = 0;

	public double animateDy = 0;

	public SquareFace(BitmapData param1, Vector<Double> param2, double param3, double param4, int param5, double param6, double param7) {
		super();
		this.face = new Face3D(param1, param2, Square.UVT.concat());
		this.xOffset = param3;
		this.yOffset = param4;
		if (this.xOffset != 0 || this.yOffset != 0) {
			this.face.bitmapFill.repeat = true;
		}
		this.animate = param5;
		if (this.animate != AnimateProperties.NO_ANIMATE) {
			this.face.bitmapFill.repeat = true;
		}
		this.animateDx = param6;
		this.animateDy = param7;
	}

	public void dispose() {
		this.face.dispose();
		this.face = null;
	}

	public boolean draw(Vector<IGraphicsData> param1, Camera param2, int param3) {
		double loc4 = 0;
		double loc5 = 0;
		if (this.animate != AnimateProperties.NO_ANIMATE) {
			switch (this.animate) {
				case AnimateProperties.WAVE_ANIMATE:
					loc4 = this.xOffset + Math.sin(this.animateDx * param3 / 1000);
					loc5 = this.yOffset + Math.sin(this.animateDy * param3 / 1000);
					break;
				case AnimateProperties.FLOW_ANIMATE:
					loc4 = this.xOffset + this.animateDx * param3 / 1000;
					loc5 = this.yOffset + this.animateDy * param3 / 1000;
			}
		} else {
			loc4 = this.xOffset;
			loc5 = this.yOffset;
		}
		/**if (Parameters.isGpuRender()) {
		 GraphicsFillExtra.setOffsetUV(this.face.bitmapFill, loc4, loc5);
		 loc4 = loc5 = 0;
		 }*/
		this.face.uvt.length = 0;
		this.face.uvt.add(0 + loc4, 0 + loc5, 0.0, 1 + loc4, 0.0 + loc5, 0.0, 1.0 + loc4, 1 + loc5, 0.0, 0.0 + loc4, 1 + loc5, 0.0);
		this.face.setUVT(this.face.uvt);
		return this.face.draw(param1, param2);
	}
}
