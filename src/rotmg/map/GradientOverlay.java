package rotmg.map;

import alde.flash.utils.Vector;
import flash.airglobal.Shape;
import flash.display.GradientType;
import flash.display.GraphicsGradientFill;
import flash.display.GraphicsPath;
import flash.display.IGraphicsData;
import rotmg.util.GraphicsUtil;

public class GradientOverlay extends Shape {

	private final GraphicsGradientFill gradientFill = new GraphicsGradientFill(GradientType.LINEAR, new Vector<Integer>(0, 0), new Vector<Double>(0.0, 1.0), new Vector<Integer>(0, 255), GraphicsUtil.getGradientMatrix(10, 600));

	private final GraphicsPath gradientPath = GraphicsUtil.getRectPath(0, 0, 10, 600);

	private Vector<IGraphicsData> gradientGraphicsData;

	public GradientOverlay() {
		super();
		gradientGraphicsData = new Vector<IGraphicsData>(this.gradientFill, this.gradientPath, GraphicsUtil.END_FILL);
		graphics.drawGraphicsData(this.gradientGraphicsData);
		visible = false;
	}

}
