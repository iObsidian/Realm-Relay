package rotmg.map;

import alde.flash.utils.XML;
import flash.utils.Dictionary;

/**
 * 100% match
 */
public class RegionLibrary {
	public static final Dictionary<Integer, XML> xmlLibrary = new Dictionary<>();
	public static final int ENTRY_REGION_TYPE = 1;
	public static final int EXIT_REGION_TYPE = 48;
	public static Dictionary<String, Integer> idToType = new Dictionary<>();

	public RegionLibrary() {
		super();
	}

	public static void parseFromXML(XML param1) {
		for (XML loc2 : param1.children("Region")) {
			int type = loc2.getIntAttribute("type");
			xmlLibrary.put(type, loc2);
			idToType.put(loc2.getAttribute("id"), type);
		}
	}

	public static String getIdFromType(int param1) {
		XML loc2 = xmlLibrary.get(param1);
		if (loc2 == null) {
			return null;
		}
		return loc2.getAttribute("id");
	}

	public static int getColor(int param1) {
		XML loc2 = xmlLibrary.get(param1);
		if (loc2 == null) {
			return 0;
		}
		return loc2.getIntValue("Color");
	}

}
