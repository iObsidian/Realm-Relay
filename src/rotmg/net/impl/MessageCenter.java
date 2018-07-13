package rotmg.net.impl;

import alde.flash.utils.MessageConsumer;
import flash.utils.Dictionary;
import rotmg.net.api.MessageMap;
import rotmg.net.api.MessageMapping;
import rotmg.net.api.MessageProvider;

import java.lang.reflect.InvocationTargetException;

public class MessageCenter implements MessageMap, MessageProvider {

	private static final int MAXID = 256;
	private static MessageCenter instance;
	private final Dictionary<Integer, MessageCenterMapping> maps = new Dictionary<>(MAXID);

	public MessageCenter() {
		super();
	}

	public static MessageCenter getInstance() {
		if (instance == null) {
			instance = new MessageCenter();
		}
		return instance;
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
	public Message require(int id) {
		MessageCenterMapping m = maps.get(id);

		if (m != null) {
			Class classToLoad = m.messageType;

			Class[] cArg = new Class[2]; //Our constructor has 3 arguments
			cArg[0] = Integer.TYPE; //First argument is of *object* type Long
			cArg[1] = MessageConsumer.class; //Second argument is of *object* type String

			try {
				return (Message) classToLoad.getDeclaredConstructor(cArg).newInstance(id, m.messageConsumer);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("No mapping for ID '" + id + "'");
		}

		return null;

	}
}