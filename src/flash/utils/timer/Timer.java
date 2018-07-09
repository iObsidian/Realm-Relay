package flash.utils.timer;

import alde.flash.utils.EventConsumer;
import flash.events.EventDispatcher;
import flash.events.TimerEvent;

import java.util.TimerTask;

/**
 * Representing AS3's Timer
 */
public class Timer extends EventDispatcher {

	public int delay;
	TimerTask t = null;
	private int repeatCount;

	/**
	 * Timer (is not started on Constructor, use start(int delay)
	 */
	public Timer(int delay) {
		this(delay, -1);
	}

	public Timer(int delay, int repeatCount) {
		this.delay = delay;
		this.repeatCount = repeatCount;
	}

	public void start() {
		start(delay);
	}

	private void start(int delay) {
		new java.util.Timer().schedule(
				new java.util.TimerTask() {
					@Override
					public void run() {
						if (repeatCount != -1) {
							repeatCount--;
						}

						for (EventConsumer r : listeners.keySet()) {
							if (listeners.get(r).equals(TimerEvent.TIMER)) {
								r.accept(null);
							} else if (listeners.get(r).equals(TimerEvent.TIMER_COMPLETE) && repeatCount == 0) {
								r.accept(null);
							}
						}

						if (repeatCount == 0) {
							stop();
						} else {
							start(delay); //re-schedule (delay can be set dynamically)
						}

					}
				},
				delay
		);
	}

	public boolean stop() {
		return (t != null && t.cancel());
	}
}
