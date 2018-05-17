package realmrelay.game.particles;

import realmrelay.game._as3.XML;
import realmrelay.game.objects.TextureData;
import realmrelay.game.objects.TextureDataConcrete;
import realmrelay.game.objects.animation.AnimationsData;

public class ParticleProperties {

	public String id;

	public TextureData textureData;

	public int size = 100;

	public double z = 0.0F;

	public double duration = 0.0F;

	public AnimationsData animationsData = null;

	public ParticleProperties(XML particleXML) {
		this.id = particleXML.getAttribute("id");
		this.textureData = new TextureDataConcrete(particleXML);
		if (particleXML.hasOwnProperty("Size")) {
			this.size = particleXML.getIntValue("Size");
		}
		if (particleXML.hasOwnProperty("Z")) {
			this.z = particleXML.getDoubleValue("Z");
		}
		if (particleXML.hasOwnProperty("Duration")) {
			this.duration = particleXML.getDoubleValue("Duration");
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
