package rotmg.map;

import alde.flash.utils.Vector;
import flash.airglobal.Shape;
import flash.display.IGraphicsData;

public class HurtOverlay extends Shape {

	private final double s = 600 / Math.sin(Math.PI / 4);

	//private final GraphicsGradientFill gradientFill = new GraphicsGradientFill(GradientType.RADIAL,[16777215, 16777215,16777215],[0,0,0.92],[0,155,255],GraphicsUtil.getGradientMatrix(this.s,this.s,0,(600-this.s)/2,(600-this.s)/2));

	//private final GraphicsPath gradientPath = GraphicsUtil.getRectPath(0, 0, 600, 600);

	private Vector<IGraphicsData> gradientGraphicsData;

	public HurtOverlay() {
		/**gradientGraphicsData = new IGraphicsData[]{this.gradientFill, this.gradientPath, GraphicsUtil.END_FILL};
		 super();
		 graphics.drawGraphicsData(this.gradientGraphicsData);
		 visible = false;*/
	}

}
