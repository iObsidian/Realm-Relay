package realmrelay.game.net.impl;

public class MessageCenterMapping {

	
	private const NullHandlerProxy nullHandler = new NullHandlerProxy();

    public  MessageCenterMapping()  {
        this.handler = this.nullHandler;
        super();
    }
    private int id;
    private Class messageType;
    private int population = 1;
    private MessageHandlerProxy handler;

    public MessageMapping  setID(int param1)  {
        this.id = param1;
        return this;
    }

    public MessageMapping  toMessage(Class param1)  {
        this.messageType = param1;
        return this;
    }

    public MessageMapping  toHandler(Class param1)  {
        this.handler = new ClassHandlerProxy().setType(param1).setInjector(this.injector);
        return this;
    }

    public MessageMapping  toMethod(Function param1)  {
        this.handler = new MethodHandlerProxy().setMethod(param1);
        return this;
    }

    public MessageMapping  setPopulation(int param1)  {
        this.population = param1;
        return this;
    }

    public MessagePool  makePool()  {
        MessagePool _loc1 = new MessagePool(this.id, this.messageType, this.handler.getMethod());
        _loc1_.populate(this.population);
        return _loc1_;
    }

    
	
}
