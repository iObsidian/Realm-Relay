package realmrelay.game.map;

import realmrelay.game._as3.XML;
import realmrelay.game.objects.TextureData;
import realmrelay.game.objects.TextureDataConcrete;
import realmrelay.game.util.BitmapUtil;
import realmrelay.packets.data.unused.BitmapData;

import java.util.HashMap;
import java.util.List;

public class GroundLibrary {

	public static final HashMap<Integer, GroundProperties> propsLibrary = new HashMap<>();
	public static final HashMap<Integer, XML> xmlLibrary = new HashMap<>(); //ObjectType, XML
	private static HashMap<Integer, Integer> tileTypeColorDict = new HashMap<>();
	public static final HashMap<Integer, TextureData> typeToTextureData = new HashMap<>();
	public static HashMap<String, Integer> idToType = new HashMap<>(); //id, TextureData
	public static GroundProperties defaultProps;
	public static String GROUND_CATEGORY = "Ground";

	public static void parseFromXML(XML xml) {

			int objectType = xml.getIntAttribute("type");
			propsLibrary.put(objectType, new GroundProperties(xml));
			xmlLibrary.put(objectType, xml);
			typeToTextureData.put(objectType, new TextureDataConcrete(xml));
			idToType.put(xml.getAttribute("id"), objectType);

		defaultProps = propsLibrary.get(255);
	}

	public static String getIdFromType(int objectType) {
		GroundProperties groundProperties = propsLibrary.get(objectType);
		if (groundProperties == null) {
			return null;
		}
		return groundProperties.id;
	}

	/**
	 * Utility method for optional param2
	 */
	public static BitmapData getBitmapData(int param1) {
		return getBitmapData(param1, 0);
	}

	public static GroundProperties getPropsFromId(String param1) {
		return propsLibrary.get(idToType.get(param1));
	}

	public static BitmapData getBitmapData(int param1, int param2) {
		return typeToTextureData.get(param1).getTexture(param2);
	}

	public static int getColor(int param1) {
		XML objectXML = null;
		int color = 0;
		BitmapData _loc4 = null;
		if (!tileTypeColorDict.containsKey(param1)) {
			objectXML = xmlLibrary.get(param1);
			if (objectXML.hasOwnProperty("Color")) {
				color = objectXML.getIntAttribute("Color");
			} else {
				_loc4 = getBitmapData(param1);
				color = BitmapUtil.mostCommonColor(_loc4);
			}
			tileTypeColorDict.put(param1, color);
		}
		return tileTypeColorDict.get(param1);
	}

}
