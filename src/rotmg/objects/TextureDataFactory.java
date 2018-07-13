package rotmg.objects;

import alde.flash.utils.XML;

public class TextureDataFactory {

	public TextureData create(XML param1) {
		return new TextureDataConcrete(param1);
	}

}
