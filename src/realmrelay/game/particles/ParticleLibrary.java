package realmrelay.game.particles;

import realmrelay.game.XML;

import java.util.HashMap;
import java.util.Map;

public class ParticleLibrary {

	public static final HashMap<String, ParticleProperties> propsLibrary = new HashMap<>();

	public static void parseFromXML(XML xml) {
		for (XML particleXML : xml.getChilds("Particle")) {
			propsLibrary.put(particleXML.getAttribute("id"), new ParticleProperties(particleXML));
		}
	}

}
