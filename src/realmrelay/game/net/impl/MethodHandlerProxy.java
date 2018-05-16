package realmrelay.game.net.impl
;

import realmrelay.game.net.api.MessageHandlerProxy;

import java.util.function.Consumer;

public class MethodHandlerProxy implements MessageHandlerProxy {

    private Consumer method;

    public MethodHandlerProxy() {
        super();
    }

    public MethodHandlerProxy setMethod(Consumer param1) {
        this.method = param1;
        return this;
    }

    public Consumer getMethod() {
        return this.method;
    }
}

