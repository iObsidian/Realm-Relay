package rotmg.particles;

import alde.flash.utils.XML;
import flash.utils.Dictionary;

/**
 * We parse a list of projectiles instead of a root XML.
 * <p>
 * This is the only difference.
 */
public class ParticleLibrary {

	public static final Dictionary<String, ParticleProperties> propsLibrary = new Dictionary<>();

	public static void parseFromXML(XML xml) {
		for (XML x : xml.children("Particle")) {
			propsLibrary.put(xml.getAttribute("id"), new ParticleProperties(x));
		}
	}
}
