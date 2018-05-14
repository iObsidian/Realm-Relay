package realmrelay.game.net.impl;

import realmrelay.game.net.api.MessageHandlerProxy;

import java.util.function.Consumer;

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

	public ClassHandlerProxy setInjector(Injector param1) {
		this.injector = param1;
		return this;
	}

	public Consumer getMethod() {
		return !!this.handler ? this.handler.execute : this.makeHandlerAndReturnExecute();
	}

	private Consumer makeHandlerAndReturnExecute() {
		if (this.handlerType == null) {
			return null;
		}
		this.handler = this.injector.getInstance(this.handlerType);
		return this.handler.execute;
	}


}
