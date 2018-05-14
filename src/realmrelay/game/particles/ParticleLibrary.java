package realmrelay.game.particles;

import realmrelay.game.XML;

import java.util.HashMap;
import java.util.List;

/**
 * We parse a list of projectiles instead of a root XML.
 * <p>
 * This is the only difference.
 */
public class ParticleLibrary {

	public static final HashMap<String, ParticleProperties> propsLibrary = new HashMap<>();

	public static void parseFromXML(List<XML> xmls) {

		for (XML xml : xmls) {

			ParticleProperties p = new ParticleProperties(xml);

			propsLibrary.put(xml.getAttribute("id"), p);

			System.out.println(p.toString());

		}

	}

}
