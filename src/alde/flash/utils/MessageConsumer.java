package alde.flash.utils;

import rotmg.net.impl.Message;

import java.util.function.Consumer;

/**
 * A method that accepts a Message
 */
public class MessageConsumer<T extends Message> {

	Consumer<T> consumer;
	Runnable runnable;

	public MessageConsumer(Consumer<T> consumer) {
		this.consumer = consumer;
	}

	public MessageConsumer(Runnable runnable) {
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