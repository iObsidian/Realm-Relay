package rotmg.assets.model;

import alde.flash.utils.EventConsumer;
import flash.display.BitmapData;
import flash.events.TimerEvent;
import flash.utils.timer.Timer;

import java.util.ArrayList;
import java.util.List;

/**
 * All done. Very simillar to RotMG's Animation,
 * to the exception that bitmap is a bitmapData,
 * and this does not extend Sprite.
 */
public class Animation {

	private final int DEFAULT_SPEED = 200;
	private final List<BitmapData> frames = new ArrayList<BitmapData>(0);
	private BitmapData bitmapData = new BitmapData(0, 0);
	private boolean started;
	private int index;
	private int count;
	private final Timer timer = this.makeTimer();
	private boolean disposed;


	public Animation() {
		super();
	}

	private Timer makeTimer() {
		Timer loc1 = new Timer(this.DEFAULT_SPEED);
		loc1.addEventListener(TimerEvent.TIMER, new EventConsumer<>(this::iterate));
		return loc1;
	}

	public int getSpeed() {
		return this.timer.delay;
	}

	public void setSpeed(int param1) {
		this.timer.delay = param1;
	}

	public void setFrames(BitmapData... rest) {
		this.frames.clear();
		this.index = 0;
		for (BitmapData loc2 : rest) {
			this.frames.add(loc2);
			this.count = this.frames.size();
		}
		if (this.started) {
			this.start();
		} else {
			this.iterate();
		}
	}

	public void addFrame(BitmapData param1) {
		this.frames.add(param1);
		this.count = this.frames.size();
		this.start();
		this.started = true;
	}

	public void start() {
		if (!this.started && this.count > 0) {
			this.timer.start();
			this.iterate();
		}
		this.started = true;
	}

	public void stop() {
		this.timer.stop();
		this.started = false;
	}

	private void iterate() {
		this.index = ++this.index % this.count;
		this.bitmapData = this.frames.get(this.index);
	}

	public void dispose() {
		this.disposed = true;
		this.stop();
		this.index = 0;
		this.count = 0;
		this.frames.clear();
		for (BitmapData loc1 : this.frames) {
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
