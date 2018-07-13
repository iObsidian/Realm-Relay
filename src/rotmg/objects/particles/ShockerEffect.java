package rotmg.objects.particles;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.Vector;
import flash.display.BitmapData;
import flash.events.TimerEvent;
import flash.geom.Point;
import flash.utils.timer.Timer;
import rotmg.objects.GameObject;
import rotmg.particles.ParticleEffect;
import rotmg.util.AssetLibrary;
import rotmg.util.ImageSet;
import rotmg.util.TextureRedrawer;

public class ShockerEffect extends ParticleEffect {

	public static Vector<BitmapData> images;

	public Point start;

	public Point end;

	public int objectId;

	public GameObject go;

	private double innerRadius;

	private double outerRadius;

	private double radians;

	private int particleScale;

	private Timer timer;

	private boolean isDestroyed = false;

	public ShockerEffect(GameObject param1) {
		super();
		this.go = param1;
		if (param1.texture.height == 8) {
			this.innerRadius = 0.4;
			this.outerRadius = 1.3;
			this.particleScale = 30;
		} else {
			this.innerRadius = 0.7;
			this.outerRadius = 2;
			this.particleScale = 40;
		}
	}

	private void parseBitmapDataFromImageSet() {
		int loc2 = 0;
		images = new Vector<BitmapData>();
		ImageSet loc1 = AssetLibrary.getImageSet("lofiParticlesShocker");
		int loc3 = 9;
		loc2 = 0;
		while (loc2 < loc3) {
			images.add(TextureRedrawer.redraw(loc1.images.get(loc2), this.particleScale, true, 0, true));
			loc2++;
		}
	}

	public boolean update(int param1, int param2) {
		if (this.isDestroyed) {
			return false;
		}
		if (this.timer == null) {
			this.initialize();
		}
		x = this.go.x;
		y = this.go.y;
		return true;
	}

	private void initialize() {
		this.timer = new Timer(200, 10);
		this.timer.addEventListener(TimerEvent.TIMER, new EventConsumer<>(this::onTimer));
		this.timer.addEventListener(TimerEvent.TIMER_COMPLETE, new EventConsumer<>(this::onTimerComplete));
		this.timer.start();
		this.parseBitmapDataFromImageSet();
	}

	public void onTimer() {
		if (map != null) {
			this.radians = Math.random() * 360 * (Math.PI / 180);
			this.start = new Point(this.go.x + Math.sin(this.radians) * this.innerRadius, this.go.y + Math.cos(this.radians) * this.innerRadius);
			this.end = new Point(this.go.x + Math.sin(this.radians) * this.outerRadius, this.go.y + Math.cos(this.radians) * this.outerRadius);
			map.addObj(new ShockParticle(this.objectId, 25, this.particleScale, this.start, this.end, this.radians, this.go, images), this.start.x, this.start.y);
		}
	}

	private void onTimerComplete() {
		this.destroy();
	}

	public void destroy() {
		if (this.timer != null) {
			this.timer.removeEventListener(TimerEvent.TIMER, new EventConsumer<>(this::onTimer));
			this.timer.removeEventListener(TimerEvent.TIMER, new EventConsumer<>(this::onTimerComplete));
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
