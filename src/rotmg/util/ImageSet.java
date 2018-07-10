package rotmg.util;

import flash.display.BitmapData;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a 100% match
 */
public class ImageSet {

	public List<BitmapData> images;

	public ImageSet() {
		this.images = new ArrayList<BitmapData>();
	}

	public void add(BitmapData image) {
		this.images.add(image);
	}

	public BitmapData random() {
		return this.images.get((int) (Math.random() * this.images.size()));
	}

	public void addFromBitmapData(BitmapData bitmapData, int width, int height) {
		int maxX = bitmapData.width / width;
		int maxY = bitmapData.height / height;

		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				images.add(BitmapUtil.cropToBitmapData(bitmapData, x * width, y * height, width, height));
			}
		}
	}

}
