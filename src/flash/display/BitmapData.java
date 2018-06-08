package flash.display;

import flash.geom.ColorTransform;
import flash.geom.Matrix;
import flash.geom.Point;
import flash.geom.Rectangle;
import spark.filters.GlowFilter;

import java.awt.image.BufferedImage;

public class BitmapData {

	public Object rect;

	public int width;
	public int height;

	public BufferedImage image;

	public BitmapData(BufferedImage b) {
		image = b;
	}

	/**
	 * Used by BitmapDataSpy
	 */
	public BitmapData(int param1, int param2, boolean param3, int param4) {
		this.image = new BufferedImage(param1, param2, 1);
	}

	public BitmapData(int i, int i1) {
	}

	public BitmapData(double width, double height, boolean param3, int param4) {

	}

	public BitmapData(int i, int i1, boolean b, double v) {

	}

	public BitmapData clone() {
		return new BitmapData(image);
	}

	/**
	 * From AS3 API :
	 * <p>
	 * This method copies a rectangular area of a source image to a rectangular
	 * area of the same size at the destination point of the destination BitmapData object.
	 * <p>
	 * This method is not implemented as it should. TODO
	 */
	public void copyPixels(BitmapData sourceImage, Rectangle rectangle, Point point) {
		this.image = sourceImage.image;
	}

	public void dispose() {
		image = null;
	}

	public void draw(BitmapData param1, Matrix loc4, Object o, Object o1, Object o2, boolean b) {
	}

	public void draw(BitmapData nameText, Object o) {
	}

	public void setPixel(int i, int i1, double loc5) {
	}

	public void applyFilter(BitmapData loc4, Object rect, Point origin, GlowFilter outlineFilter) {
	}

	public void colorTransform(Object rect, ColorTransform param2) {
	}

	public int getPixel32(int loc3, int loc4) {
		return 0;
	}

	public void copyPixels(BitmapData bitmapData, Rectangle param2, Point param3, BitmapData random, Point p0, boolean b) {
	}

	public void draw(DisplayObject gradient, Matrix tempMatrix, Object o, String subtract) {
	}
}
