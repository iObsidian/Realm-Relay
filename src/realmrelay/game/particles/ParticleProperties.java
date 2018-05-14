package realmrelay.game.particles;

import realmrelay.game.XML;
import realmrelay.game.objects.TextureData;
import realmrelay.game.objects.TextureDataConcrete;
import realmrelay.game.objects.animation.AnimationsData;

public class ParticleProperties {

	public String id;

	public TextureData textureData;

	public int size = 100;

	public float z = 0.0F;

	public float duration = 0.0F;

	public AnimationsData animationsData = null;

	public ParticleProperties(XML particleXML) {
		this.id = particleXML.getAttribute("id");
		this.textureData = new TextureDataConcrete(particleXML);
		if (particleXML.hasOwnProperty("Size")) {
			this.size = particleXML.getIntValue("Size");
		}
		if (particleXML.hasOwnProperty("Z")) {
			this.z = particleXML.getFloatValue("Z");
		}
		if (particleXML.hasOwnProperty("Duration")) {
			this.duration = particleXML.getFloatValue("Duration");
		}
		if (particleXML.hasOwnProperty("Animation")) {
			this.animationsData = new AnimationsData(particleXML);
		}
	}

	@Override
	public String toString() {
		return "ParticleProperties{" +
				"id='" + id + '\'' +
				", textureData=" + textureData +
				", size=" + size +
				", z=" + z +
				", duration=" + duration +
				", animationsData=" + animationsData +
				'}';
	}
}
