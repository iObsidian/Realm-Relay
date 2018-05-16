package realmrelay.game._as3;

import java.awt.*;
import java.awt.Point;
import java.awt.image.BufferedImage;

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
}
