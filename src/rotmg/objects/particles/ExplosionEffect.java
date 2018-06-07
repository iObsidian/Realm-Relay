package rotmg.objects.particles;

import alde.flash.utils.Vector;
import flash.geom.Vector3D;
import rotmg.particles.ParticleEffect;

public class ExplosionEffect extends ParticleEffect {

	public Vector<Integer> colors;

	public int numParts;

	public ExplosionEffect(Vector<Integer> param1, int param2, int param3) {
		super();
		this.colors = param1;
		size = param2;
		if (ExplosionParticle.total >= 250) {
			this.numParts = 2;
		} else if (ExplosionParticle.total >= 150) {
			this.numParts = 4;
		} else if (ExplosionParticle.total >= 90) {
			this.numParts = 12;
		} else {
			this.numParts = param3;
		}
	}

	public boolean runNormalRendering(int param1, int param2) {
		int loc4 = 0;
		Particle loc5 = null;
		if (this.colors.length == 0) {
			return false;
		}
		if (ExplosionParticle.total > 400) {
			return false;
		}
		int loc3 = 0;
		while (loc3 < this.numParts) {
			loc4 = this.colors.get((int) (this.colors.length * Math.random()));
			loc5 = new ExplosionParticle(loc4, 0.5, size, (int) (200 + Math.random() * 100), Math.random() - 0.5, Math.random() - 0.5, 0);
			map.addObj(loc5, x, y);
			loc3++;
		}
		return false;
	}

	public boolean runEasyRendering(int param1, int param2) {
		int loc4 = 0;
		Particle loc5 = null;
		if (this.colors.length == 0) {
			return false;
		}
		if (ExplosionParticle.total > 400) {
			return false;
		}
		this.numParts = 2;
		int loc3 = 0;
		while (loc3 < this.numParts) {
			loc4 = this.colors.get((int) (this.colors.length * Math.random()));
			loc5 = new ExplosionParticle(loc4, 0.5, size, (int) (50 + Math.random() * 100), Math.random() - 0.5, Math.random() - 0.5, 0);
			map.addObj(loc5, x, y);
			loc3++;
		}
		return false;
	}
}


class ExplosionParticle extends Particle {

	public static int total = 0;

	public int lifetime;

	public int timeLeft;

	protected Vector3D moveVec;

	private boolean deleted = false;

	ExplosionParticle(int param1, double param2, int param3, int param4, double param5, double param6, double param7) {
		super(param1, param2, param3);
		this.moveVec = new Vector3D();
		this.timeLeft = this.lifetime = param4;
		this.moveVec.x = param5;
		this.moveVec.y = param6;
		this.moveVec.z = param7;
		total++;
	}

	public boolean update(int param1, int param2) {
		this.timeLeft = this.timeLeft - param2;
		if (this.timeLeft <= 0) {
			if (!this.deleted) {
				total--;
				this.deleted = true;
			}
			return false;
		}
		x = x + this.moveVec.x * param2 * 0.008;
		y = y + this.moveVec.y * param2 * 0.008;
		z = z + this.moveVec.z * param2 * 0.008;
		return true;
	}


}
