package rotmg.util;

import alde.flash.utils.Vector;
import flash.display.GraphicsEndFill;
import flash.display.GraphicsPath;
import flash.display.GraphicsPathCommand;

/**
 * About 10% done
 */
public class GraphicsUtil {

	private static final double TWO_PI = 2 * Math.PI;

	public static final GraphicsEndFill END_FILL = new GraphicsEndFill();

	public static Vector<Integer> QUAD_COMMANDS = new Vector(GraphicsPathCommand.MOVE_TO, GraphicsPathCommand.LINE_TO, GraphicsPathCommand.LINE_TO, GraphicsPathCommand.LINE_TO);

	public void drawRect(int x, int y, int width, int height, GraphicsPath path) {
		path.moveTo(x, y);
		path.lineTo(x + width, y);
		path.lineTo(x + width, y + height);
		path.lineTo(x, y + height);
	}

	public static void drawCircle(double centerX, double centerY, double radius, GraphicsPath path, int numPoints) {
		double th = 0;
		double thm = 0;
		double px = 0;
		double py = 0;
		double hx = 0;
		double hy = 0;
		double curve = 1 + 1 / (numPoints * 1.75);
		path.moveTo(centerX + radius, centerY);
		for (int i = 1; i <= numPoints; i++) {
			th = TWO_PI * i / numPoints;
			thm = TWO_PI * (i - 0.5) / numPoints;
			px = centerX + radius * Math.cos(th);
			py = centerY + radius * Math.sin(th);
			hx = centerX + radius * curve * Math.cos(thm);
			hy = centerY + radius * curve * Math.sin(thm);
			path.curveTo(hx, hy, px, py);
		}
	}

	public void drawDiamond(double x, double y, double radius, GraphicsPath path) {
		path.moveTo(x, y - radius);
		path.lineTo(x + radius, y);
		path.lineTo(x, y + radius);
		path.lineTo(x - radius, y);
	}
}
