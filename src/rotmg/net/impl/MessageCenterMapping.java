package rotmg.net.impl;

import alde.flash.utils.MessageConsumer;
<<<<<<< HEAD:src/kabam/rotmg/net/impl/MessageCenterMapping.java
import kabam.rotmg.net.api.MessageMapping;
=======
import rotmg.net.api.MessageMapping;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/net/impl/MessageCenterMapping.java

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

}