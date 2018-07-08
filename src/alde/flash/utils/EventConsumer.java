package alde.flash.utils;

import flash.events.Event;
import rotmg.net.impl.Message;

import java.util.function.Consumer;

/**
 * A method that accepts a Message
 */
public class EventConsumer<T extends Event> {

	Consumer<T> consumer;
	Runnable runnable;

	public EventConsumer(Consumer<T> consumer) {
		this.consumer = consumer;
	}

	public EventConsumer(Runnable runnable) {
		this.runnable = runnable;
	}

	public void accept(T event) {
		if (runnable != null) {
			runnable.run();
		} else {
			consumer.accept(event);
		}
	}

}