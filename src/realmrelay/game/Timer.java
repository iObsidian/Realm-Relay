package realmrelay.game;

import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Representing AS3's Timer
 */
public class Timer {

	public int delay;

	Runnable c;

	TimerTask t = null;

	public void addEventListener(Integer i, Runnable method) {
		this.c = method;
	}

	/**
	 * Timer (is not started on Constructor, use start(int delay)
	 */
	public Timer(int delay) {
		this.delay = delay;
	}

	public void start() {
		start(delay);
	}

	private void start(int delay) {
		new java.util.Timer().schedule(
				new java.util.TimerTask() {
					@Override
					public void run() {
						c.run();
						start(delay); //re-schedule (delay can be set dynamically)
					}
				},
				delay
		);
	}

	public boolean stop() {
		return (t != null && t.cancel());
	}
}
