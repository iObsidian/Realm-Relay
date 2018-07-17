package flash.airglobal;

import alde.flash.utils.Vector;
import flash.display.*;
import flash.geom.Matrix;
import flash.library.Array;

public class Graphics {

	public void clear() {
	}

	public void beginFill(double color, double alpha) {
	}

	public void beginGradientFill(String type, Array colors, Array alphas, Array ratios, Matrix matrix, String spreadMethod, String interpolationMethod, double focalPointRatio) {
	}

	public void beginBitmapFill(BitmapData bitmap, Matrix matrix, boolean repeat, boolean smooth) {
	}

	public void beginShaderFill(Shader shader, Matrix matrix) {
	}

	public void lineGradientStyle(String type, Array colors, Array alphas, Array ratios, Matrix matrix, String spreadMethod, String interpolationMethod, double focalPointRatio) {
	}

	public void lineStyle(double thickness, double color, double alpha, boolean pixelHinting, String scaleMode, String caps, String joints, double miterLimit) {
	}

	public void drawRect(double x, double y, double width, double height) {
	}

	public void drawRoundRect(double x, double y, double width, double height, double ellipseWidth, double ellipseHeight) {
	}

	public void drawRoundRectComplex(double x, double y, double width, double height, double topLeftRadius, double topRightRadius, double bottomLeftRadius, double bottomRightRadius) {
	}

	public void drawCircle(double x, double y, double radius) {
	}

	public void drawEllipse(double x, double y, double width, double height) {
	}

	public void moveTo(double x, double y) {
	}

	public void lineTo(double x, double y) {
	}

	public void curveTo(double controlX, double controlY, double anchorX, double anchorY) {
	}

	public void cubicCurveTo(double controlX1, double controlY1, double controlX2, double controlY2, double anchorX, double anchorY) {
	}

	public void endFill() {
	}

	public void copyFrom(Graphics sourceGraphics) {
	}

	public void lineBitmapStyle(BitmapData bitmap, Matrix matrix, boolean repeat, boolean smooth) {
	}

	public void lineShaderStyle(BitmapData shader, Matrix matrix) {
	}

	public void drawPath(Vector<Integer> commands, Vector<Double> data, String winding) {
	}

	public void drawTriangles(Vector<Double> vertices, Vector<Integer> indices, Vector<Double> uvtData, String culling) {
	}

	private void drawPathObject(IGraphicsPath path) {
	}

	private void beginFillObject(IGraphicsFill fill) {
	}

	private void beginStrokeObject(IGraphicsStroke istroke) {
	}

	public void drawGraphicsData(Vector<IGraphicsData> graphicsData) {

		System.out.println("Received graphics data");

	}

	public void lineStyle(int i, int lineColor) {
	}

	public void beginFill(int fillColor) {
	}

	public void lineStyle() {
	}

	public void lineStyle(int i, int i1, int i2, boolean b, String normal, String round, String round1) {
	}
}
