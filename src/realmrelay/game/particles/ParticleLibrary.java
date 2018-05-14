package realmrelay.game.particles;

import java.util.HashMap;
import java.util.List;

import realmrelay.game._as3.XML;

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
