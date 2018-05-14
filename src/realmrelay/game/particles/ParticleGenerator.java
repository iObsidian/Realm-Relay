package realmrelay.game.particles;

import realmrelay.game.objects.EffectProperties;
import realmrelay.game.objects.GameObject;
import realmrelay.game.util.AssetLibrary;
import realmrelay.packets.data.unused.BitmapData;

import java.util.ArrayList;
import java.util.List;

public class ParticleGenerator extends ParticleEffect {

	private List<BaseParticle> particlePool;

	private List<BaseParticle> liveParticles;

	private GameObject targetGO;

	private double generatedParticles = 0;

	private double totalTime = 0;

	private EffectProperties effectProps;

	private BitmapData bitmapData;

	private double friction;

	public ParticleGenerator(EffectProperties effectProperties, GameObject go) {
		super();
		this.targetGO = go;
		this.particlePool = new ArrayList<BaseParticle>();
		this.liveParticles = new ArrayList<BaseParticle>();
		this.effectProps = effectProperties;
		if (this.effectProps.bitmapFile) {
			this.bitmapData = AssetLibrary.getImageFromSet(this.effectProps.bitmapFile, this.effectProps.bitmapIndex);
			this.bitmapData = TextureRedrawer.redraw(this.bitmapData, this.effectProps.size, true, 0, 0);
		} else {
			this.bitmapData = TextureRedrawer.redrawSolidSquare(this.effectProps.color, this.effectProps.size);
		}
	}

	public static ParticleGenerator attachParticleGenerator(EffectProperties effectProperties, GameObject go) {
		return new ParticleGenerator(effectProperties, go);
	}

	@Override
	public boolean update(int time, int dt)
	{
		double tDelta = 0F;
		BaseParticle newParticle = null;
		BaseParticle particle = null;
		double t = time / 1000;
		tDelta = dt / 1000;
		if (this.targetGO.map == null) {
			return false;
		}
		x = this.targetGO.x;
		y = this.targetGO.y;
		z = this.targetGO.z + this.effectProps.zOffset;
		this.totalTime = this.totalTime + tDelta;
		double projectedTotal = this.effectProps.rate * this.totalTime;
		int particlesToGen = projectedTotal - this.generatedParticles;
		for (int i = 0; i < particlesToGen; i++) {
			if (this.particlePool.length) {
				newParticle = this.particlePool.pop();
			} else {
				newParticle = new BaseParticle(this.bitmapData);
			}
			newParticle.initialize(this.effectProps.life + this.effectProps.lifeVariance * (2 * Math.random() - 1), this.effectProps.speed + this.effectProps.speedVariance * (2 * Math.random() - 1), this.effectProps.speed + this.effectProps.speedVariance * (2 * Math.random() - 1), this.effectProps.rise + this.effectProps.riseVariance * (2 * Math.random() - 1), z_);
			map.addObj(newParticle, x + this.effectProps.rangeX * (2 * Math.random() - 1), y_ + this.effectProps.rangeY * (2 * Math.random() - 1));
			this.liveParticles.add(newParticle);
		}
		this.generatedParticles = this.generatedParticles + particlesToGen;
		for (BaseParticle particle : this.liveParticles) {
			particle.timeLeft = particle.timeLeft - tDelta;
			if (particle.timeLeft <= 0) {
				this.liveParticles.splice(j, 1);
				map.removeObj(particle.objectId);
				j--;
				this.particlePool.add(particle);
			} else {
				particle.spdZ = particle.spdZ + this.effectProps.riseAcc * tDelta;
				particle.x = particle.x + particle.spdX * tDelta;
				particle.y = particle.y + particle.spdY * tDelta;
				particle.z = particle.z + particle.spdZ * tDelta;
			}
		}
		return true;
	}

	@Override
	public void removeFromMap()
	{
		for (BaseParticle particle : this.liveParticles) {
			map.removeObj(particle.objectId);
		}
		this.liveParticles = null;
		this.particlePool = null;
		super.removeFromMap();
	}


}
