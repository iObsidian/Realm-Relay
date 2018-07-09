package rotmg.objects.animation;

import alde.flash.utils.XML;
import kabam.rotmg.objects.TextureData;
import kabam.rotmg.objects.TextureDataConcrete;


/**
 * This is a 100% match.
 */
public class FrameData {

	public int time;

	public TextureData textureData;

	public FrameData(XML xml) {
		this.time = (int) (xml.getDoubleAttribute("time") * 1000);
		this.textureData = new TextureDataConcrete(xml);
	}

}
