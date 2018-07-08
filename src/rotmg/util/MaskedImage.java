package rotmg.util;

import flash.display.BitmapData;

/**
 * Still needs mirror() and amountTransparent() methods
 */

public class MaskedImage {

	public BitmapData image;
	public BitmapData mask;

	public MaskedImage(BitmapData image, BitmapData mask) {
		super();
		this.image = image;
		this.mask = mask;
	}

	public int width() {
		return this.image.width;
	}

	public int height() {
		return this.image.height;
	}


	public MaskedImage mirror() {
		return mirror(0);
	}

	public MaskedImage mirror(int width) {
		BitmapData mirroredImage = BitmapUtil.mirror(this.image, width);
		BitmapData mirroredMask = this.mask == null ? null : BitmapUtil.mirror(this.mask, width);
		return new MaskedImage(mirroredImage, mirroredMask);
	}


	// mirror()

	// amountTransparent()


}
