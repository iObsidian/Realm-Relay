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

			ParticleProperties p = new ParticleProperties(xml);

			propsLibrary.put(xml.getAttribute("id"), p);

			System.out.println(p.toString());



	}

}
