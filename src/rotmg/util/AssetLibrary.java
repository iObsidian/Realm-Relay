package rotmg.util;

import alde.flash.utils.StaticAssetLoaderUtility;
import flash.display.BitmapData;
import flash.utils.Dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a 80% match.
 * <p>
 * Instead of 'Class' for sounds, we use the 'Sound' directly.
 * <p>
 * The play sound does not use a SoundTransform.
 */
public class AssetLibrary {

	private static Dictionary<String, BitmapData> images = new Dictionary<>();

	private static Dictionary<String, ImageSet> imageSets = new Dictionary<>();

	private static Dictionary<String, List<Sound>> sounds = new Dictionary<>();

	private static Dictionary<BitmapData, String> imageLookup = new Dictionary<BitmapData, String>();

	public static void addImage(String name, BitmapData data) {
		images.put(name, data);
		imageLookup.put(data, name);
	}

	/**
	 * Utility method used to load images from path rather than use EmbeddedAssets cumbersome classes
	 */
	public static void addImageSet(String name, String path, int width, int height) {
		BitmapData i = StaticAssetLoaderUtility.getEmbeddedImage(path);
		addImageSet(name, i, width, height);
	}

	public static void addImageSet(String name, BitmapData data, int width, int height) {
		images.put(name, data);
		ImageSet imageSet = new ImageSet();
		imageSet.addFromBitmapData(data, width, height);
		imageSets.put(name, imageSet);

		//System.out.println("Adding image set to imageSets, size : " + imageSets.size() + " : " + imageSet.images.size());

		for (BitmapData b : imageSet.images) {
			imageLookup.put(b, name);
		}
	}

	public static void addToImageSet(String name, BitmapData data) {
		ImageSet imageSet = null;
		imageSet = imageSets.get(name);

		if (imageSet == null) {
			imageSet = new ImageSet();
			imageSets.put(name, imageSet);
		}

		imageSet.add(data);
		imageLookup.put(data, name);
	}

	public static void addSound(String name, Sound soundClass) {
		List<Sound> a = sounds.get(name);

		if (a == null) {
			sounds.put(name, new ArrayList<>());
		}
		sounds.get(name).add(soundClass);
	}

	public static Object lookupImage(BitmapData data) {
		return imageLookup.get(data);
	}

	public static BitmapData getImage(String name) {
		return images.get(name);
	}

	public static ImageSet getImageSet(String name) {
		return imageSets.get(name);
	}

	public static BitmapData getImageFromSet(String name, int id) {

		//System.out.println("Loading '" + id + "' from '" + name + "'.");


		boolean error = false;

		if (imageSets.get(name) == null) {
			System.err.println("ERROR : ImageSet '" + name + "' DOES NOT EXIST!");
			error = true;
		}

		if (imageSets.get(name).images.size() == 0) {
			System.err.println("ERROR : ImageSet '" + name + "' IS EMPTY!");
			error = true;
		}

		if (error) {
			System.err.println("If you have correctly added the file inside assets folder, have you forgotten the '_' at the end of the file?");
		}

		if (imageSets.get(name).images.size() < id) {
			System.err.println("File '" + name + "' is not up to date, trying to load image " + id + " but there is only " + images.size() + " images. Replace all images and retry.");
		}

		return imageSets.get(name).images.get(id);
	}

	public static Sound getSound(String name) {
		List<Sound> a = sounds.get(name);
		int i = (int) (Math.random() * a.size());
		return sounds.get(name).get(i);
	}

	/**
	 * This method is not a 100% match
	 * Removed 'SoundTransform'
	 */
	public static void playSound(String name, double volume) {
		Sound sound = getSound(name);
		sound.play(0, 0);
	}
}