package rotmg.particles;

import rotmg.objects.EffectProperties;
import rotmg.objects.GameObject;

public class XMLEffect extends ParticleEffect {

	private GameObject go;

	private ParticleProperties partProps;

	private double cooldown;

	private double cooldownLeft;

	public XMLEffect(GameObject go, EffectProperties effectProps) {
		super();
		this.go = go;
		this.partProps = ParticleLibrary.propsLibrary.get(effectProps.particle);
		this.cooldown = effectProps.cooldown;
		this.cooldownLeft = 0;
	}

	@Override
	public boolean update(int time, int dt) {
		if (this.go.map == null) {
			return false;
		}
		double fdt = dt / 1000;
		this.cooldownLeft = this.cooldownLeft - fdt;
		if (this.cooldownLeft >= 0) {
			return true;
		}
		this.cooldownLeft = this.cooldown;
		map.addObj(new XMLParticle(this.partProps), this.go.x, this.go.y);
		return true;
	}


}
