package realmrelay.game.assets.model;

import realmrelay.packets.data.unused.BitmapData;

import java.util.ArrayList;
import java.util.List;

public class Animation {

	private final int DEFAULT_SPEED = 200;

	private final Bitmap bitmap = this.makeBitmap();

	private final List<BitmapData> frames = new ArrayList<BitmapData>(0);

	private final Timer timer = this.makeTimer();

	private boolean started;

	private int index;

	private int count;

	private boolean disposed;

	public Animation() {
		super();
	}

	private Bitmap makeBitmap() {
		Bitmap loc1 = new Bitmap();
		addChild(loc1);
		return loc1;
	}

	private Timer makeTimer() {
		Timer loc1 = new Timer(this.DEFAULT_SPEED);
		loc1.addEventListener(TimerEvent.TIMER, this.iterate);
		return loc1;
	}

	public int getSpeed() {
		return this.timer.delay;
	}

	public void setSpeed(int param1) {
		this.timer.delay = param1;
	}

	public function setFrames(...rest):void

	{
		BitmapData loc2 = null;
		this.frames.length = 0;
		this.index = 0;
		for (loc2 in rest) {
			this.count = this.frames.add(loc2);
		}
		if (this.started) {
			this.start();
		} else {
			this.iterate();
		}
	}

	public void addFrame(BitmapData param1) {
		this.count = this.frames.add(param1);
		this.started && this.start();
	}

	public void start() {
		if (!this.started && this.count > 0) {
			this.timer.start();
			this.iterate();
		}
		this.started = true;
	}

	public void stop() {
		this.started && this.timer.stop();
		this.started = false;
	}

	private void iterate(TimerEvent =null param1) {
		this.index = ++this.index % this.count;
		this.bitmap.bitmapData = this.frames[this.index];
	}

	public void dispose() {
		BitmapData loc1 = null;
		this.disposed = true;
		this.stop();
		this.index = 0;
		this.count = 0;
		this.frames.length = 0;
		for (loc1 in this.frames) {
			loc1.dispose();
		}
	}

	public boolean isStarted() {
		return this.started;
	}

	public boolean isDisposed() {
		return this.disposed;
	}


}
