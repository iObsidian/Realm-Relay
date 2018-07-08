package alde.flash.utils;

import java.util.function.Consumer;

import rotmg.net.impl.Message;

/**
 * A method that accepts a Message
 */
public class MessageConsumer<T extends Message> {

	Consumer<T> consumer;

	public MessageConsumer(Consumer<T> consumer) {
		this.consumer = consumer;
	}

	public Consumer getConsumer() {
		return consumer;
	}
}