package kabam.rotmg.objects;

import alde.flash.utils.Vector;
import alde.flash.utils.XML;
import flash.display.BitmapData;
import kabam.rotmg.objects.animation.AnimatedChar;
import rotmg.util.AnimatedChars;
import rotmg.util.AssetLibrary;
import rotmg.util.MaskedImage;

import java.util.HashMap;

/**
 * This class is a 25% match. Removed the use of remote textures. Parse only works for textures.
 */
public class TextureDataConcrete extends TextureData {

	public static boolean remoteTexturesUsed = false;

	public TextureDataConcrete(XML param1) {
		if (param1.hasOwnProperty("RandomTexture")) {
			this.parse(param1.child("RandomTexture"), param1.getAttribute("id"));
		} else if (param1.hasOwnProperty("AnimatedTexture")) {
			this.parse(param1.child("AnimatedTexture"), param1.getAttribute("id"));
		} else if (param1.hasOwnProperty("Texture")) {
			this.parse(param1.child("Texture"), param1.getAttribute("id"));
		} else if (param1.hasOwnProperty("RemoteTexture")) {
			this.parse(param1.child("RemoteTexture"));
		} else {
			this.parse(param1);
		}
		for (XML xml : param1.childs("AltTexture")) {
			this.parse(xml);
		}
		if (param1.hasOwnProperty("Mask")) {
			this.parse(param1.child("Mask"));
		}
		if (param1.hasOwnProperty("Effect")) {
			for (XML x : param1.childs()) {
				System.out.println(x.name());
			}
			this.parse(param1.child("Effect"));
		}

	}

	private void parse(XML xml) {
		parse(xml, "");
	}

	/**
	 * This method is imcomplete
	 */
	private void parse(XML xml, String id) {
		MaskedImage image = null;
		//RemoteTexture remoteTexture = null;
		switch (xml.name()) {
			case "Texture":
				try {
					texture = AssetLibrary.getImageFromSet(xml.getValue("File"), xml.getIntValue("Index"));
				} catch (Exception e) {
					e.printStackTrace();

					System.err.println("Error loading Texture for " + id + " - name: " + xml.getValue("File") + " - idx: "
							+ xml.getIntValue("Index"));

					/*throw new Error("Error loading Texture for " + id + " - name: " + xml.getValue("File") + " - idx: "
							+ xml.getIntValue("Index"));*/
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
					image = animatedChar.imageFromDir(0, AnimatedChar.STAND, 0);
					texture = image.image;
					mask = image.mask;
				} catch (Exception e) {
					e.printStackTrace();
					throw new Error("Error loading AnimatedTexture for " + id + " - name: " + xml.getValue("File")
							+ " - idx: " + xml.getValue("Index"));
				}
				break;
			/**case "RemoteTexture":
			 texture = AssetLibrary.getImageFromSet("lofiObj3", 255);
			 if (this.isUsingLocalTextures) {
			 remoteTexture = new RemoteTexture(xml.Id, xml.Instance, this.onRemoteTexture);
			 remoteTexture.run();
			 if (!AssetLoader.currentXmlIsTesting) {
			 remoteTexturesUsed = true;
			 }
			 }
			 remoteTextureDir = xml.hasOwnProperty("Right") ? AnimatedChar.RIGHT : AnimatedChar.DOWN;
			 break;**/
			case "RandomTexture":
				try {
					randomTextureData = new Vector<TextureData>();
					for (XML childXML : xml.childs()) {
						randomTextureData.add(new TextureDataConcrete(childXML));
					}
				} catch (Exception e) {
					throw new Error("Error loading RandomTexture for " + id);
				}
				break;
			case "AltTexture":
				if (altTextures == null) {
					altTextures = new HashMap<Integer, TextureData>();
				}
				altTextures.put(xml.getIntAttribute("id"), new TextureDataConcrete(xml));
		}
	}

	@Override
	public BitmapData getTexture(int id) {
		return null;
	}

	@Override
	public TextureData getAltTextureData(int id) {
		return null;
	}
}
