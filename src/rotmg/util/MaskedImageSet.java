package rotmg.util;

import flash.display.BitmapData;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a 100% match
 */
public class MaskedImageSet {

	public List<MaskedImage> images;

	public MaskedImageSet() {
		this.images = new ArrayList<MaskedImage>();
	}

	public void addFromBitmapData(BitmapData images, BitmapData masks, int width, int height) {
		ImageSet imageSet = new ImageSet();
		imageSet.addFromBitmapData(images, width, height);
		ImageSet masksSet = null;
		if (masks != null) {
			masksSet = new ImageSet();
			masksSet.addFromBitmapData(masks, width, height);
		}
		for (int i = 0; i < imageSet.images.size(); i++) {
			this.images.add(new MaskedImage(imageSet.images.get(i),
					masksSet == null ? null : i >= masksSet.images.size() ? null : masksSet.images.get(i)));
		}

	}

	public void addFromMaskedImage(MaskedImage maskedImage, int width, int height) {
		this.addFromBitmapData(maskedImage.image, maskedImage.mask, width, height);
	}

}
