package alde.flash.utils;

import flash.events.Event;
import rotmg.net.impl.Message;

import java.util.function.Consumer;

/**
 * A method that accepts a Message
 */
public class EventConsumer<T extends Event> {

	private Consumer<T> consumer;

	public EventConsumer(Consumer<T> consumer) {
		this.consumer = consumer;
	}

	public Consumer getConsumer() {
		return consumer;
	}
}
