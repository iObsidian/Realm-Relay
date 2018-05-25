package rotmg.net.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.function.Consumer;

import rotmg.net.api.MessageMap;
import rotmg.net.api.MessageMapping;

public class MessageCenter implements MessageMap, MessageProvider {

	private static MessageCenter instance;

	public static MessageCenter getInstance() {
		if (instance == null) {
			instance = new MessageCenter();
		}
		return instance;
	}

	private static final int MAXID = 256;

	private final HashMap<Integer, MessageCenterMapping> maps = new HashMap<Integer, MessageCenterMapping>(MAXID);

	public MessageCenter() {
		super();
	}

	public MessageMapping map(int param1) {
		if (this.maps.get(param1) == null) {
			this.maps.put(param1, this.makeMapping(param1));
		}
		return this.maps.get(param1);
	}

	public void unmap(int param1) {
		this.maps.put(param1, null);
	}

	private MessageCenterMapping makeMapping(int param1) {
		return (MessageCenterMapping) new MessageCenterMapping().setID(param1);
	}

	@Override
	public Message require(int param1) {

		MessageCenterMapping m = maps.get(param1);

		if (m != null) {
			Class classToLoad = m.messageType;

			Class[] cArg = new Class[2]; //Our constructor has 3 arguments
			cArg[0] = Integer.TYPE; //First argument is of *object* type Long
			cArg[1] = Consumer.class; //Second argument is of *object* type String

			try {
				return (Message) classToLoad.getDeclaredConstructor(cArg).newInstance(param1, m.getConsumer());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}

		}

		System.err.println("Null mapping");

		return null;

	}
}