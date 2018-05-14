package realmrelay.game.objects;

import realmrelay.game._as3.XML;

public class TextureDataFactory {

	public TextureData create(XML param1) {

		return new TextureDataConcrete(param1);
	}

}
