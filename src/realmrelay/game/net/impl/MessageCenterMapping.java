package realmrelay.game.net.impl;

import realmrelay.game.net.api.MessageHandlerProxy;
import realmrelay.game.net.api.MessageMapping;

import java.util.function.Consumer;

public class MessageCenterMapping implements MessageMapping {

	private final NullHandlerProxy nullHandler = new NullHandlerProxy();

	public MessageCenterMapping() {
		this.handler = this.nullHandler;
	}

	private int id;
	private Class messageType;
	private int population = 1;
	private MessageHandlerProxy handler;

	public MessageMapping setID(int param1) {
		this.id = param1;
		return this;
	}

	public MessageMapping toMessage(Class param1) {
		this.messageType = param1;
		return this;
	}

	public MessageMapping toHandler(Class param1) {
		this.handler = new ClassHandlerProxy().setType(param1).setInjector(this.injector);
		return this;
	}

	public MessageMapping toMethod(Consumer param1) {
		this.handler = new MethodHandlerProxy().setMethod(param1);
		return this;
	}

	public MessageMapping setPopulation(int param1) {
		this.population = param1;
		return this;
	}

	public MessagePool makePool() {
		MessagePool _loc1 = new MessagePool(this.id, this.messageType, this.handler.getMethod());
		_loc1.populate(this.population);
		return _loc1;
	}
}

class NullHandlerProxy implements MessageHandlerProxy {

	NullHandlerProxy() {
		super();
	}

	public Consumer getMethod() {
		return null;
	}
}
