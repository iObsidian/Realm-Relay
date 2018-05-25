package rotmg.map;

import java.util.HashMap;

import alde.flash.utils.XML;
import rotmg.game._as3.XML;

/**
 * 100% match
 */
public class RegionLibrary {
	public static final HashMap<Integer, XML> xmlLibrary = new HashMap<>();

	public static HashMap<String, Integer> idToType = new HashMap<>();

	public static final int ENTRY_REGION_TYPE = 1;

	public static final int EXIT_REGION_TYPE = 48;

	public RegionLibrary() {
		super();
	}

	public static void parseFromXML(XML param1) {
		for (XML loc2 : param1.getChilds("Region")) {
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
