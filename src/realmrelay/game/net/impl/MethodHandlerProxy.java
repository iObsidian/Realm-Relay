package realmrelay.game.net.impl
;

import realmrelay.game.net.api.MessageHandlerProxy;

public class MethodHandlerProxy implements MessageHandlerProxy {

    private Function method;

    public MethodHandlerProxy() {
        super();
    }

    public MethodHandlerProxy setMethod(Function param1) {
        this.method = param1;
        return this;
    }

    public Function getMethod() {
        return this.method;
    }
}

