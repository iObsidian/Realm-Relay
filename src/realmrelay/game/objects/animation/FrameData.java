package realmrelay.game.objects.animation;

import realmrelay.game._as3.XML;
import realmrelay.game.objects.TextureData;
import realmrelay.game.objects.TextureDataConcrete;

/**
 * This is a 100% match.
 */
public class FrameData {

	public int time;

	public TextureData textureData;

	public FrameData(XML xml) {
		this.time = (int) (xml.getFloatAttribute("time") * 1000);
		this.textureData = new TextureDataConcrete(xml);
	}

}
