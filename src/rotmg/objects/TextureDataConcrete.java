package rotmg.objects;

import alde.flash.utils.Vector;
import alde.flash.utils.XML;
import flash.display.BitmapData;
import flash.utils.Dictionary;
import rotmg.appengine.RemoteTexture;
import rotmg.objects.animation.AnimatedChar;
import rotmg.util.AnimatedChars;
import rotmg.util.AssetLibrary;
import rotmg.util.MaskedImage;

/**
 * This class is a 25% match. Removed the use of remote textures. Parse only works for textures.
 */
public class TextureDataConcrete extends TextureData {

	public static boolean remoteTexturesUsed = false;

	private Boolean isUsingLocalTextures;

	public TextureDataConcrete(XML param1) {
		super();
		this.isUsingLocalTextures = this.getWhetherToUseLocalTextures();
		if (param1.hasOwnProperty("Texture")) {
			this.parse(param1.child("Texture"), param1.getAttribute("id"));
		} else if (param1.hasOwnProperty("AnimatedTexture")) {
			this.parse(param1.child("AnimatedTexture"), param1.getAttribute("id"));
		} else if (param1.hasOwnProperty("RemoteTexture")) {
			this.parse(param1.child("RemoteTexture"));
		} else if (param1.hasOwnProperty("RandomTexture")) {
			this.parse(param1.child("RandomTexture"), param1.getAttribute("id"));
		} else {
			this.parse(param1);
		}
		for (XML loc2 :
				param1.children("AltTexture")) {
			this.parse(loc2);
		}
		if (param1.hasOwnProperty("Mask")) {
			this.parse(param1.child("Mask"));
		}
		if (param1.hasOwnProperty("Effect")) {
			this.parse(param1.child("Effect"));
		}
	}

	public BitmapData getTexture(int param1) {
		if (randomTextureData == null) {
			return texture;
		}
		TextureData loc2 = randomTextureData.get(param1 % randomTextureData.length);
		return loc2.getTexture(param1);
	}

	private void parse(XML xml) {
		parse(xml, "");
	}


	public TextureData getAltTextureData(int param1) {
		if (altTextures == null) {
			return null;
		}
		return altTextures.get(param1);
	}

	private boolean getWhetherToUseLocalTextures() {
		/*
		ApplicationSetup loc1 = ApplicationSetup.getInstance();
		return loc1.useLocalTextures();
		 */

		return false;
	}


	private void parse(XML param1, String param2) {
		MaskedImage image = null;
		RemoteTexture remoteTexture = null;
		XML xml = param1;
		String id = param2;
		switch (xml.name()) {
			case "Texture":
				try {
					texture = AssetLibrary.getImageFromSet(xml.getValue("File"), xml.getIntValue("Index"));
				} catch (Error error) {
					throw new Error("Error loading Texture for " + id + " -  name");
				}
				break;
			case "Mask":
				mask = AssetLibrary.getImageFromSet(xml.getValue("File"), xml.getIntValue("Index"));
				break;
			case "Effect":
				effectProps = new EffectProperties(xml);
				break;
			case "AnimatedTexture":
				animatedChar = AnimatedChars.getAnimatedChar(xml.getValue("File"), xml.getIntValue("Index"));
				try {
					image = animatedChar.imageFromAngle(0, AnimatedChar.STAND, 0);
					texture = image.image;
					mask = image.mask;
				} catch (Error error) {
					throw new Error("Error loading AnimatedTexture for " + id + " -  name");
				}
				break;
			case "RemoteTexture":
				System.err.println("Remote textures are not implemented!");
				/*texture = AssetLibrary.getImageFromSet("lofiObj3", 255);
				if (this.isUsingLocalTextures) {
					remoteTexture = new RemoteTexture(xml.getValue("Id"), xml.getValue("Instance"), this.onRemoteTexture);
					remoteTexture.run();
					if (!AssetLoader.currentXmlIsTesting) {
						remoteTexturesUsed = true;
					}
				}
				remoteTextureDir = !!xml.hasOwnProperty("Right") ? AnimatedChar.RIGHT : AnimatedChar.DOWN;*/
				break;
			case "RandomTexture":
				try {
					randomTextureData = new Vector<TextureData>();
					for (XML childXML : xml.children()) {
						randomTextureData.add(new TextureDataConcrete(childXML));
					}
				} catch (Error error) {
					throw new Error("Error loading RandomTexture for " + id);
				}
				break;
			case "AltTexture":
				if (altTextures == null) {
					altTextures = new Dictionary<>();
				}
				altTextures.put(xml.getIntAttribute("id"), new TextureDataConcrete(xml));
		}
	}

	private void onRemoteTexture(BitmapData param1) {
		if (param1.width > 16) {
			AnimatedChars.add("remoteTexture", param1, null, param1.width / 7, param1.height, param1.width, param1.height, remoteTextureDir);
			animatedChar = AnimatedChars.getAnimatedChar("remoteTexture", 0);
			texture = animatedChar.imageFromAngle(0, AnimatedChar.STAND, 0).image;
		} else {
			texture = param1;
		}
	}


}
