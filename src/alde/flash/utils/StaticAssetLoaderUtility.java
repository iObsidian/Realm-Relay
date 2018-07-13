package alde.flash.utils;

import flash.display.BitmapData;
import rotmg.WebMain;

import javax.imageio.ImageIO;

/**
 * Not a part of AS3, just a lazy way of loading assets without all the stupid Embedded classes
 */
public class StaticAssetLoaderUtility {

	public static final BitmapData defaultImage = getImageFromResources("/assets/unknown.png");

	public static final String prefixpath = "/assets/EmbeddedAssets_";
	public static final String suffixpath = ".png";

	public static BitmapData getEmbeddedImage(String path) {
		return getImageFromResources(prefixpath + path + suffixpath);
	}

	public static BitmapData getImageFromResources(String path) {
		BitmapData data = defaultImage;
		try {
			data = new BitmapData(ImageIO.read(WebMain.class.getResource(path)));
		} catch (Exception e) {
			System.err.println("Error with getting image from path '" + path + "'.");
			e.printStackTrace();
		}

		return data;
	}

}
