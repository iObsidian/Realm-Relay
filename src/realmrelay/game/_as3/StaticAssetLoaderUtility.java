package realmrelay.game._as3;

import realmrelay.game.WebMain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Not a part of AS3, just a lazy way of loading assets without all the stupid Embedded classes
 */
public class StaticAssetLoaderUtility {

	public static final BufferedImage defaultImage = getImageFromResources("/assets/unknown.png").image;

	public static final String prefixpath = "/assets/EmbeddedAssets_";
	public static final String suffixpath = ".png";

	public static BitmapData getEmbeddedImage(String path) {
		return getImageFromResources(prefixpath + path + suffixpath);
	}

	public static BitmapData getImageFromResources(String path) {
		BufferedImage data = defaultImage;
		try {
			data = ImageIO.read(WebMain.class.getResource(path));
		} catch (Exception e) {
			System.err.println("Error with getting image from path '" + path + "'.");
			e.printStackTrace();
		}

		return new BitmapData(data);
	}

}
