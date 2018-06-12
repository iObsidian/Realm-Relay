package alde.flash.utils;

import java.util.function.Consumer;

import flash.events.Event;

/**
 * A method that accepts a Message
 */
public class EventConsumer<T extends Event> {

	public static final int RUNNABLE_TYPE = 0;
	public static final int CONSUMER_TYPE = 1;

	public int type = 0;

	private Runnable runnable;
	private Consumer<T> consumer;

	public EventConsumer(Runnable runnable) {
		this.runnable = runnable;
		type = 0;
	}

	public EventConsumer(Consumer<T> consumer) {
		this.consumer = consumer;
		type = 1;
	}

	public Consumer getConsumer() { // TODO might be null if type = runnable
		return consumer;
	}

	public Runnable getRunnable() {
		return runnable;
	}

}
