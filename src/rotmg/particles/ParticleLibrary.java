package rotmg.particles;

import alde.flash.utils.XML;

import java.util.HashMap;

/**
 * We parse a list of projectiles instead of a root XML.
 * <p>
 * This is the only difference.
 */
public class ParticleLibrary {

	public static final HashMap<String, ParticleProperties> propsLibrary = new HashMap<>();

	public static void parseFromXML(XML xml) {
		for (XML x : xml.getChilds("Particle")) {
			propsLibrary.put(xml.getAttribute("id"), new ParticleProperties(x));
		}
	}
}
