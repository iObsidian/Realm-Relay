package alde.flash.utils;

import flash.events.TimerEvent;

import java.util.HashMap;
import java.util.TimerTask;

/**
 * Representing AS3's Timer
 */
public class Timer {

	private int repeatCount;
	public int delay;

	TimerTask t = null;

	HashMap<Runnable, String> runnablesAndCondition = new HashMap<>();

	public void addEventListener(String s, Runnable method) {
		runnablesAndCondition.put(method, s);
	}

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

						for (Runnable r : runnablesAndCondition.keySet()) {

							if (runnablesAndCondition.get(r).equals(TimerEvent.TIMER)) {
								r.run();
							} else if (runnablesAndCondition.get(r).equals(TimerEvent.TIMER_COMPLETE) && repeatCount == 0) {
								r.run();
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
