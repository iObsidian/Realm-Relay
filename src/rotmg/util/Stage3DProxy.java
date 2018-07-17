package rotmg.util;

import alde.flash.utils.EventConsumer;
import flash.display.Stage3D;
import flash.events.Event;
import flash.events.IEventDispatcher;
import rotmg.stage3D.Context3DProxy;

public class Stage3DProxy implements IEventDispatcher {

	private static Context3DProxy context3D;

	private Stage3D stage3D;

	public Stage3DProxy(Stage3D param1) {
		super();
		this.stage3D = param1;
	}

	public void requestContext3D() {
		this.stage3D.requestContext3D();
	}

	public Context3DProxy getContext3D() {
		if (context3D == null) {
			context3D = new Context3DProxy(this.stage3D.context3D);
		}
		return context3D;
	}


	public void addEventListener(String type, EventConsumer listener, boolean useCapture, int priority) {

	}

	public void addEventListener(String param1, EventConsumer param2, boolean param3, int param4, boolean param5) {
		this.stage3D.addEventListener(param1, param2, param3, param4, param5);
	}

	public void removeEventListener(String param1, EventConsumer param2, boolean param3) {
		this.stage3D.removeEventListener(param1, param2, param3);
	}


	public void addEventListener(String type, EventConsumer listener) {

	}

	public void removeEventListener(String type, EventConsumer listener) {

	}

	public boolean dispatchEvent(Event param1) {
		return this.stage3D.dispatchEvent(param1);
	}

	public boolean hasEventListener(String param1) {
		return this.stage3D.hasEventListener(param1);
	}

	public boolean willTrigger(String param1) {
		return this.stage3D.willTrigger(param1);
	}
}
