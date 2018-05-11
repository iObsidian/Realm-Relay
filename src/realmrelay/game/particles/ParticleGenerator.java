package realmrelay.game.particles;

import realmrelay.game.objects.EffectProperties;
import realmrelay.game.objects.GameObject;
import realmrelay.game.util.AssetLibrary;
import realmrelay.packets.data.unused.BitmapData;

import java.util.List;

public class ParticleGenerator {


	private List<BaseParticle> particlePool;

	private List<BaseParticle> liveParticles;

	private GameObject targetGO;

	private float generatedParticles = 0;

	private float totalTime = 0;

	private EffectProperties effectProps;

	private BitmapData bitmapData;

	private float friction;

	public function ParticleGenerator(effectProperties:EffectProperties, go:GameObject)
	{
		super();
		this.targetGO = go;
		this.particlePool = new List<BaseParticle>();
		this.liveParticles = new List<BaseParticle>();
		this.effectProps = effectProperties;
		if(this.effectProps.bitmapFile)
		{
			this.bitmapData = AssetLibrary.getImageFromSet(this.effectProps.bitmapFile,this.effectProps.bitmapIndex);
			this.bitmapData = TextureRedrawer.redraw(this.bitmapData,this.effectProps.size,true,0,0);
		}
		else
		{
			this.bitmapData = TextureRedrawer.redrawSolidSquare(this.effectProps.color,this.effectProps.size);
		}
	}

	public static function attachParticleGenerator(effectProperties:EffectProperties, go:GameObject) : ParticleGenerator
	{
		return new ParticleGenerator(effectProperties,go);
	}

	override public function update(time:int, dt:int) : boolean
	{
		float tDelta = NaN;
		BaseParticle newParticle = null;
		BaseParticle particle = null;
		float t = time / 1000;
		tDelta = dt / 1000;
		if(this.targetGO.map_ == null)
		{
			return false;
		}
		x = this.targetGO.x_;
		y = this.targetGO.y_;
		z = this.targetGO.z_ + this.effectProps.zOffset;
		this.totalTime = this.totalTime + tDelta;
		float projectedTotal = this.effectProps.rate * this.totalTime;
		int particlesToGen = projectedTotal - this.generatedParticles;
		for(int i = 0; i < particlesToGen; i++)
		{
			if(this.particlePool.length)
			{
				newParticle = this.particlePool.pop();
			}
			else
			{
				newParticle = new BaseParticle(this.bitmapData);
			}
			newParticle.initialize(this.effectProps.life + this.effectProps.lifeVariance * (2 * Math.random() - 1),this.effectProps.speed + this.effectProps.speedVariance * (2 * Math.random() - 1),this.effectProps.speed + this.effectProps.speedVariance * (2 * Math.random() - 1),this.effectProps.rise + this.effectProps.riseVariance * (2 * Math.random() - 1),z_);
			map_.addObj(newParticle,x_ + this.effectProps.rangeX * (2 * Math.random() - 1),y_ + this.effectProps.rangeY * (2 * Math.random() - 1));
			this.liveParticles.add(newParticle);
		}
		this.generatedParticles = this.generatedParticles + particlesToGen;
		for(int j = 0; j < this.liveParticles.length; j++)
		{
			particle = this.liveParticles[j];
			particle.timeLeft = particle.timeLeft - tDelta;
			if(particle.timeLeft <= 0)
			{
				this.liveParticles.splice(j,1);
				map_.removeObj(particle.objectId_);
				j--;
				this.particlePool.add(particle);
			}
			else
			{
				particle.spdZ = particle.spdZ + this.effectProps.riseAcc * tDelta;
				particle.x = particle.x_ + particle.spdX * tDelta;
				particle.y = particle.y_ + particle.spdY * tDelta;
				particle.z = particle.z_ + particle.spdZ * tDelta;
			}
		}
		return true;
	}

	override public function removeFromMap() : void
	{
		BaseParticle particle = null;
		for(particle in this.liveParticles)
		{
			map_.removeObj(particle.objectId_);
		}
		this.liveParticles = null;
		this.particlePool = null;
		super.removeFromMap();
	}


}
