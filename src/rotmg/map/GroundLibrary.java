package rotmg.map;

import alde.flash.utils.XML;
import flash.display.BitmapData;
import flash.utils.Dictionary;
import rotmg.objects.TextureData;
import rotmg.objects.TextureDataConcrete;
import rotmg.util.BitmapUtil;

public class GroundLibrary {

	public static final Dictionary<Integer, GroundProperties> propsLibrary = new Dictionary<>();
	public static final Dictionary<Integer, XML> xmlLibrary = new Dictionary<>(); //ObjectType, XML
	public static final Dictionary<Integer, TextureData> typeToTextureData = new Dictionary<>();
	public static Dictionary<String, Integer> idToType = new Dictionary<>(); //id, TextureData
	public static GroundProperties defaultProps;
	public static String GROUND_CATEGORY = "Ground";
	private static Dictionary<Integer, Integer> tileTypeColorDict = new Dictionary<>();

	public static void parseFromXML(XML xml) {
		int objectType = xml.getIntAttribute("type");

		for (XML loc2 : xml.children("Ground")) {
			propsLibrary.put(objectType, new GroundProperties(loc2));
			xmlLibrary.put(objectType, loc2);
			typeToTextureData.put(objectType, new TextureDataConcrete(loc2));
			idToType.put(loc2.getAttribute("id"), objectType);
		}

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
