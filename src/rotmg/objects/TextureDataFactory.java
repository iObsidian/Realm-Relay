package rotmg.objects;

import rotmg.game._as3.XML;

public class TextureDataFactory {

	public TextureData create(XML param1) {

		return new TextureDataConcrete(param1);
	}

}
