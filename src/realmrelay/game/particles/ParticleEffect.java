package realmrelay.game.particles;

import realmrelay.game.objects.EffectProperties;
import realmrelay.game.objects.GameObject;

public class ParticleEffect extends GameObject {

	public ParticleEffect() {
		objectId = getNextFakeObjectId();
		hasShadow = false;
	}

	public static ParticleEffect fromProps(EffectProperties effectProps, GameObject go) {
		switch (effectProps.id) {
			case "Healing":
				return new HealingEffect(go);
			case "Fountain":
				return new FountainEffect(go);
			case "Gas":
				return new GasEffect(go, effectProps);
			case "Vent":
				return new VentEffect(go);
			case "Bubbles":
				return new BubbleEffect(go, effectProps);
			case "XMLEffect":
				return new XMLEffect(go, effectProps);
			case "CustomParticles":
				return ParticleGenerator.attachParticleGenerator(effectProps, go);
			default:
				return null;
		}
	}

	public boolean update(int time, int dt) {
		return false;
	}

}
