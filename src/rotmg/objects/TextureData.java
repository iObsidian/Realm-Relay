package rotmg.objects;

import java.util.HashMap;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
<<<<<<< HEAD:src/kabam/rotmg/objects/TextureData.java
import kabam.rotmg.objects.animation.AnimatedChar;
<<<<<<< HEAD:src/rotmg/objects/TextureData.java

import java.util.HashMap;
=======
import rotmg.objects.animation.AnimatedChar;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/objects/TextureData.java
=======
import rotmg.objects.animation.AnimatedChar;
>>>>>>> parent of 5791e6e... Commit before reverting refactoring:src/kabam/rotmg/objects/TextureData.java

/**
 * This is a 99.9% match.
 * I made it abstract. Use TextureDataConcrete.
 */
public abstract class TextureData {

	public BitmapData texture = null;
	public BitmapData mask = null;
	public AnimatedChar animatedChar = null;
	public Vector<TextureData> randomTextureData = null;
	public HashMap<Integer, TextureData> altTextures = null; //ID, XML
	public int remoteTextureDir;
	public EffectProperties effectProps = null;

	// Utility method for default int
	public BitmapData getTexture() {
		return getTexture(0);
	}

	public abstract BitmapData getTexture(int id);

	public abstract TextureData getAltTextureData(int id);

}
