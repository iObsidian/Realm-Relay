package rotmg.sound;

import flash.utils.Dictionary;

import java.util.Map;

public class SoundEffectLibrary {

	private static final String URL_PATTERN = "{URLBASE}/sfx/{NAME}.mp3";
	public static Map nameMap = new Dictionary<String, Integer>();
	private static String urlBase;

	public static void load(String name) {
		System.out.println("Request to load " + name);
	}

	public static void play(String string) {

	}

	/**public static Sound load(String name) {
	 return nameMap.get(name) = nameMap.get(name) || makeSound(param1);
	 }

	 public static Sound makeSound(String param1) {
	 Sound _loc2_ = new Sound();
	 _loc2_.load(makeSoundRequest(param1));
	 return _loc2_;
	 }*/


}
