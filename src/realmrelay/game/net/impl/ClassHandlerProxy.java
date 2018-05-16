package realmrelay.game.net.impl;

import realmrelay.game.net.api.MessageHandlerProxy;

import java.util.function.Consumer;
import java.util.function.Function;

public class ClassHandlerProxy implements MessageHandlerProxy {

	private Class handlerType;

	private Object handler;

	public ClassHandlerProxy() {
		super();
	}

	public ClassHandlerProxy setType(Class param1) {
		this.handlerType = param1;
		return this;
	}

	public Consumer getMethod() {
		return this.handler != null ? this.handler.execute : this.makeHandlerAndReturnExecute();
	}

	private Function makeHandlerAndReturnExecute() {
		if (!this.handlerType) {
			return null;
		}

		this.handler = this.injector.getInstance(this.handlerType);
		return this.handler.execute;
	}
}
}
