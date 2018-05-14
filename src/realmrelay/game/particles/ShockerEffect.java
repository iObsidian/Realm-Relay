package realmrelay.game.particles;

import realmrelay.game._as3.Point;
import realmrelay.game._as3.Timer;
import realmrelay.game._as3.TimerEvent;
import realmrelay.game.objects.GameObject;
import realmrelay.game.util.AssetLibrary;
import realmrelay.game.util.ImageSet;
import realmrelay.packets.data.unused.BitmapData;

import java.util.ArrayList;
import java.util.List;

public class ShockerEffect extends ParticleEffect {

	public static List<BitmapData> images;

	public Point start;

	public Point end;

	public int objectId;

	public GameObject go;

	private float innerRadius;

	private float outerRadius;

	private float radians;

	private int particleScale;

	private Timer timer;

	private boolean isDestroyed = false;

	public ShockerEffect(GameObject param1) {
		super();
		this.go = param1;
		if (param1.texture.height() == 8) {
			this.innerRadius = 0.4F;
			this.outerRadius = 1.3F;
			this.particleScale = 30;
		} else {
			this.innerRadius = 0.7F;
			this.outerRadius = 2;
			this.particleScale = 40;
		}
	}

	private void parseBitmapDataFromImageSet() {
		int loc2 = 0;
		images = new ArrayList<BitmapData>();
		ImageSet loc1 = AssetLibrary.getImageSet("lofiParticlesShocker");
		int loc3 = 9;
		loc2 = 0;
		while (loc2 < loc3) {
			images.add(TextureRedrawer.redraw(loc1.images.get(loc2), this.particleScale, true, 0, true));
			loc2++;
		}
	}

	@Override
	public boolean update(int param1, int param2) {
		if (this.isDestroyed) {
			return false;
		}
		if (this.timer == null) {
			this.initialize();
		}
		x = this.go.x_;
		y = this.go.y_;
		return true;
	}

	private void initialize() {
		this.timer = new Timer(200, 10);
		this.timer.addEventListener(TimerEvent.TIMER, this::onTimer);
		this.timer.addEventListener(TimerEvent.TIMER_COMPLETE, this::onTimerComplete);
		this.timer.start();
		this.parseBitmapDataFromImageSet();
	}

	private void onTimer(TimerEvent param1) {
		if (map_) {
			this.radians = int(Math.random() * 360) * (Math.PI / 180);
			this.start = new Point(this.go.x + Math.sin(this.radians) * this.innerRadius, this.go.y + Math.cos(this.radians) * this.innerRadius);
			this.end = new Point(this.go.x + Math.sin(this.radians) * this.outerRadius, this.go.y + Math.cos(this.radians) * this.outerRadius);
			map.addObj(new ShockParticle(this.objectId, 25, this.particleScale, this.start. this.end.
			this.radians, this.go, images),this.start.x, this.start.y);
		}
	}

	private void onTimerComplete(TimerEvent param1) {
		this.destroy();
	}

	public void destroy() {
		if (this.timer) {
			this.timer.removeEventListener(TimerEvent.TIMER, this.onTimer);
			this.timer.removeEventListener(TimerEvent.TIMER, this.onTimerComplete);
			this.timer.stop();
			this.timer = null;
		}
		this.go = null;
		this.isDestroyed = true;
	}



	public void removeFromMap() {
		this.destroy();
		super.removeFromMap();
	}


}
