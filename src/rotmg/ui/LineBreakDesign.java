package rotmg.ui;

import alde.flash.utils.Vector;
import flash.airglobal.Shape;
import flash.display.GraphicsPath;
import flash.display.GraphicsPathWinding;
import flash.display.GraphicsSolidFill;
import flash.display.IGraphicsData;
import rotmg.util.GraphicsUtil;

public class LineBreakDesign extends Shape {

	private GraphicsSolidFill designFill;

	private GraphicsPath designPath;

	private Vector<IGraphicsData> designGraphicsData;

	public LineBreakDesign(int param1, int param2) {
		super();
		this.designFill = new GraphicsSolidFill(16777215, 1);
		this.designPath = new GraphicsPath(new Vector<Integer>(), new Vector<Double>(), GraphicsPathWinding.NON_ZERO);

		designGraphicsData = new Vector<>(this.designFill, this.designPath, GraphicsUtil.END_FILL);
		this.setWidthColor(param1, param2);
	}

	public void setWidthColor(int param1, int param2) {
		graphics.clear();
		this.designFill.color = param2;
		GraphicsUtil.clearPath(this.designPath);
		GraphicsUtil.drawDiamond(0, 0, 4, this.designPath);
		GraphicsUtil.drawDiamond(param1, 0, 4, this.designPath);
		GraphicsUtil.drawRect(0, -1, param1, 2, this.designPath);
		graphics.drawGraphicsData(this.designGraphicsData);
	}


}
