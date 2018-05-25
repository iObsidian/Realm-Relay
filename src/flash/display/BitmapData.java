package flash.display;

import java.awt.image.BufferedImage;

import flash.geom.Matrix;
import flash.geom.Point;
import flash.geom.Rectangle;

public class BitmapData {

	public int width() {
		return image.getWidth();
	}

	public int height() {
		return image.getHeight();
	}

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

	public BitmapData clone() {
		return new BitmapData(image);
	}

	/**
	 * From AS3 API :
	 * 
	 * This method copies a rectangular area of a source image to a rectangular 
	 * area of the same size at the destination point of the destination BitmapData object. 
	 * 
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
}
