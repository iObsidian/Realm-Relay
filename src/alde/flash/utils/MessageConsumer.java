package alde.flash.utils;

import rotmg.net.impl.Message;

import java.util.function.Consumer;

/**
 * A method that accepts a Message
 */
public class MessageConsumer<T extends Message> {

	private Consumer<T> consumer;

	public MessageConsumer(Consumer<T> consumer) {
		this.consumer = consumer;
	}

	public Consumer getConsumer() {
		return consumer;
	}
}
