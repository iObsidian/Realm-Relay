package realmrelay.game.objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import realmrelay.game.XML;
import realmrelay.game.objects.animation.AnimatedChar;
import realmrelay.packets.data.unused.BitmapData;

/**
 * This is a 99.9% match.
 * I made it abstract. Use TextureDataConcrete.
 */
public abstract class TextureData {

    public BitmapData texture = null;
    public BitmapData mask = null;
    public AnimatedChar animatedChar = null;
    public List<TextureData> randomTextureData = null;
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