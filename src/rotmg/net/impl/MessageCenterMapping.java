package rotmg.net.impl;

import java.util.function.Consumer;

import rotmg.net.api.MessageMapping;

/**
 * Map between CLASS and TYPE
 */
public class MessageCenterMapping implements MessageMapping {

	Class messageType;
	private int id;
	private int population = 1;

	private MessageConsumer messageConsumer;

	public MessageMapping setID(int param1) {
		this.id = param1;
		return this;
	}

	public MessageMapping toMessage(Class param1) {
		this.messageType = param1;
		return this;
	}

	public MessageMapping toMethod(MessageConsumer param1) {
		this.messageConsumer = param1;
		return this;
	}

	public MessageMapping setPopulation(int param1) {
		this.population = param1;
		return this;
	}

	public Consumer getConsumer() {
		if (messageConsumer == null) {
			return null;
		}
		return messageConsumer.getConsumer();
	}
}