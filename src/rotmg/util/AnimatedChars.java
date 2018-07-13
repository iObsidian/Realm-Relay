package rotmg.util;

import alde.flash.utils.StaticAssetLoaderUtility;
import flash.display.BitmapData;
import rotmg.objects.animation.AnimatedChar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnimatedChars {

	private static HashMap<String, List<AnimatedChar>> nameMap = new HashMap<>();

	public static AnimatedChar getAnimatedChar(String name, int id) {
		List<AnimatedChar> chars = nameMap.get(name);
		if (chars == null || id >= chars.size()) {
			System.err.println("Null texture");
			return null;
		}
		return chars.get(id);
	}

	public static void add(String name, BitmapData images, BitmapData masks, int charWidth, int charHeight,
	                       int sheetWidth, int sheetHeight, int firstDir) {
		List<AnimatedChar> chars = new ArrayList<AnimatedChar>();
		MaskedImageSet charImages = new MaskedImageSet();

		//System.out.println(name);

		charImages.addFromBitmapData(images, masks, sheetWidth, sheetHeight);
		for (MaskedImage image : charImages.images) {
			chars.add(new AnimatedChar(image, charWidth, charHeight, firstDir));
		}
		nameMap.put(name, chars);
	}

	/**
	 * Utility method to load images using path
	 */
	public static void add(String name, String imagePath, String masksPath, int charWidth, int charHeight, int sheetWidth,
	                       int sheetHeight, int firstDir) {

		BitmapData images = StaticAssetLoaderUtility.getEmbeddedImage(imagePath);

		BitmapData masks = null;

		if (masksPath != null) {
			masks = StaticAssetLoaderUtility.getEmbeddedImage(masksPath);
		}

		add(name, images, masks, charWidth, charHeight, sheetWidth, sheetHeight, firstDir);
	}
}