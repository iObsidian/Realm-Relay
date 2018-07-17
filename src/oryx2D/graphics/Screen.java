package oryx2D.graphics;

import flash.display.BitmapData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Screen {

	public final int MAP_SIZE = 128;

	public final int width, height;
	public int xOffset, yOffset;

	private BufferedImage image;
	private int[] pixels;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //Image (game view)
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Get data buffer of the image to write pixels to it
	}

	/*
	 * Sets all pixels to black
	 */
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void render(int xp, int yp, BitmapData BitmapData) {
		this.render(xp, yp, BitmapData, 0);
	}

	/**
	 * @param xp x position
	 * @param yp y position
	 * @param BitmapData to draw
	 * @param flip flip
	 */
	public void render(int xp, int yp, BitmapData BitmapData, int flip) {
		xp -= xOffset;
		yp -= yOffset;

		for (int y = 0; y < BitmapData.height; y++) {
			int ya = y + yp;
			int ys = y;

			if (flip == 2 || flip == 3)
				ys = BitmapData.height - 1 - y;

			for (int x = 0; x < BitmapData.width; x++) {
				int xa = x + xp;
				int xs = x;

				if (flip == 1 || flip == 3)
					xs = BitmapData.width - 1 - x;

				// Check if BitmapData is outside of the screen
				if (xa < -BitmapData.width || xa >= this.width || ya < 0 || ya >= this.height) {
					break;
				}

				if (xa < 0)
					xa = 0;

				int pixelColor = BitmapData.pixels[xs + ys * BitmapData.width];

				if (pixelColor != 0 && pixelColor != 0xFFFF00FF) { //PINK or transparent
					pixels[xa + ya * this.width] = pixelColor;
				}
			}
		}
	}

	public void drawPixels(Graphics g, int width, int height) {
		g.drawImage(image, 0, 0, width, height, null); //Draw the image
	}

}
